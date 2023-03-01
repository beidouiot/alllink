package com.beidouiot.alllink.community.common.migrate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beidouiot.alllink.community.common.migrate.misc.Closer;
import com.beidouiot.alllink.community.common.migrate.misc.SchemaMigrationException;
import com.beidouiot.alllink.community.common.migrate.util.ClassUtils;

/**
 * Applies or rolls back migration classes
 *
 */
public class Engine {

	private static final Log log = LogFactory.getLog(Engine.class);
	
	/**
	 * Applies all migrations that have not been applied.
	 */
	public static void migrate() {
		Engine.migrate(Long.MAX_VALUE);
	}
	
	public static void migrate(long version) {
		migrate(version, true);
	}
	
	/**
	 * Applies (or rolls back) migrations such that 
	 * all migrations up and and including <code>version</code>
	 * are in the schema.
	 */
	public static void migrate(long version, Boolean isUp) {
		
		log.debug("Migrating to version " + version);
		String basePackageName = StringUtils.replace(Configure.getBaseClassName(), ".Migration_", "");
		List<Class<?>> migrationClasses = ClassUtils.getClassList(basePackageName, false);
		if (migrationClasses == null || migrationClasses.size() <= 0) {
			log.debug("No migration classes match " + Configure.getBaseClassName());
			return;
		}
		Map<Long, Class<?>> migrationMap = new HashMap<>();
		for(Class<?> clazz : migrationClasses) {
			Long index = Long.parseLong(StringUtils.split(clazz.getSimpleName(), "_")[1]);
			migrationMap.put(index , clazz);
		}
		List<Long> indexList = new ArrayList<>();
		indexList.addAll(migrationMap.keySet());
		Collections.sort(indexList);
		long currentVersion = -1;
		
		try {
			Connection connection = Configure.getConnection();
			currentVersion = getCurrentVersion(connection);
			
			log.debug("Current version is " + currentVersion);
		} catch (SQLException e) {
			log.error("Failed to get current version from the database", e);
			throw new SchemaMigrationException("Failed to get current version from the database", e);
		}

		//boolean isUp = isUpMigration(currentVersion, version);
//		List<Class<? extends Migration>> classesToMigrate = classesToMigrate();
//		classesToMigrate = orderMigrations(classesToMigrate, currentVersion, version);
		
		long lastVersion = currentVersion;
		Exception exception = null;
		if(isUp) {
			for(int i = 0; i < indexList.size(); i++) {
				Long indexVersion = indexList.get(i);
				//判断是否执行
				if((indexVersion < 1000 && indexVersion > currentVersion) || isExectue(indexVersion)) {
					try {
						((Migration)migrationMap.get(indexVersion).newInstance()).up();
						lastVersion = indexVersion;
					} catch (Exception e) {
						exception = e;
						break;
					}
					if (lastVersion != currentVersion) {
						try {
							updateCurrentVersion(Configure.getConnection(), lastVersion);
						} catch (SQLException e) {
							log.error("Failed to update " + Configure.getVersionTable() + " with versin " + lastVersion,e );
							throw new SchemaMigrationException("Failed to update " + Configure.getVersionTable() + " with versin " + lastVersion);
						}
					}
				}
				
			}
		}else {
			for(int i = indexList.size() - 1; i >= 0; i--) {
				Long indexVersion = indexList.get(i);
				if(indexVersion >= version) {
					try {
						((Migration)migrationMap.get(indexVersion).newInstance()).down();
						removeVersion(indexVersion);
					} catch (Exception e) {
						exception = e;
						break;
					} 
				}
			}
		}
		
//		for (int x = 0 ; x < classesToMigrate.size() ; x++) {
//			//Execute each migration
//
//			try {
//				Class<? extends Migration> migrationClass = classesToMigrate.get(x);
//				
//				if (log.isDebugEnabled()) {
//					String direction = isUp ? "Running " : "Rolling back";
//					log.debug(direction + " migration " + migrationClass.getName());
//				}
//				
//				lastVersion = runMigration(migrationClass, isUp);
//			} catch (Exception e) {
//				exception = e;
//				break;
//			}
//		}
		
		
		if (exception != null) {
			log.error("Migration failed",exception);
			throw new SchemaMigrationException("Migration failed", exception);
		}
		
		log.debug("Migration complete");
	}

//	private static int runMigration(Class<? extends Migration> classToMigrate, boolean isUp) {
//		int retVal = getVersionNumber(classToMigrate.getName());
//		
//		if (retVal < 0) {
//			//Theoretically, this can't happen
//			throw new SchemaMigrationException("Invalid classname " + classToMigrate.getName() +".");
//		}
//		
//		try {
//			Migration migration = (Migration)classToMigrate.newInstance();
//		
//			if (isUp) {
//				migration.up();
//			} else {
//				migration.down();
//				retVal--;  //Just removed this version
//			}
//		
//			return retVal;
//		} catch (InstantiationException e) {
//			log.error("Instantiation Exception Occured in Engine.runMigration",e);
//			throw new SchemaMigrationException(e);
//		} catch (IllegalAccessException e) {
//			log.error("IllegalAccessException Occoured in Engine.runMigration",e);
//			throw new SchemaMigrationException(e);
//		}
//	}
	
	public static Boolean isExectue(Long version){
		try {
			Connection connection = Configure.getConnection();
			String query = "select " + Configure.VERSION_FIELD_NAME + " from " + Configure.getVersionTable() + " where " + Configure.VERSION_FIELD_NAME +" = " + version;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
				return !(resultSet != null && resultSet.next()); 
			} finally {
				Closer.close(resultSet);
				Closer.close(statement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void removeVersion(Long version) {
		try {
			Connection connection = Configure.getConnection();
			String query = "delete from " + Configure.getVersionTable() + " where " + Configure.VERSION_FIELD_NAME +" = " + version;
			Statement statement = null;
			ResultSet resultSet = null;
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(query);
			} finally {
				Closer.close(resultSet);
				Closer.close(statement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static long getCurrentVersion(Connection connection) throws SQLException {
		
		//This should run on every JDBC compliant DB . . . I hope
		String query= "select " + Configure.VERSION_FIELD_NAME + " from " + Configure.getVersionTable() + " order by " + Configure.VERSION_FIELD_NAME + " desc";
		
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			
			if (resultSet != null && resultSet.next()) {
				return resultSet.getLong(Configure.VERSION_FIELD_NAME);
			} 
			
		} finally {
			Closer.close(resultSet);
			Closer.close(statement);
		}
		
		throw new RuntimeException("Couldn't determine current version");
	}

	protected static void updateCurrentVersion(Connection connection, long lastVersion) throws SQLException {
		
		//This should run on every JDBC compliant DB . . . I hope
		//String query= "update " + Configure.getVersionTable() + " set " + Configure.VERSION_FIELD_NAME + " = " + lastVersion;
		String query= "insert into " + Configure.getVersionTable() + " values(" + lastVersion + ")";
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.executeUpdate(query);
			return;
			
		} finally {
			Closer.close(statement);
		}
	}

	@SuppressWarnings("unchecked")
	protected static List<Class<? extends Migration>> classesToMigrate() {
		
		List<Class<? extends Migration>> retVal = new ArrayList<Class<? extends Migration>>();
		String baseName = Configure.getBaseClassName();
		
		int item = Configure.getStartIndex().intValue();
		
		while (true) {
			String classname = baseName + item;
			
			log.debug("Looking for classname " + classname);
			
			try {
				Class<? extends Migration> clazz = (Class<? extends Migration>) Class.forName(classname);
				retVal.add(clazz);
				
				log.debug("Found classname " + classname);
			} catch (Exception e) {
				log.debug("Assuming there are no files including or beyond " + classname);
				break;
			}
			item++;
		}		
		
		return retVal;
	}
	
	protected static List<Class<? extends Migration>> orderMigrations(List<Class<? extends Migration>> migrationClasses, int currentVersion, int targetVersion) {
		
		List<Class<? extends Migration>> retVal = new ArrayList<Class<? extends Migration>>();
		String baseName = Configure.getBaseClassName();
		boolean goUp = true;
		
		String startClass = baseName + (currentVersion + 1);
		String endClass = baseName + targetVersion;
		
		if (!isUpMigration(currentVersion, targetVersion)) {

			//Going down
			startClass = baseName + (targetVersion + 1);
			endClass = baseName + currentVersion;
			goUp = false;
			
		}
			
		boolean hasStarted = false;
			
		for (Class<? extends Migration> clazz  : migrationClasses) {
			
			if (!hasStarted) {
				
				if (clazz.getName().equals(startClass)) {
					//Just set this to true - the class 
					//will be collected in the next "if" 
					hasStarted = true;
				}
			}
			
			if (hasStarted) {
				
				int index = goUp? retVal.size() : 0;
				retVal.add(index, clazz);
				
				if (clazz.getName().equals(endClass)) {
					//We've already got the class, so
					//just get out
					break;
				}
			}
		}
		
		return retVal;
	}
	
	protected static int getVersionNumber(String classname) {
		int retVal = -1;
		
		String baseName = Configure.getBaseClassName();
		
		if (classname.startsWith(baseName)) {
			String id = classname.substring(baseName.length());
			
			try {
				return Integer.parseInt(id);
			} catch (NumberFormatException e) {
				log.error("Invalid classname - can't determine version from " + classname, e);
			}
		}
		
		return retVal;
	}
	
	private static boolean isUpMigration(long currentVersion, long targetVersion) {
		return currentVersion < targetVersion;
	}
	
	public static void main(String[] args) {
		
		Configure.configure();
		
		Integer version = null;
		if (args.length > 0) {
			try {
				version = new Integer(args[0]);
			} catch (NumberFormatException e) {
				log.error(args[0] + " is not a valid version number");
				throw new RuntimeException(e);
			}
		}
		
		if (version == null) {
			Engine.migrate();
		} else {
			Engine.migrate(version.intValue());
		}
		
		log.info("Done");
	}
}
