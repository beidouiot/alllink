package com.beidouiot.alllink.community.common.migrate.generators;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beidouiot.alllink.community.common.migrate.Configure;
import com.beidouiot.alllink.community.common.migrate.misc.Closer;
import com.beidouiot.alllink.community.common.migrate.misc.SchemaMigrationException;
import com.beidouiot.alllink.community.common.migrate.misc.Validator;
import com.beidouiot.alllink.community.common.migrate.schema.Column;
import com.beidouiot.alllink.community.common.migrate.schema.ForeignKey;
import com.beidouiot.alllink.community.common.migrate.schema.Index;
import com.beidouiot.alllink.community.common.migrate.schema.Table;

/**
 * Default Generator for migrate4j.  Designed to work specifically for
 * H2 databases.
 *
 */
public class GenericGenerator implements Generator {

	private static Log log = LogFactory.getLog(GenericGenerator.class);

	public String addColumnStatement(Column column, String tableName,
			int position) {
		throw new SchemaMigrationException("This method has not been implemented for your database yet");
	}
	
	/**
	 * ALTER TABLE <tableName> ADD <column.name> <column.type>[(<column.length>)] 
	 * 		[NOT] NULL [AUTO_INCREMENT] [PRIMARY KEY] [DEFAULT <column.default>]
	 * 		[BEFORE <existingcolumn>]
	 * 
	 * <p>Determines column placement based on existing columns.  Uses JDBC to find 
	 * the <code>afterColumn</code>, then selects the next column to be used in the
	 * <code>BEFORE</code> clause.  Obviously, Generators for databases that support 
	 * an actual <code>AFTER</code> clause must override this method.
	 */
	public String addColumnStatement(Column column, String tableName,
			String afterColumn) {

		Validator.notNull(column, "Column can not be null");
		Validator.notNull(tableName, "Table name can not be null");
	    
	    StringBuffer retVal = new StringBuffer();
	    
	    retVal.append("ALTER TABLE ")
	          .append(wrapName(tableName))
	          .append(" ADD ")
	          .append(makeColumnString(column));
	    
	    //This is based on having to pass a "before"
	    if (afterColumn != null && afterColumn.trim().length() > 0) {
	    	
	    	List<String> columnNames = getExistingColumnNames(tableName);
	    	Validator.notNull(columnNames, "Could not get existing columns to determine where to place new column");
	    	Validator.isTrue(columnNames.size() > 0, "Did not find any existing columns");
	    	
	    	String before = null;
	    	for (String col : columnNames) {
	    		if (col.equalsIgnoreCase(afterColumn)) {
	    			int index = columnNames.indexOf(col) + 1; 
	    			if (columnNames.size() >= index) {
	    				before = columnNames.get(index);
	    				break;
	    			} else if (columnNames.size() == index - 1) {
	    				//request was to add as last column
	    				before = "";
	    				break;
	    			}
	    		}
	    	}
	    	
	    	Validator.notNull(before, "Could not find " + afterColumn);
	    	
	    	if (StringUtils.trim(before).length() > 0){
	    	
	    		retVal.append(" BEFORE ")
	    			.append(wrapName(before));
	    	}
	    }
	    
	    return retVal.toString();
	    
	}
	
	/**
	 * ALTER TABLE <foreignKey.childTable> ADD CONSTRAINT <foreignKey.name>
	 * 		FOREIGN KEY (foreignKey.childColumn[,...]) REFERENCES 
	 * 		<foreignKey.parentTable> (foreignKey.parentColumn[,...])
	 */
	public String addForeignKey(ForeignKey foreignKey) {
		Validator.notNull(foreignKey, "Foreign key can not be null");
	    
	    StringBuffer retVal = new StringBuffer();
	    
	    String[] childColumns = wrapStrings(foreignKey.getChildColumns());
	    String[] parentColumns = wrapStrings(foreignKey.getParentColumns());
	    
	    
	    retVal.append("ALTER TABLE ")
	    	  .append(wrapName(foreignKey.getChildTable()))
	          .append(" ADD CONSTRAINT ")
	          .append(wrapName(foreignKey.getName()))
	          .append(" FOREIGN KEY  (")
	          .append(GeneratorHelper.makeStringList(childColumns))
	          .append(") REFERENCES ")
	          .append(wrapName(foreignKey.getParentTable()))
	          .append(" (")
	          .append(GeneratorHelper.makeStringList(parentColumns))
	          .append(")");
	    
	    return retVal.toString();
	}	
	
	/**
	 * CREATE [UNIQUE] INDEX <name> [PRIMARY KEY] ON <index.table>(<index.columnName>[,...])
	 */
	public String addIndex(Index index) {
		Validator.notNull(index, "Index can not be null");
		
		StringBuffer query = new StringBuffer("CREATE ");
		
		if (index.isUnique()) {
			query.append("UNIQUE ");
		}
		
		query.append("INDEX ")
			.append(wrapName(index.getName()))
			.append(" ");
		
		if (index.isPrimaryKey()) {
			query.append("PRIMARY KEY ");
		}
		
		query.append("ON ")
			.append(wrapName(index.getTableName()))
			.append("(");
			
		String[] columns = index.getColumnNames();
		String comma = "";
		for (int x = 0 ; x < columns.length ; x++) {	
			query.append(comma)
				.append(wrapName(columns[x]));
			
			comma = ", ";
				
		}
		
		query.append(")");
		
		return query.toString();
	}
		
	
	/**
	 * Uses JDBC meta data to determine column existence	 
	 */
	public boolean columnExists(String columnName, String tableName) {
		Validator.notNull(columnName, "Column name can not be null");
		Validator.notNull(tableName, "Table name can not be null");
		
		try {
			Connection connection = Configure.getConnection();
			
			ResultSet resultSet = null;
			
			try {
			
				DatabaseMetaData databaseMetaData = connection.getMetaData();
			
				resultSet = databaseMetaData.getColumns(null, null, tableName, columnName);
				
				if (resultSet != null && resultSet.next()) {
					return true;
				}
			} finally {
				Closer.close(resultSet);
			}
		} catch (SQLException exception) {
			log.error("Exception occoured in AbstractGenerator.exists(Column , Table )!!",exception);
			throw new SchemaMigrationException(exception);
		}
		
		return false;
	}
	
	public String createTableStatement(Table table, String options) {
		return createTableStatement(table);
	}
	
	
	/**
	 * CREATE TABLE <table.name> (<column.name> <column.type>[(<column.length>)] 
	 * 		[NOT] NULL [AUTO_INCREMENT] [PRIMARY KEY] [DEFAULT <column.default>][,...])
	 */
	public String createTableStatement(Table table) {
		
		StringBuffer retVal = new StringBuffer();

		Validator.notNull(table, "Table can not be null");		
		
		Column[] columns = table.getColumns();

		Validator.notNull(columns, "Columns can not be null");
		Validator.isTrue(columns.length > 0, "At least one column must exist");
		
		int numberOfAutoIncrementColumns = GeneratorHelper.countAutoIncrementColumns(columns);
		
		Validator.isTrue(numberOfAutoIncrementColumns <=1, "Can not have more than one autoincrement key");
				
		retVal.append("CREATE TABLE ")
			  .append(wrapName(table.getTableName()))
			  .append(" (");
		
		try {
			for (int x = 0 ; x < columns.length ; x++ ){
				Column column = (Column)columns[x];
				
				if (x > 0) {
					retVal.append(", ");
				}
				
				retVal.append(makeColumnString(column));
				
			}
		} catch (ClassCastException e) {
			log.error("A table column couldn't be cast to a column: " + e.getMessage());
			throw new SchemaMigrationException("A table column couldn't be cast to a column: " + e.getMessage());
		}
		
		return retVal.toString().trim() + ");";
	}
	
	/**
	 * ALTER TABLE <tableName> drop <columnName>
	 */
	public String dropColumnStatement(String columnName, String tableName) {

		Validator.notNull(columnName, "Column name can not be null");
		Validator.notNull(tableName, "Table name can not be null");
		
	    StringBuffer query = new StringBuffer();
	    
	    query.append("ALTER TABLE ")
	    	.append(wrapName(tableName))
	    	.append(" DROP ")
	    	.append(wrapName(columnName));
	    
		return query.toString();
	}
	
	/**
	 * DROP INDEX <indexName>
	 */
	public String dropIndex(String indexName, String tableName) {

	    Validator.notNull(indexName, "Index name can not be null");
	    
	    StringBuffer query = new StringBuffer();
	    
	    query.append("DROP INDEX ")
	    	.append(wrapName(indexName));
	    
		return query.toString();
	}
	
	/**
	 * DROP INDEX <indexName>
	 */
	public String dropIndex(Index index) {
		Validator.notNull(index, "Index can not be null");
		
		return dropIndex(index.getName(), index.getTableName());
	    
	}
		
	/**
	 * DROP TABLE <tableName>
	 */
	public String dropTableStatement(String tableName) {
		Validator.notNull(tableName, "Table name must not be null");
		
		StringBuffer retVal = new StringBuffer();
		retVal.append("DROP TABLE ")
			  .append(wrapName(tableName));
	
		return retVal.toString();
	}
	
	/**
	 * Uses JDBC meta data to determine foreignKey existence	 
	 */
	public boolean exists(ForeignKey foreignKey) {
		
		Validator.notNull(foreignKey, "Foreign key can not be null");
		
		return foreignKeyExists(foreignKey.getName(), foreignKey.getChildTable());
	}
	
	/**
	 * Uses JDBC meta data to determine foreignKey existence	 
	 */
	public boolean foreignKeyExists(String foreignKeyName, String childTableName) {
		Validator.notNull(foreignKeyName, "Foreign key name can not be null");
		Validator.notNull(childTableName, "Child table name can not be null");
		
		try {
			Connection connection = Configure.getConnection();
			ResultSet resultSet = null;
			
			try {
			
				DatabaseMetaData databaseMetaData = connection.getMetaData();
			
				resultSet = databaseMetaData.getImportedKeys(null, null, childTableName);
				
				if (resultSet != null) {
					while (resultSet.next()) {
						String parentTable = resultSet.getString("FK_NAME");
						
						if (foreignKeyName.equalsIgnoreCase(parentTable)) {
							return true;
						}
						
					}
				}
			} finally {
				Closer.close(resultSet);
			}
			
			return false;
		} catch (SQLException exception) {
                       log.error("Error occoured in H2Generator.exsists(ForeignKey)",exception);
			throw new SchemaMigrationException(exception);
		}
	}
	
	/**
	 * Uses JDBC meta data to determine index existence	 
	 */
	public boolean indexExists(String indexName, String tableName) {
		Validator.notNull(indexName, "Index name can not be null");
		Validator.notNull(tableName, "Table name can not be null");
		
		try {
			Connection connection = Configure.getConnection();
			
			ResultSet resultSet = null;
			
			try {
			
				DatabaseMetaData databaseMetaData = connection.getMetaData();
			
				resultSet = databaseMetaData.getIndexInfo(null, null, tableName, false, false);
				
				if (resultSet != null) {
					while (resultSet.next()) {
						String name = resultSet.getString("INDEX_NAME");
						if (name != null & name.equals(indexName)) {
							return true;
						}
					}
				}
			} finally {
				Closer.close(resultSet);
			}
		} catch (SQLException exception) {
			log.error("Exception occoured in AbstractGenerator.exists(Index)!!",exception);
			throw new SchemaMigrationException(exception);
		}
	
		return false;
	}
	
	/**
	 * Uses JDBC meta data to determine index existence	 
	 */
	public boolean exists(Index index) {
		
		if (index.getName() != null && index.getName().trim().length() > 0) {
			return indexExists(index.getName(), index.getTableName());
		}
		
		throw new SchemaMigrationException("Can't determine if index exists without knowing it's name");
	}
	
	/**
	 * Uses JDBC meta data to determine table existence	 
	 */
	public boolean tableExists(String tableName) {
		try {
			Connection connection = Configure.getConnection();
			ResultSet resultSet = null;
			
			try {
			
				DatabaseMetaData databaseMetaData = connection.getMetaData();
			
				resultSet = databaseMetaData.getTables(connection.getCatalog(), "", tableName, null);
				
				if (resultSet != null) {
					while (resultSet.next()) {
						if (tableName.equalsIgnoreCase(resultSet.getString("TABLE_NAME"))) {
							return true;
						}
					}
				}
			} finally {
				Closer.close(resultSet);
			}
			
			return false;
		} catch (SQLException exception) {
                       log.error("Error occoured in H2Generator.exsists(ForeignKey)",exception);
			throw new SchemaMigrationException(exception);
		}
	}

	/**
	 * ALTER TABLE <childTable> DROP CONSTRAINT <foreignKeyName>
	 */
	public String dropForeignKey(ForeignKey foreignKey) {
		Validator.notNull(foreignKey, "Foreign key can not be null");		
		
		return dropForeignKey(foreignKey.getName(), foreignKey.getChildTable());
	}

	/**
	 * ALTER TABLE <childTable> DROP CONSTRAINT <foreignKeyName>
	 */
	public String dropForeignKey(String foreignKeyName, String childTable) {
		Validator.notNull(foreignKeyName, "Foreign key name can not be null");
		Validator.notNull(childTable, "Child table name can not be null");
		
		StringBuffer retVal = new StringBuffer();
		
		retVal.append("ALTER TABLE ")
			.append(wrapName(childTable))
			.append(" DROP CONSTRAINT ")
			.append(wrapName(foreignKeyName));
		
		return retVal.toString();
	}
	
	public String wrapName(String name) {
		StringBuffer wrap = new StringBuffer();
		
		wrap.append(getIdentifier())
			.append(name)
			.append(getIdentifier());
	
		return wrap.toString();
	}
	
	public String[] wrapStrings(String[] strings) {
		
		String[] wrapped = new String[strings.length];
		
		for (int x = 0 ; x < strings.length ; x++ ) {
			wrapped[x] = wrapName(strings[x]);
		}
		
		return wrapped;
	}
	
	protected String getIdentifier() {
		return "\"";
	}
	
	protected String makeColumnString(Column column) {
		StringBuffer retVal = new StringBuffer();
		
		retVal.append(wrapName(column.getColumnName()))
			  .append(" ");		
		
		int type = column.getColumnType();
		
		String typeName = GeneratorHelper.getSqlName(type);
		if(Types.TIMESTAMP == type) {
			typeName = "DATETIME";
		}
		
		retVal.append(typeName);
		if (GeneratorHelper.needsLength(type)) {
			
			retVal.append("(")
				  .append(column.getLength())
				  .append(")");
			
		}
		retVal.append(" ");
		
		if (!column.isNullable()) {
			retVal.append("NOT ");;
		}
		retVal.append("NULL ");
		
		if (column.isAutoincrement()) {
			retVal.append("AUTO_INCREMENT ");
		}
		
		if (column.isPrimaryKey()) {
			retVal.append("PRIMARY KEY ");
		}
		if (column.getDefaultValue() != null) {
			
			retVal.append("DEFAULT '")
				  .append(column.getDefaultValue())
				  .append("' ");
		}
		return retVal.toString().trim();
	}
	
	public String renameColumn(String newColumnName, String oldColumnName,
			String tableName) {
	
		Validator.notNull(newColumnName, "New column name can not be null");
		Validator.notNull(oldColumnName, "Old column name can not be null");
		Validator.notNull(tableName, "Table name can not be null");
		
		StringBuffer query = new StringBuffer();
		
		query.append("ALTER TABLE ")
			.append(wrapName(tableName))
			.append(" ALTER COLUMN ")
			.append(wrapName(oldColumnName))
			.append(" RENAME TO ")
			.append(wrapName(newColumnName));
		
		return query.toString();
	}
	
	protected List<String> getExistingColumnNames(String tableName) {
		
		List<String> columnNames = new ArrayList<String>();
		
		try {
			Connection connection = Configure.getConnection();
			
			ResultSet resultSet = null;
			
			try {
			
				DatabaseMetaData databaseMetaData = connection.getMetaData();
			
				resultSet = databaseMetaData.getColumns(null, null, tableName, "%");
				
				if (resultSet != null) {
					while (resultSet.next()) {
						columnNames.add(resultSet.getString("COLUMN_NAME"));
					}
				}
			} finally {
				Closer.close(resultSet);
			}
			
		} catch (SQLException exception) {
			log.error(exception.getMessage(), exception);
			throw new SchemaMigrationException("Failed to get existing columns: " + exception.getMessage(), exception);
		}
		
		return columnNames; 
	}
	
	protected Column getExistingColumn(String columnName, String tableName) {
		
		int type = -1;
		int length = -1;
		boolean nullable = false;
		Object defaultObject = null;
		boolean isPrimaryKey = false;
		boolean isAutoIncrement = false;
		
		try {
			Connection connection = Configure.getConnection();
			
			ResultSet resultSet = null;
			
			try {
			
				DatabaseMetaData databaseMetaData = connection.getMetaData();
			
				resultSet = databaseMetaData.getColumns(null, null, tableName, "%");
				
				if (resultSet != null) {
					while (resultSet.next()) {
						String col = resultSet.getString("COLUMN_NAME");
						if (col != null && col.equalsIgnoreCase(columnName)) {
							type = resultSet.getInt("DATA_TYPE");
							length = resultSet.getInt("COLUMN_SIZE");
							defaultObject = resultSet.getObject("COLUMN_DEF");
							
							int nullstate = resultSet.getInt("NULLABLE");
							nullable = nullstate == DatabaseMetaData.columnNullable;
							
							if (defaultObject != null && defaultObject.toString().trim().length() == 0 && !GeneratorHelper.isStringType(type)) {
								defaultObject = null;
							}
							
							String remarks = resultSet.getString("REMARKS");
							if (remarks != null) {
								if (remarks.toLowerCase().contains("increm")) {
									isAutoIncrement = true;
								}
							}
							
							break;
						}
					}
					
					resultSet.close();
				}
				
				resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
				if (resultSet != null) {
					while (resultSet.next()) {
						String col = resultSet.getString("COLUMN_NAME");
						if (col != null && col.equalsIgnoreCase(columnName)) {
							isPrimaryKey = true;
							break;
						}
					}
					
					resultSet.close();
				}
				
				
				
			} finally {
				Closer.close(resultSet);
			}
			
		} catch (SQLException exception) {
			log.error(exception.getMessage(), exception);
			throw new SchemaMigrationException("Failed to get existing columns: " + exception.getMessage(), exception);
		}
		
		if (type >= 0) {
			return new Column(columnName, type, length, isPrimaryKey, nullable, defaultObject, isAutoIncrement);
		} 
		
		throw new SchemaMigrationException("Could not locate column " + columnName + " in table " + tableName);
	}
	
}
