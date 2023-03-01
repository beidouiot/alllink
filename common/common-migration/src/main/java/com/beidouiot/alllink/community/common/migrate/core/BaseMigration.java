package com.beidouiot.alllink.community.common.migrate.core;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.beidouiot.alllink.community.common.migrate.Define;
import com.beidouiot.alllink.community.common.migrate.Migration;
import com.beidouiot.alllink.community.common.migrate.Define.ColumnOption;
import com.beidouiot.alllink.community.common.migrate.Define.DataTypes;
import com.beidouiot.alllink.community.common.migrate.schema.Column;

public abstract class BaseMigration implements Migration {
	
	/**
	 * UUID 自定义主键
	 */
	private GeneralColumn uuidPk(){
		return new GeneralColumn(Define.column("id", Define.DataTypes.CHAR, Define.length(36), Define.primarykey(), Define.notnull()), "");
	}
	
	protected GeneralColumn uuid(String name, Boolean isNotNull){
		return new GeneralColumn(Define.column(name, Define.DataTypes.CHAR, Define.length(36), Define.notnull(isNotNull)), "");
	}
	
	protected GeneralColumn uuid(String name){
		return new GeneralColumn(Define.column(name, Define.DataTypes.CHAR, Define.length(36), Define.notnull(false)), "");
	}
	
	protected GeneralColumn uuid(String name, String comment){
		return new GeneralColumn(Define.column(name, Define.DataTypes.CHAR, Define.length(36), Define.notnull()), comment);
	}
	
	private GeneralColumn snowPk(){
		return new GeneralColumn(Define.column("id", Define.DataTypes.BIGINT, Define.length(19), Define.primarykey(), Define.notnull()), "");
	}
	
	protected GeneralColumn snowid(String name, Boolean isNotNull){
		return new GeneralColumn(Define.column(name, Define.DataTypes.BIGINT, Define.length(19), Define.notnull(isNotNull)), "");
	}
	
	protected GeneralColumn snowid(String name){
		return new GeneralColumn(Define.column(name, Define.DataTypes.BIGINT, Define.length(19), Define.notnull(false)), "");
	}
	
	protected GeneralColumn snowid(String name, String comment){
		return new GeneralColumn(Define.column(name, Define.DataTypes.BIGINT, Define.length(19), Define.notnull()), comment);
	}
	
	/**
	 * 自增长主键
	 */
	protected GeneralColumn autoincrementPk(){
		return new GeneralColumn(Define.column("id", DataTypes.BIGINT, Define.primarykey(), Define.notnull(), Define.autoincrement()), "");
	}
	
	protected GeneralColumn ahmChar(String column, String comment, Integer length){
		return column(column, comment, DataTypes.CHAR, Define.length(length), Define.notnull(false), Define.defaultValue(null));
	}
	
	protected GeneralColumn ahmVarchar(String column, String comment, Integer length){
		return column(column, comment, DataTypes.VARCHAR, Define.length(length), Define.notnull(false), Define.defaultValue(null));
	}
	
	protected GeneralColumn ahmDateTime(String column, String comment){
		return column(column, comment, DataTypes.TIMESTAMP, Define.notnull(false), Define.defaultValue(null));
	}
	
	protected GeneralColumn ahmBigint(String column, String comment){
		return column(column, comment, DataTypes.BIGINT, Define.notnull(false), Define.defaultValue(null));
	}
	
	protected GeneralColumn ahmTinyint(String column, String comment){
		return column(column, comment, DataTypes.TINYINT, Define.notnull(false), Define.defaultValue(null));
	}
	
	protected GeneralColumn ahmInt(String column, String comment){
		return column(column, comment, DataTypes.INTEGER, Define.notnull(false), Define.defaultValue(null));
	}
	
	protected GeneralColumn ahmDouble(String column, String comment){
		return column(column, comment, DataTypes.DOUBLE, Define.notnull(false), Define.defaultValue(null));
	}
	
	protected GeneralColumn ahmText(String column, String comment){
		return column(column, comment, DataTypes.LONGVARCHAR);
	}
	
	protected GeneralColumn pk(){
		return snowPk();
	}
	
	protected GeneralColumn uidPk(){
		return uuidPk();
	}
	
	protected GeneralColumn column(String columnName, String comment, DataTypes dataType ,ColumnOption<?>... options){
		return new GeneralColumn(Define.column(columnName, dataType, options), comment);
	}
	
	protected void addColumn(GeneralColumn gColumn, String tableName){
		MigrationHelper.addColumn(gColumn.getColumn(), tableName);
		MigrationHelper.addColumnCommnent(tableName, gColumn.getColumn().getColumnName(), Define.dataTypeMap.get(gColumn.getColumn().getColumnType()).toString(), gColumn.getColumn().getLength(), gColumn.getComment());
	}
	
	protected void addColumn(String tableName, String columnName, String comment, DataTypes dataType , Integer length){
		
		if(dataType.equals(DataTypes.TIMESTAMP)){
			MigrationHelper.addColumn(Define.column(columnName, dataType),tableName);
		}else{
			MigrationHelper.addColumn(Define.column(columnName, dataType, Define.length(length)), tableName);
		}
		MigrationHelper.addColumnCommnent(tableName, columnName, dataType.toString(), length, comment);
	}
	
	protected void addColumn(String tableName, String columnName, String comment, DataTypes dataType , Integer length, Object defaultValue){
		MigrationHelper.addColumn(Define.column(columnName, dataType, Define.length(length), Define.defaultValue(defaultValue)), tableName);
		MigrationHelper.addColumnCommnent(tableName, columnName, dataType.toString(), length, comment);
		MigrationHelper.setDefault(tableName, columnName, defaultValue);
	}
	/**
	 * @param tableName 表名
	 * @param tableComment 表的备注
	 * @param isAddCommon 是否增加常用字段
	 * @param jrColumns 表列
	 */
	protected void table(String tableName, String tableComment ,Boolean isAddCommon, GeneralColumn... cols){
		Column[] columns = new Column[cols.length];
		Map<String, String> commentMap = new HashMap<String, String>();
		for(int i = 0; i < cols.length; i ++){
			Column column = cols[i].getColumn(); 
			columns[i] = column;
			commentMap.put(column.getColumnName(), cols[i].getComment());
		}
		MigrationHelper.createTable(Define.table(tableName, columns));
		if(isAddCommon){
			MigrationHelper.addCommonColumns(tableName);
		}
		for(GeneralColumn c : cols) {
		    if(StringUtils.isNotBlank(c.getComment())) {		        
		        MigrationHelper.addComment(tableName, c);
		    }
		}
		MigrationHelper.addTableComment(tableName, tableComment);
	}
}
