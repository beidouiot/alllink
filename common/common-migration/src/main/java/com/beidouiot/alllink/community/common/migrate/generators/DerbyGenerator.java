package com.beidouiot.alllink.community.common.migrate.generators;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.beidouiot.alllink.community.common.migrate.Configure;
import com.beidouiot.alllink.community.common.migrate.misc.Closer;
import com.beidouiot.alllink.community.common.migrate.misc.SchemaMigrationException;
import com.beidouiot.alllink.community.common.migrate.misc.Validator;
import com.beidouiot.alllink.community.common.migrate.schema.Column;
import com.beidouiot.alllink.community.common.migrate.schema.Table;

public class DerbyGenerator extends GenericGenerator {
	
	@Override
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
			String separator = "";
			
			for (int x = 0 ; x < columns.length ; x++ ){
				Column column = (Column)columns[x];
				
				retVal.append(separator);
				separator = ", ";
				
				retVal.append(makeColumnString(column));
				
			}
			
			Column[] primaryKeys = GeneratorHelper.getPrimaryKeyColumns(columns);
			if (primaryKeys != null) {
				
				retVal.append(separator);
				retVal.append("PRIMARY KEY (");
				separator = "";
				
				for (int x = 0; x < primaryKeys.length; x++) {
					Column primaryKey = primaryKeys[x];
					
					retVal.append(wrapName(primaryKey.getColumnName()));
					
					retVal.append(separator);
					
					separator = ", ";
				}
				
				retVal.append(")");
			}
			
		} catch (ClassCastException e) {
			throw new SchemaMigrationException("A table column couldn't be cast to a column: " + e.getMessage());
		}
		
		
		
		retVal.append(")");
		
		return retVal.toString();
		
	}
	
	@Override
	protected String makeColumnString(Column column) {
		StringBuffer retVal = new StringBuffer();
		
		retVal.append(wrapName(column.getColumnName()))
			  .append(" ");		
		
		int type = column.getColumnType();
		
		retVal.append(GeneratorHelper.getSqlName(type));
		if (GeneratorHelper.needsLength(type)) {
			
			retVal.append("(")
				  .append(column.getLength())
				  .append(")");
			
		}
		retVal.append(" ");		
		
		if (!column.isNullable()) {
			retVal.append("NOT NULL ");
		}
		
		if (column.isAutoincrement()) {
			retVal.append("GENERATED BY DEFAULT AS IDENTITY ");
		} else {
			
		}
		
		if (column.getDefaultValue() != null) {
			retVal.append("DEFAULT '")
				  .append(column.getDefaultValue())
				  .append("' ");
		}
		
		return retVal.toString().trim();
	}
	
	@Override
	public boolean tableExists(String tableName) {
		
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		String query = "select * from SYS.SYSTABLES";
		
		try {
			conn = Configure.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery(query);
			
			if (resultSet != null) {
				while (resultSet.next()) {
					
					String table = resultSet.getString("TABLENAME");
					if (table.equals(tableName)) {
						return true;
					}
				}
			}
			
		} catch (SQLException exception) {
			throw new SchemaMigrationException("Failed to determine whether " + tableName + " exists", exception);
		} finally {
			Closer.close(statement);
			Closer.close(resultSet);
		}
		
		return false;
		
	}
}
