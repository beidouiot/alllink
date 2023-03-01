package com.beidouiot.alllink.community.common.migrate;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.beidouiot.alllink.community.common.migrate.generators.Generator;
import com.beidouiot.alllink.community.common.migrate.generators.GeneratorFactory;
import com.beidouiot.alllink.community.common.migrate.misc.Closer;
import com.beidouiot.alllink.community.common.migrate.misc.SchemaMigrationException;
import com.beidouiot.alllink.community.common.migrate.misc.Validator;
import com.beidouiot.alllink.community.common.migrate.schema.Column;
import com.beidouiot.alllink.community.common.migrate.schema.ForeignKey;
import com.beidouiot.alllink.community.common.migrate.schema.Index;
import com.beidouiot.alllink.community.common.migrate.schema.Table;

/**
 * Contains commands that can be called from within Migration classes.
 *
 */
public class Execute {
	
	private static Log log = LogFactory.getLog(Execute.class);
	
	/**
	 * Indicates whether an index exists
	 * 
	 * @param index
	 * @return
	 */
	public static boolean exists(Index index) {
		Validator.notNull(index, "Index can not be null");
		
		return indexExists(index.getName(), index.getTableName());
	}
	
	/**
	 * Indicates whether an index exists
	 * 
	 * @param indexName
	 * @param tableName
	 * @return
	 */
	public static boolean indexExists(String indexName, String tableName) {
		Validator.notNull(indexName, "Index name can not be null");
		
		try {
			Connection connection = Configure.getConnection();
		
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			return generator.indexExists(indexName, tableName);
			
		} catch (SQLException e) {
			String message = "Unable to check index " + indexName + " on table " + tableName;
			log.error(message, e);
			throw new SchemaMigrationException(message, e);
		} 
	}
	
	/**
	 * Indicates whether a table exists
	 * 
	 * @param table
	 * @return
	 */
	public static boolean exists(Table table) {
		Validator.notNull(table, "Table can not be null");
		
		return tableExists(table.getTableName());
	}
	
	/**
	 * Indicates whether a table exists
	 * 
	 * @param tableName
	 * @return
	 */
	public static boolean tableExists(String tableName) {
		Validator.notNull(tableName, "Table name can not be null");
		
		try {
			Connection connection = Configure.getConnection();
		
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			return generator.tableExists(tableName);
			
		} catch (SQLException e) {
			log.error("Unable to create table " + tableName, e);
			throw new SchemaMigrationException("Unable to check table " + tableName, e);
		}
	}
	
	/**
	 * Indicates whether a table contains a column
	 * 
	 * @param column
	 * @param table
	 * @return
	 */
	public static boolean exists(Column column, Table table) {
		Validator.notNull(table, "Table can not be null");
		Validator.notNull(column, "Column can not be null");
		
		return columnExists(column.getColumnName(), table.getTableName());
		
	}
	
	/**
	 * Indicates whether a table contains a column
	 * 
	 * @param column
	 * @param tableName
	 * @return
	 */
	public static boolean exists(Column column, String tableName) {
		Validator.notNull(column, "Column can not be null");
		
		return columnExists(column.getColumnName(), tableName);
		
	}
	
	/**
	 * Indicates whether a table contains a column
	 * 
	 * @param columnName
	 * @param tableName
	 * @return
	 */
	public static boolean columnExists(String columnName, String tableName) {
		Validator.notNull(tableName, "Table Name can not be null");
		Validator.notNull(columnName, "Column Name can not be null");
		
		if (!tableExists(tableName)) {
			return false;
		}
		
		try {
			Connection connection = Configure.getConnection();
		
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			return generator.columnExists(columnName, tableName);
			
		} catch (SQLException e) {
			throw new SchemaMigrationException("Unable to check column " + columnName + " on table " + tableName, e);
		} 
	}
	
	/**
	 * Indicates whether a foreign key exists
	 * 
	 * @param foreignKeyName
	 * @param childTableName
	 * @return
	 */
	public static boolean foreignKeyExists(String foreignKeyName, String childTableName) {
		Validator.notNull(foreignKeyName, "Foreign key name can not be null");
		Validator.notNull(childTableName, "Child table name can not be null");
		
		try {
			Connection connection = Configure.getConnection();
		
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			return generator.foreignKeyExists(foreignKeyName, childTableName);
			
		} catch (SQLException e) {
			log.error("Unable to check foreign key " + foreignKeyName + " on table " + childTableName, e);
			throw new SchemaMigrationException("Unable to check foreign key " + foreignKeyName + " on table " + childTableName, e);
		} 
	}
	
	/**
	 * Indicates whether a foreign key exists
	 * 
	 * @param foreignKey
	 * @return
	 */
	public static boolean exists(ForeignKey foreignKey) {
		Validator.notNull(foreignKey, "Foreign key can not be null");
		
		try {
			Connection connection = Configure.getConnection();
		
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			return generator.exists(foreignKey);
			
		} catch (SQLException e) {
			log.error("Unable to check foreign key " + foreignKey.getName() + " on table " + foreignKey.getParentTable(), e);
			throw new SchemaMigrationException("Unable to check foreign key " + foreignKey.getName() + " on table " + foreignKey.getParentTable(), e);
		} 
	}
	
	/**
	 * Create a table
	 * 
	 * @param table
	 */
	public static void createTable(Table table) {
		createTable(table, null);
	}
	
	/**
	 * Create a table with database specific options.
	 * This allows, for example, passing an engine type
	 * to MySQL.  While the <code>tableOptions</code>
	 * may be ignored for database products that do not
	 * accept such things, be aware that using this 
	 * argument may make your migrations no longer cross
	 * product compatible.
	 * 
	 * @param table
	 * @param tableOptions
	 */
	public static void createTable(Table table, String tableOptions){
		Validator.notNull(table, "Table can not be null");
		
		if (exists(table)) {
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
		
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query;
			if (tableOptions != null) {
				query = generator.createTableStatement(table, tableOptions);
			} else {
				query = generator.createTableStatement(table);
			}
			
			executeStatement(connection, query);
			
		} catch (SQLException e) {
			log.error("Unable to create table " + table.getTableName(), e);
			throw new SchemaMigrationException("Unable to create table " + table.getTableName(), e);
		} 
	}
	
	/**
	 * Drop a table
	 * 
	 * @param tableName
	 */
	public static void dropTable(String tableName) {
		Validator.notNull(tableName, "Table name can not be null");
		
		if (!tableExists(tableName)) {
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.dropTableStatement(tableName);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to drop table " + tableName, e);
			throw new SchemaMigrationException("Unable to drop table " + tableName, e);
		} 
	}
	
	/**
	 * Add a column to a table
	 * 
	 * @param column
	 * @param tableName
	 */
	public static void addColumn(Column column, String tableName) {
		addColumn(column, tableName, null);
	}
	
	/**
	 * Add a column to a table
	 * 
	 * @param column
	 * @param table
	 * @param afterColumn
	 */
	public static void addColumn(Column column, String table, String afterColumn) {
		Validator.notNull(column, "Column can not be null");
		Validator.notNull(table, "Table can not be null");
		Validator.isTrue(tableExists(table), "Table does not exist");
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.addColumnStatement(column, table, afterColumn);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to alter table " + table + " and add column " + column.getColumnName(), e);
			throw new SchemaMigrationException("Unable to alter table " + table + " and add column " + column.getColumnName(), e);
		}
	}
	
	/**
	 * Add a column to a table
	 * 
	 * @param column
	 * @param table
	 * @param position
	 */
	public static void addColumn(Column column, String table, int position) {
		Validator.notNull(column, "Column can not be null");
		Validator.notNull(table, "Table can not be null");
		Validator.isTrue(tableExists(table), "Table does not exist");
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.addColumnStatement(column, table, position);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to alter table " + table + " and add column " + column.getColumnName(), e);
			throw new SchemaMigrationException("Unable to alter table " + table + " and add column " + column.getColumnName(), e);
		}
	}
	
	/**
	 * Drop a column from a table
	 * 
	 * @param columnName
	 * @param tableName
	 */
	public static void dropColumn(String columnName, String tableName) {
		Validator.notNull(columnName, "Column can not be null");
		Validator.notNull(tableName, "Table can not be null");
		Validator.isTrue(tableExists(tableName), "Table does not exist");
		
		if (!columnExists(columnName, tableName)) {
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.dropColumnStatement(columnName, tableName);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to alter table " + tableName + " and drop column " + columnName, e);
			throw new SchemaMigrationException("Unable to alter table " + tableName + " and drop column " + columnName, e);
		}
		
	}
	
	/**
	 * Add an index
	 * 
	 * @param index
	 */
	public static void addIndex(Index index) {
		Validator.notNull(index, "Index can not be null");
		
		if (exists(index)) {
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.addIndex(index);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to add index " + index.getName() + " on table " + index.getTableName(), e);
			throw new SchemaMigrationException("Unable to add index " + index.getName() + " on table " + index.getTableName(), e);
		}
	}
	
	/**
	 * Drop an index
	 * 
	 * @param indexName
	 * @param tableName
	 */
	public static void dropIndex(String indexName, String tableName) {
		Validator.notNull(indexName, "Index can not be null");
		
		if (!indexExists(indexName, tableName)) {
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.dropIndex(indexName, tableName);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to drop index " + indexName + " from table " + tableName, e);
			throw new SchemaMigrationException("Unable to drop index " + indexName + " from table " + tableName, e);
		}
	}
	
	/**
	 * Add a foreign key
	 * 
	 * @param foreignKey
	 */
	public static void addForeignKey(ForeignKey foreignKey) {
		Validator.notNull(foreignKey, "ForeignKey can not be null");
		
		if (exists(foreignKey)) {
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.addForeignKey(foreignKey);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to add foreign key " + foreignKey.getName() + " on table " + foreignKey.getParentTable(), e);
			throw new SchemaMigrationException("Unable to add foreign key " + foreignKey.getName() + " on table " + foreignKey.getParentTable(), e);
		}
	}
	
	/**
	 * Drop a foreign key
	 * 
	 * @param foreignKey
	 */
	public static void dropForeignKey(ForeignKey foreignKey) {
		Validator.notNull(foreignKey, "ForeignKey can not be null");
		
		if (!exists(foreignKey)) {
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.dropForeignKey(foreignKey);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to drop foreign key " + foreignKey.getName() + " from table " + foreignKey.getParentTable(), e);
			throw new SchemaMigrationException("Unable to drop foreign key " + foreignKey.getName() + " from table " + foreignKey.getParentTable(), e);
		}
	}
	
	/**
	 * Drop a foreign key
	 * 
	 * @param foreignKeyName
	 * @param childTableName
	 */
	public static void dropForeignKey(String foreignKeyName, String childTableName) {
		Validator.notNull(foreignKeyName, "ForeignKey can not be null");
		
		if (!foreignKeyExists(foreignKeyName, childTableName)) {
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.dropForeignKey(foreignKeyName, childTableName);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			log.error("Unable to drop foreign key " + foreignKeyName + " from table " + childTableName, e);
			throw new SchemaMigrationException("Unable to drop foreign key " + foreignKeyName + " from table " + childTableName, e);
		}
		
	}
	
	/**
	 * Rename a column
	 * 
	 * @param newColumnName
	 * @param oldColumnName
	 * @param tableName
	 */
	public static void renameColumn(String newColumnName, String oldColumnName, String tableName) {
		Validator.notNull(newColumnName, "New column name can not be null");
		Validator.notNull(oldColumnName, "Old column name can not be null");
		Validator.notNull(tableName, "Table name can not be null");
		
		if (!columnExists(oldColumnName, tableName)) {
			
			//Is this already done?
			if (!columnExists(newColumnName, tableName)) {
				throw new SchemaMigrationException("Column " + oldColumnName + " does not exist in table " + tableName);
			}
			
			//We must have already done this
			return;
		}
		
		try {
			Connection connection = Configure.getConnection();
			
			Generator generator = GeneratorFactory.getGenerator(connection);
			
			String query = generator.renameColumn(newColumnName, oldColumnName, tableName);
			
			executeStatement(connection, query);
		} catch (SQLException e) {
			String message = "Unable to rename column " + oldColumnName + " to " + newColumnName + " on table " + tableName;
			log.error(message, e);
			throw new SchemaMigrationException(message, e);
		}
	}
		
	private static void executeStatement(Connection connection, String query) throws SQLException {
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			statement.execute(query);
		} finally {
			Closer.close(statement);
		}
		
	}
}
