package com.beidouiot.alllink.community.common.base.utils;

/**
 * 
 *
 * @Description 实体配置
 * @author longww
 * @date 2021年4月11日
 */
public class EntityConstants {

	private EntityConstants() {}
	
	/**
	 * 基本字段
	 */
	public static final String ID = "id";
	public static final String ID_COLUMN_DEFINITION = "bigint comment '主键id'";
	public static final String CREATE_DATE = "create_date";
	public static final String CREATE_DATE_COLUMN_DEFINITION = "datetime comment '创建时间'";
	public static final String UPDATED_DATE = "updated_date";
	public static final String UPDATED_DATE_COLUMN_DEFINITION = "datetime comment '修改时间'";
	public static final String CREATED_BY = "created_by";
	public static final String CREATED_BY_COLUMN_DEFINITION = "varchar(100) comment '创建人（用户登录名）'";
	public static final String UPDATED_BY = "updated_by";
	public static final String UPDATED_BY_COLUMN_DEFINITION = "varchar(100) comment '修改人（用户登录名）'";
	public static final String DELETE_FLAG = "delete_flag";
	public static final String DELETE_FLAG_COLUMN_DEFINITION = "bit comment '删除标志'";
	public static final String OPTLOCK_VERSION = "opt_lock_version";
	public static final String OPTLOCK_VERSION_COLUMN_DEFINITION = "int comment '乐观锁版本号'";
	public static final String NAME = "name";
	public static final String CODE = "code";
	public static final String ADDR = "addr";
	public static final String DESCR = "descr";
	public static final String EMAIL = "email";
	public static final String MOBILE = "mobile";
	public static final String MOBILE_COLUMN_DEFINITION= "varchar(11) comment '手机号'";
	public static final String ZIP_CODE = "zip_code";
	public static final String ZIP_CODE_COLUMN_DEFINITION= "varchar(6) comment '邮编'";
	public static final String PHONE = "phone";
	public static final String PHONE_COLUMN_DEFINITION= "varchar(20) comment '电话'";
	public static final String SORT_NO = "sort_no";
	public static final String SORT_NO_COLUMN_DEFINITION= "int comment '排序号'";
	public static final String PID = "pid";
	public static final String PID_COLUMN_DEFINITION= "bigint comment '父id'";
	
	/**
	 * 业务公共字段
	 */
	public static final String INDUSTRY_ID = "industry_id";
	public static final String INDUSTRY_ID_COLUMN_DEFINITION= "bigint comment '所属行业id'";
	public static final String TENANT_ID = "tenant_id";
	public static final String TENANT_ID_COLUMN_DEFINITION= "bigint comment '所属租户id'";
	public static final String CUSTOMER_ID = "customer_id";
	public static final String CUSTOMER_ID_COLUMN_DEFINITION= "bigint comment '所属客户id'";
	public static final String PARK_ID = "park_id";
	public static final String PARK_ID_COLUMN_DEFINITION= "bigint comment '所属园区id，可以是园区的任意一个节点'";
	public static final String CITY = "city";
	public static final String CITY_COLUMN_DEFINITION= "varchar(1000) comment '所在城市，省市区信息，采用json格式存储'";
	public static final String SYSTEM_CODE = "system_code";
	public static final String SYSTEM_CODE_COLUMN_DEFINITION= "varchar(50) comment '所属系统编号'";
	public static final String WEIXIN = "weixin";
	public static final String WEIXIN_COLUMN_DEFINITION= "varchar(50) comment '微信号'";
	public static final String QQ = "qq_no";
	public static final String QQ_COLUMN_DEFINITION= "varchar(50) comment 'QQ号'";
	public static final String IDENTITY_NO = "identity_no";
	public static final String IDENTITY_NO_COLUMN_DEFINITION= "varchar(100) comment '证件号'";
	public static final String IDENTITY_TYPE = "identity_type";
	public static final String IDENTITY_TYPE_COLUMN_DEFINITION= "varchar(100) comment '证件类型，枚举（身份证、军官证、士官证、士兵证、台胞证、护照、绿卡）'";


	
	/**
	 * 行业信息表
	 */
	public static final String INDUSTRY = "industry";
	public static final String INDUSTRY_NAME_COLUMN_DEFINITION= "varchar(100) comment '行业名称'";
	public static final String INDUSTRY_CODE_COLUMN_DEFINITION= "varchar(100) comment '行业编号'";
	public static final String INDUSTRY_DESCRIPTION_COLUMN_DEFINITION= "varchar(200) comment '行业描述'";
	
	/**
	 * 用户表
	 */
	public static final String USER = "user";
	public static final String USERNAME = "username";
	public static final String LOGIN_NAME_COLUMN_DEFINITION = "varchar(20) comment '登录名'";
	public static final String PASSWORD = "password";
	public static final String PASSWORD_COLUMN_DEFINITION = "varchar(256) comment '密码'";
	public static final String EMAIL_COLUMN_DEFINITION = "varchar(100) comment '邮箱'";
	public static final String NAME_COLUMN_DEFINITION = "varchar(200) comment '姓名'";
	public static final String NICKNAME = "nickname";
	public static final String NICKNAME_COLUMN_DEFINITION= "varchar(50) comment '昵称'";
	public static final String SEX = "sex";
	public static final String SEX_COLUMN_DEFINITION= "int comment '性别,枚举型（1男、2女、3保密）'";
	public static final String USER_STATUS = "status";
	public static final String USER_STATUS_COLUMN_DEFINITION= "bit comment '状态(是否禁用)，状态值：启用（0），禁用（1）'";
	public static final String USER_TYPE = "user_type";
	public static final String USER_TYPE_COLUMN_DEFINITION= "varchar(20) comment '用户类型，枚举型（平台管理员、租户管理员、客户管理、普通用户）'";
	public static final String USER_DESCR_COLUMN_DEFINITION= "varchar(200) comment '用户描述'";
	public static final String USER_CODE_COLUMN_DEFINITION= "varchar(50) comment '用户工号/学号等'";
	public static final String HEAD_PORTRAIT = "head_portrait";
	public static final String HEAD_PORTRAIT_COLUMN_DEFINITION= "varchar(512) comment '用户头像，存放图片文件base64后的值'";
	
	/**
	 * 租户信息表
	 */
	public static final String TENANT = "tenant";
	public static final String TENANT_NAME_COLUMN_DEFINITION = "varchar(100) comment '租户名称'";
	public static final String TENANT_ADDR_COLUMN_DEFINITION = "varchar(500) comment '租户详细地址'";
	public static final String TENANT_DESCR_COLUMN_DEFINITION = "varchar(500) comment '租户描述'";
	public static final String LINKMAN = "linkman";
	public static final String TENANT_LINKMAN_COLUMN_DEFINITION = "varchar(50) comment '租户联系人姓名'";
	public static final String TENANT_EMAIL_COLUMN_DEFINITION = "varchar(50) comment '联系人邮箱,为选填项,邮箱不能重复'";
	public static final String TENANT_STATUS = "status";
	public static final String TENANT_STATUS_COLUMN_DEFINITION = "bit comment '租户状态，状态有：启用（0）、禁用（1）。租户禁用后，租户下的所有用户不能登录、不能做任何操作'";

	/**
	 * 企业客户信息表
	 */
	public static final String CUSTOMER = "customer";
	public static final String CUSTOMER_NAME_COLUMN_DEFINITION = "varchar(100) comment '客户名称'";
	public static final String CUSTOMER_DESCR_COLUMN_DEFINITION = "varchar(500) comment '客户描述'";
	public static final String CUSTOMER_ADDR_COLUMN_DEFINITION = "varchar(200) comment '客户详细地址'";
	
	/**
	 * 园区/小区信息表
	 */
	public static final String PARK = "park";
	public static final String PARK_NAME_COLUMN_DEFINITION = "varchar(200) comment '园区/小区名称'";
	public static final String PARK_TYPE = "type";
	public static final String PARK_TYPE_COLUMN_DEFINITION = "varchar(20) comment '园区/小区节点类型，枚举（园区/小区、楼栋、单元、楼层、房间）'";
	public static final String PARK_SORT_NO_COLUMN_DEFINITION = "varchar(20) comment '节点排序号'";
	public static final String PARK_DESCR_COLUMN_DEFINITION = "varchar(200) comment '节点描述'";
	
	/**
	 * 角色信息表
	 */
	public static final String ROLE = "role";
	public static final String ROLE_NAME_COLUMN_DEFINITION = "varchar(20) comment '角色名称'";
	public static final String ROLE_CODE_COLUMN_DEFINITION = "varchar(20) comment '角色编号，不能重复，由系统自动生成'";
	public static final String ROLE_DESCR_COLUMN_DEFINITION = "varchar(200) comment '角色描述'";
	
}
