package com.beidouiot.alllink.community.common.migrate.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.beidouiot.alllink.community.common.migrate.Configure;
import com.beidouiot.alllink.community.common.migrate.Define;
import com.beidouiot.alllink.community.common.migrate.Engine;
import com.beidouiot.alllink.community.common.migrate.Execute;
import com.beidouiot.alllink.community.common.migrate.Define.DataTypes;
import com.beidouiot.alllink.community.common.migrate.config.DbConfigration;
import com.beidouiot.alllink.community.common.migrate.schema.Column;
import com.beidouiot.alllink.community.common.migrate.schema.Table;


public class MigrationHelper {
	
	static Log log = LogFactory.getLog(MigrationHelper.class);
	
	public static void updateDataBase(DbConfigration configration, String module){
		String pakageName = "com.isemciga.migration";
		log.info("同步"+module + "模块 Migration 版本........Begin");
		String tableName = "version_" + module;
		initDataBase(configration, module, pakageName, tableName);
		Configure.configure(configration.mysqlUrl, 
										configration.mysqlDriverClassName, 
										configration.mysqlUserName, 
										configration.mysqlPassword, 
										"", pakageName, 
										Configure.DEFAULT_CLASSNAME_PREFIX,
										Configure.DEFAULT_SEPARATOR, 
										Configure.DEFAULT_START_INDEX, 
										tableName);
		if(!Execute.tableExists(tableName)){
			try {
				log.info("初始化"+module + "模块 Migration 版本........Begin");
				MigrationHelper.initMigration4j(tableName);
				log.info("初始化"+module + "模块 Migration 版本........End");
			} catch (Exception e) {
				log.info("初始化"+module + "模块 Migration 版本........Exception");
			}
		}
		Engine.migrate();
		log.info("同步"+module + "模块 Migration 版本........End");
	}
	
	public static void dropTable(String tableName){
		if(Execute.tableExists(tableName)){
			Execute.dropTable(tableName);
		}
	}
	
	public static void createTable(Table table){
		if(Execute.tableExists(table.getTableName())){
			Execute.dropTable(table.getTableName());
		}
		Execute.createTable(table);
	}
	
	public static void addColumn(Column column, String tableName){
		dropColumn(column.getColumnName(), tableName);
		Execute.addColumn(column, tableName);
	}
	
	public static void dropColumn(String columnName, String tableName){
		if(Execute.columnExists(columnName, tableName)){
			Execute.dropColumn(columnName, tableName);
		}
	}
	
	public static void initDataBase(DbConfigration configration, String databaseName, String pakageName, String tableName){
		Configure.configure(configration.mysqlUrl.replace(databaseName, "mysql"), 
				configration.mysqlDriverClassName, 
				configration.mysqlUserName, 
				configration.mysqlPassword, 
				"", pakageName, 
				Configure.DEFAULT_CLASSNAME_PREFIX,
				Configure.DEFAULT_SEPARATOR, 
				Configure.DEFAULT_START_INDEX, 
				tableName);
		executeSql("CREATE DATABASE IF NOT EXISTS "+ databaseName +" DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;");
	}
	
	public static void initMigrationData(String versionTable){
		executeSql("insert into "+ versionTable +" values (0);");
	}
	
	public static void initMigrationVersion(String versionTable){
		String sql = "create table "+ versionTable +" (version bigint primary key);";
		executeSql(sql);
	}
	
	public static void initMigration4j(String versionTable){
		initMigrationVersion(versionTable);
		initMigrationData(versionTable);
	}
	
	public static void addTreeColumns(String tableName){
		addColumn(Define.column("_level", DataTypes.INTEGER), tableName);
		addColumnCommnent(tableName, "_level", "int", 11, "等级");
		
		addColumn(Define.column("_parent_id", DataTypes.CHAR, Define.length(36)), tableName);
		addColumnCommnent(tableName, "_parent_id", "char", 36, "关联父节点Id");
		
		addColumn(Define.column("_is_leaf", DataTypes.TINYINT), tableName);
		addColumnCommnent(tableName, "_is_leaf", "tinyint", 4, "是否为叶子");
		
		addColumn(Define.column("_expanded", DataTypes.TINYINT), tableName);
		addColumnCommnent(tableName, "_expanded", "tinyint", 4, "是否展开/折叠");
	}
	
	public static void dropTreeColumns(String tableName){
		dropColumn("_level", tableName);
		dropColumn("_parent_id", tableName);
		dropColumn("_is_leaf", tableName);
		dropColumn("_expanded", tableName);
	}
	
	public static void addCommonColumns(String tableName){
		
		addColumn(Define.column("create_by", DataTypes.VARCHAR, Define.length(20)), tableName);
		addColumnCommnent(tableName, "created_by", "varchar", 20, "创建人");
		
		addColumn(Define.column("created_date",DataTypes.TIMESTAMP), tableName);
		addColumnCommnent(tableName, "created_date", "datetime", 0, "创建时间");
		
		addColumn(Define.column("updated_by", DataTypes.VARCHAR, Define.length(20)), tableName);
		addColumnCommnent(tableName, "updated_by", "varchar", 20, "修改人");
		
		addColumn(Define.column("updated_date",DataTypes.TIMESTAMP), tableName);
		addColumnCommnent(tableName, "updated_date", "datetime", 0, "修改时间");
		
		addColumn(Define.column("delete_flag",DataTypes.TINYINT, Define.defaultValue("0"), Define.notnull(false)), tableName);
		addColumnCommnent(tableName, "delete_flag", "tinyint", 0, "0", "是否删除，0--否  1--是");
		
		addColumn(Define.column("opt_lock_version",DataTypes.INTEGER, Define.defaultValue("0"), Define.notnull(false)), tableName);
		addColumnCommnent(tableName, "opt_lock_version", "int", 0, "0", "乐观锁版本号");
	}
	
	public static void dropCommonColumns(String tableName){
		dropColumn("created_by", tableName);
		dropColumn("create_date", tableName);
		dropColumn("updated_by", tableName);
		dropColumn("updated_date", tableName);
		dropColumn("delete_flag", tableName);
		dropColumn("opt_lock_version", tableName);
	}
	
	public static void addHibernateVersionColumn(String tableName){
		addColumn(Define.column("opt_lock_version", DataTypes.BIGINT, Define.defaultValue(0)), tableName);
	}
	
	public static void addDecimalColumn(String columnName, String tableName, Integer length, Integer mon, String afterColumnName){
		
		if(StringUtils.hasText(tableName)){
			String sql = "ALTER TABLE " +  tableName+ " ADD " + columnName +" decimal(" + length +"," + mon +") " +" after "+  afterColumnName;
			executeSql(sql);
		}
	}
	
	public static void addDateTime(String columnName, String tableName, String afterColumnName, String comment){
		if(StringUtils.hasText(tableName)){
			dropColumn(columnName, tableName);
			String sql = "ALTER TABLE " +  tableName+ " ADD " + columnName +" datetime DEFAULT NULL COMMENT '"+comment+"'" +" after "+  afterColumnName;
			executeSql(sql);
		}
	}
	
	public static void changeTableName(String oldName, String newName){
		if(StringUtils.hasText(oldName) && StringUtils.hasText(newName)){
			String sql ="ALTER TABLE "+ oldName +" RENAME TO " + newName;
			executeSql(sql);
		}
	}
	
	public static void alterColumn(String tableName, String column, String type){
		String sql = "ALTER TABLE "+ tableName +" MODIFY COLUMN " +column+ " "+type;
		executeSql(sql);
	}
	
	public static void setDefault(String tableName, String column, Object value){
		String sql = "alter table "+tableName+" alter column "+ column +" set default '"+ value +"'";
		executeSql(sql);
	}
	
	public static void changeColumnName(String tableName,String oldName, String newName, String type){
		if(StringUtils.hasText(tableName) && StringUtils.hasText(oldName) && StringUtils.hasText(newName)){
			String sql = "alter table "+tableName+" change "+oldName+" "+newName+" "+type;
			executeSql(sql);
		}
	}
	
	public static void addTableComment(String tableName, String tableComment){
		String sql = "ALTER TABLE " + tableName + " COMMENT='"+ tableComment +"'";
		executeSql(sql);
	}
	
	public static void addColumnCommnent(String tableName, String columnName, 
			String columnType, int datasize, String comment){
		if(StringUtils.isEmpty(comment)) return;
		String dataType = columnType.toUpperCase();
		if(datasize > 0 && Objects.equals(dataType, "LONGVARCHAR")){
			dataType="text";
		}else if(datasize > 0 && !Objects.equals(dataType, "TIMESTAMP") &&
									  !Objects.equals(dataType, "DATETIME") &&
									  !Objects.equals(dataType, "INT") &&
									  !Objects.equals(dataType, "BIGINT") &&
									  !Objects.equals(dataType, "TINYINT") &&
									  !Objects.equals(dataType, "INT UNSIGNED") && 
									  !Objects.equals(dataType, "DECIMAL") &&
									  !Objects.equals(dataType, "DOUBLE")){
			dataType = dataType +"(" + datasize + ")";
		}else if(Objects.equals(dataType, "DECIMAL")){
			dataType = dataType +"(15,4)";
		}
		String sql  = "ALTER table "+tableName+" MODIFY "+columnName+" "+ dataType +"  COMMENT '"+ comment  +"'";
		executeSql(sql);
	}
	
	public static void addColumnCommnent(String tableName, String columnName, 
	        String columnType, int datasize, boolean nullable, String comment){
	    if(StringUtils.isEmpty(comment)) return;
	    String dataType = columnType.toUpperCase();
	    if(datasize > 0 && Objects.equals(dataType, "LONGVARCHAR")){
	        dataType="text";
	    }else if(datasize > 0 && !Objects.equals(dataType, "TIMESTAMP") &&
	            !Objects.equals(dataType, "DATETIME") &&
	            !Objects.equals(dataType, "INT") &&
	            !Objects.equals(dataType, "BIGINT") &&
	            !Objects.equals(dataType, "TINYINT") &&
	            !Objects.equals(dataType, "INT UNSIGNED") && 
	            !Objects.equals(dataType, "DECIMAL") &&
	            !Objects.equals(dataType, "DOUBLE")){
	        dataType = dataType +"(" + datasize + ")";
	    }else if(Objects.equals(dataType, "DECIMAL")){
	        dataType = dataType +"(15,4)";
	    }
	    
	    String nullableSQL = nullable ? " NULL " : " NOT NULL ";
	    
	    String sql  = "ALTER table "+tableName+" MODIFY "+columnName+" "+ dataType + nullableSQL + "  COMMENT '"+ comment  +"'";
	    
	    executeSql(sql);
	}
	
	public static void addColumnCommnent(String tableName, String columnName, 
			String columnType, int datasize, String defaultValue, String comment){
		if(StringUtils.isEmpty(comment)) return;
		String dataType = columnType.toUpperCase();
		if(datasize > 0 && Objects.equals(dataType, "LONGVARCHAR")){
			dataType="text";
		}else if(datasize > 0 && !Objects.equals(dataType, "TIMESTAMP") &&
				!Objects.equals(dataType, "DATETIME") &&
				!Objects.equals(dataType, "INT") &&
				!Objects.equals(dataType, "BIGINT") &&
				!Objects.equals(dataType, "TINYINT") &&
				!Objects.equals(dataType, "INT UNSIGNED") && 
				!Objects.equals(dataType, "DECIMAL") &&
				!Objects.equals(dataType, "DOUBLE")){
			dataType = dataType +"(" + datasize + ")";
		}else if(Objects.equals(dataType, "DECIMAL")){
			dataType = dataType +"(15,4)";
		}
		String sql  = "ALTER table "+tableName+" MODIFY "+columnName+" "+ dataType + " DEFAULT '" + defaultValue + "'" +"  COMMENT '"+ comment  +"'";
		executeSql(sql);
	}
	
	public static void addColumnsComment(String tableName, ResultSet resultSet, Map<String, String> columnComments) throws Exception{
		String columnName = resultSet.getString("COLUMN_NAME"); 
		String columnType = resultSet.getString("TYPE_NAME"); 
		int datasize = resultSet.getInt("COLUMN_SIZE");
		String comment = columnComments.get(columnName);
		if(StringUtils.hasText(comment)){
			addColumnCommnent(tableName, columnName, columnType, datasize, comment);
		}
	}
	public static void addWorkFlowColumns(String tableName, String tableComment){
		if(!Execute.columnExists("_is_submit", tableName)){
			addColumn( Define.column("_is_submit", Define.DataTypes.TINYINT), tableName);//是否已经发送
		}
		if(!Execute.columnExists("_instance_code", tableName)){
			addColumn( Define.column("_instance_code", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//实例编号
		}
		if(!Execute.columnExists("_instance_id", tableName)){
			addColumn( Define.column("_instance_id", Define.DataTypes.VARCHAR, Define.length(36)), tableName);//实例id
		}
		if(!Execute.columnExists("_task_name", tableName)){
			addColumn( Define.column("_task_name", Define.DataTypes.VARCHAR, Define.length(256)), tableName);//任务名称
		}
		if(!Execute.columnExists("_task_no", tableName)){
			addColumn( Define.column("_task_no", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//任务编号
		}
		if(!Execute.columnExists("_before_user_name", tableName)){
			addColumn( Define.column("_before_user_name", Define.DataTypes.VARCHAR, Define.length(512)), tableName);//上一步审批人姓名
		}
		if(!Execute.columnExists("_before_complete_time", tableName)){
			addColumn( Define.column("_before_complete_time", Define.DataTypes.TIMESTAMP), tableName);//上一步审批完成时间
		}
		if(!Execute.columnExists("_after_user_name", tableName)){
			addColumn( Define.column("_after_user_name", Define.DataTypes.VARCHAR, Define.length(512)), tableName);//下一步审批人姓名
		}
		if(!Execute.columnExists("_task_status", tableName)){
			addColumn( Define.column("_task_status", Define.DataTypes.INTEGER, Define.length(11)), tableName);//任务状态
		}
		if(!Execute.columnExists("_form_id", tableName)){
			addColumn( Define.column("_form_id", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//表单Id
		}
		if(!Execute.columnExists("_salesman_id", tableName)){
			addColumn( Define.column("_salesman_id", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//跟单业务员id
		}
		if(!Execute.columnExists("_is_declaration_again", tableName)){
			addColumn( Define.column("_is_declaration_again", Define.DataTypes.TINYINT), tableName);//是否重新报单
		}
		if(!Execute.columnExists("_city_key", tableName)){
			addColumn( Define.column("_city_key", Define.DataTypes.VARCHAR, Define.length(512)), tableName);//城市Key
		}
		if(!Execute.columnExists("_is_allow_submit", tableName)){
			addColumn( Define.column("_is_allow_submit", Define.DataTypes.TINYINT), tableName);//是否允许提交
		}
		if(!Execute.columnExists("_is_allow_advance_pay", tableName)){
			addColumn( Define.column("_is_allow_advance_pay", Define.DataTypes.TINYINT), tableName);//是否允许提前还款
		}
		if(!Execute.columnExists("_type_id", tableName)){
			addColumn( Define.column("_type_id", Define.DataTypes.VARCHAR, Define.length(512)), tableName);//是否允许提前还款
		}
		Map<String, String> columnComments = new HashMap<String, String>();
		columnComments.put("_is_submit", "是否提交审核");
		columnComments.put("_instance_code", "实例编号");
		columnComments.put("_instance_id", "实例Id");
		columnComments.put("_task_name", "当前任务名称");
		columnComments.put("_task_no", "任务编号");
		columnComments.put("_before_user_name", "上一步审批人");
		columnComments.put("_before_complete_time", "上一步审批时间");
		columnComments.put("_after_user_name", "下一步审批人");
		columnComments.put("_task_status", "任务状态");
		columnComments.put("_form_id", "表单Id");
		columnComments.put("_salesman_id", "跟单业务员Id");
		columnComments.put("_is_declaration_again", "是否重新报单");
		columnComments.put("_city_key", "城市Key");
		columnComments.put("_is_allow_submit", "是否允许提交");
		columnComments.put("_is_allow_advance_pay", "是否允许提前还款");
		columnComments.put("_type_id", "是否允许提前还款");
		addComment(tableName, "流程表", columnComments);
	}
	
	public static void addWorkFlowBusinessColumns(String tableName, String tableComment){
		if(!Execute.columnExists("_product_id", tableName)){
			addColumn( Define.column("_product_id", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//关联产品id
		}
		if(!Execute.columnExists("_amt", tableName)){
			addDecimalColumn("_amt", tableName, 16, 4, "_product_id");//借款金额
		}
		if(!Execute.columnExists("_period", tableName)){
			addColumn( Define.column("_period", Define.DataTypes.INTEGER, Define.length(11)), tableName);//借款期限
		}
		if(!Execute.columnExists("_period_unit", tableName)){
			addColumn( Define.column("_period_unit", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//期限单位
		}
		if(!Execute.columnExists("_acct_bank_id", tableName)){
			addColumn( Define.column("_acct_bank_id", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//关联账户银行卡id
		}
		if(!Execute.columnExists("_repay_user_id", tableName)){
			addColumn( Define.column("_repay_user_id", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//关联还款用户id
		}
		if(!Execute.columnExists("_loan_user_id", tableName)){
			addColumn( Define.column("_loan_user_id", Define.DataTypes.VARCHAR, Define.length(64)), tableName);//关联放款用户id
		}
		Map<String, String> columnComments = new HashMap<String, String>();
		columnComments.put("_product_id", "关联产品id");
		columnComments.put("_amt", "借款金额");
		columnComments.put("_period", "借款期限");
		columnComments.put("_period_unit", "期限单位");
		columnComments.put("_acct_bank_id", "关联账户银行卡id");
		columnComments.put("_repay_user_id", "关联还款用户id");
		columnComments.put("_loan_user_id", "关联放款用户id");
		addComment(tableName, tableComment, columnComments);
	}
	
	public static void dropWorkFlowBusinessColumns(String tableName){
		if(Execute.columnExists("_product_id", tableName)){
			dropColumn("_product_id", tableName);
		}
		if(Execute.columnExists("_amt", tableName)){
			dropColumn("_amt", tableName);
		}
		if(Execute.columnExists("_period", tableName)){
			dropColumn("_period", tableName);
		}
		if(Execute.columnExists("_period_unit", tableName)){
			dropColumn("_period_unit", tableName);
		}
	}
	
	public static void dropWorkFlowColumns(String tableName){
		if(Execute.columnExists("_is_submit", tableName)){
			dropColumn("_is_submit", tableName);
		}
		if(Execute.columnExists("_instance_code", tableName)){
			dropColumn("_instance_code", tableName);
		}
		if(Execute.columnExists("_task_name", tableName)){
			dropColumn("_task_name", tableName);
		}
		if(Execute.columnExists("_task_no", tableName)){
			dropColumn("_task_no", tableName);
		}
		if(Execute.columnExists("_before_user_name", tableName)){
			dropColumn("_before_user_name", tableName);
		}
		if(Execute.columnExists("_before_complete_time", tableName)){
			dropColumn("_before_complete_time", tableName);
		}
		if(Execute.columnExists("_after_user_name", tableName)){
			dropColumn("_after_user_name", tableName);
		}
		if(Execute.columnExists("_form_id", tableName)){
			dropColumn("_form_id", tableName);
		}
	}
	/**
	 * EG 
	 * Map<String, String> columnComments = new HashMap<String, String>();
		columnComments.put("_id", "主键");
		columnComments.put("_name", "文件名称");
		columnComments.put("_type", "文件类型");
		columnComments.put("_url", "文件路径");
		columnComments.put("_model", "文件模块");
		columnComments.put("_form_id", "表单ID");
		columnComments.put("_remark", "备注");
		addComment("sys_files", "系统文件", columnComments);
	 * @param tableName
	 * @param tableComment
	 * @param columnComments
	 * @throws Exception
	 */
	public static void addComment(String tableName, String tableComment, Map<String, String> columnComments) {
		Connection con = null;
		try {
			con = 	Configure.getConnection();
			DatabaseMetaData data =  con.getMetaData();
			ResultSet colRet  = data.getColumns(null,"%", tableName,"%");
			while(colRet.next()) { 
				addColumnsComment(tableName, colRet, columnComments);
			}
			addTableComment(tableName, tableComment);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void addComment(String tableName, GeneralColumn column) {
	    String columnType = Define.dataTypeMap.get(column.getColumn().getColumnType()).toString();
	    String columnName = column.getColumn().getColumnName();
	    int datasize = column.getColumn().getLength();
	    Object defaultValue = column.getColumn().getDefaultValue();
	    if(defaultValue != null) {	        
	        addColumnCommnent(tableName, columnName,
	                columnType, datasize, 
	                defaultValue.toString(), column.getComment());
	    } else {
	        addColumnCommnent(tableName, columnName, columnType, datasize, column.getColumn().isNullable(), column.getComment());
	    }
	}
	
	public static void dropAllIndex(){
		Connection con = null;
		ResultSet rs = null;
		try {
			con = 	Configure.getConnection();
			DatabaseMetaData data = con.getMetaData();
			rs = data.getTables(null, "%", null, new String[]{"TABLE"});
			while(rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				if(Execute.columnExists("_index", tableName)){
					dropColumn("_index", tableName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(null != rs){
				try {rs.close();} catch (Exception e2) {}
			}
			if(null != con){
				try {con.close();} catch (Exception e2) {}
			}
		}finally{
			if(null != rs){
				try {rs.close();} catch (Exception e2) {}
			}
			if(null != con){
				try {con.close();} catch (Exception e2) {}
			}
		}
	}
	
	public static void executeSql(String sql){
		Connection con = null;
		PreparedStatement statement = null;
		try {
			con = 	Configure.getConnection();
			statement = con.prepareStatement(sql);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			if(null != statement){
				try {statement.close();} catch (Exception e2) {}
			}
			if(null != con){
				try {con.close();} catch (Exception e2) {}
			}
		}finally{
			if(null != statement){
				try {statement.close();} catch (Exception e2) {}
			}
			if(null != con){
				try {con.close();} catch (Exception e2) {}
			}
		}
	}
}
