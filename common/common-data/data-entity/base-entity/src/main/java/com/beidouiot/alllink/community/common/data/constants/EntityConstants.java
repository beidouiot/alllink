package com.beidouiot.alllink.community.common.data.constants;

/**
 * 
 *
 * @Description 实体常量
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
	public static final String DELETE_FLAG_COLUMN_DEFINITION = "bit comment '删除标志' default 0";
	public static final String OPTLOCK_VERSION = "opt_lock_version";
	public static final String OPTLOCK_VERSION_COLUMN_DEFINITION = "int comment '乐观锁版本号'";
	public static final String NAME = "name";
	public static final String CHINESE_NAME = "chinese_name";
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
	public static final String SORT_NO_COLUMN_DEFINITION= "int comment '排序号'  default 1";
	public static final String PID = "pid";
	public static final String PID_COLUMN_DEFINITION= "bigint comment '父id'";
	public static final String STATUS = "status";
	public static final String REMARK = "remark";
	public static final String REMARK_COLUMN_DEFINITION = "varchar(200) comment '备注'";
	public static final String ACTIVATION_TIME = "activation_time";
	public static final String ACTIVATION_TIME_COLUMN_DEFINITION = "datetime comment '激活时间'";
	public static final String ENABLE_FLAG = "enable_flag";
	public static final String ENABLE_FLAG_COLUMN_DEFINITION = "bit comment '是否启用'";
	public static final String LEVEL = "level";
	public static final String LEVEL_COLUMN_DEFINITION = "int comment '所在树结构层数'";
	public static final String LEAF_FLAG = "leaf_flag";
	public static final String LEAF_FLAG_COLUMN_DEFINITION = "bit comment '是否为叶节点'";
	
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
	public static final String PROJECT_ID = "park_id";
	public static final String PROJECT_ID_COLUMN_DEFINITION= "bigint comment '所属项目id'";
	public static final String CITY = "city";
	public static final String CITY_COLUMN_DEFINITION= "json comment '所在城市，省市区信息，采用json格式存储'";
	public static final String SYSTEM_CODE = "system_code";
	public static final String SYSTEM_CODE_COLUMN_DEFINITION= "varchar(50) comment '所属系统编号'";
	public static final String WEIXIN = "weixin";
	public static final String WEIXIN_COLUMN_DEFINITION= "varchar(50) comment '微信号'";
	public static final String QQ = "qq_no";
	public static final String QQ_COLUMN_DEFINITION = "varchar(50) comment 'QQ号'";
	public static final String IDENTITY_NO = "identity_no";
	public static final String IDENTITY_NO_COLUMN_DEFINITION = "varchar(100) comment '证件号'";
	public static final String IDENTITY_TYPE = "identity_type";
	public static final String IDENTITY_TYPE_COLUMN_DEFINITION = "varchar(100) comment '证件类型，枚举（身份证、军官证、士官证、士兵证、台胞证、护照、绿卡）'";
	public static final String USER_ID = "user_id";
	public static final String USER_ID_COLUMN_DEFINITION = "bigint comment '用户id'";
	public static final String ROLE_ID = "role_id";
	public static final String ROLE_ID_COLUMN_DEFINITION = "bigint comment '角色id'";
	public static final String MENU_ID = "menu_id";
	public static final String MENU_ID_COLUMN_DEFINITION = "bigint comment '菜单id'";
	public static final String DEPT_ID = "dept_id";
	public static final String DEPT_ID_COLUMN_DEFINITION = "bigint comment '部门id'";
	public static final String POSITION_ID = "position_id";
	public static final String POSITION_ID_COLUMN_DEFINITION = "bigint comment '职位id'";
	public static final String PRODUCT_TYPE_ID = "product_type_id";
	public static final String PRODUCT_TYPE_ID_COLUMN_DEFINITION = "bigint comment '产品类别id'";
	public static final String PRODUCT_ID = "product_id";
	public static final String PRODUCT_ID_COLUMN_DEFINITION = "bigint comment '产品id'";
	public static final String INDUSTRY_TYPE_ID = "industry_type_id";
	public static final String INDUSTRY_TYPE_ID_COLUMN_DEFINITION = "bigint comment '行业类别id'";
	public static final String STANDARD_PROPERTY_MODEL_ID = "standard_property_model_id";
	public static final String STANDARD_PROPERTY_MODEL_ID_COLUMN_DEFINITION = "bigint comment '标准属性模型id'";
	public static final String STANDARD_EVENT_MODEL_ID = "standard_event_model_id";
	public static final String STANDARD_EVENT_MODEL_ID_COLUMN_DEFINITION = "bigint comment '标准事件模型id'";
	public static final String STANDARD_COMMAND_MODEL_ID = "standard_command_model_id";
	public static final String STANDARD_COMMAND_MODEL_ID_COLUMN_DEFINITION = "bigint comment '标准指令模型id'";
	public static final String PRODUCT_PROPERTY_MODEL_ID = "product_property_model_id";
	public static final String PRODUCT_PROPERTY_MODEL_ID_COLUMN_DEFINITION = "bigint comment '产品属性模型id'";
	public static final String PRODUCT_EVENT_MODEL_ID = "product_event_model_id";
	public static final String PRODUCT_EVENT_MODEL_ID_COLUMN_DEFINITION = "bigint comment '产品事件模型id'";
	public static final String PRODUCT_COMMAND_MODEL_ID = "product_command_model_id";
	public static final String PRODUCT_COMMAND_MODEL_ID_COLUMN_DEFINITION = "bigint comment '产品指令模型id'";
	public static final String DEVICE_ID = "device_id";
	public static final String DEVICE_ID_COLUMN_DEFINITION = "bigint comment '设备id'";
	
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
	public static final String USER_STATUS_COLUMN_DEFINITION= "bit comment '状态(是否禁用)，状态值：启用（0），禁用（1）'  default 0";
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
	public static final String TENANT_STATUS_COLUMN_DEFINITION = "bit comment '租户状态，状态有：启用（0）、禁用（1）。租户禁用后，租户下的所有用户不能登录、不能做任何操作'  default 0";

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
	public static final String ROLE_CODE_COLUMN_DEFINITION = "varchar(50) comment '角色编号，不能重复，由系统自动生成'";
	public static final String ROLE_DESCR_COLUMN_DEFINITION = "varchar(200) comment '角色描述'";
	
	/**
	 * 用户角色关系表
	 */
	public static final String ROLE_USER = "role_user";
	
	/**
	 * 菜单/功能表
	 */
	public static final String MENU = "menu";
	public static final String MENU_NAME_COLUMN_DEFINITION = "varchar(100) comment '菜单/功能名称'";
	public static final String MENU_CODE_COLUMN_DEFINITION = "varchar(50) comment '菜单/功能编号，不能重复，由系统自动生成'";
	public static final String MENU_ADDR_COLUMN_DEFINITION = "varchar(200) comment '菜单/功能地址'";
	public static final String ICON = "icon";
	public static final String MENU_ICON_COLUMN_DEFINITION = "varchar(200) comment '菜单/功能图标'";
	public static final String TYPE = "type";
	public static final String MENU_TYPE_COLUMN_DEFINITION = "varchar(20) comment '菜单/功能类型，枚举型（web菜单、tab、按钮，app菜单）'";
	public static final String HAS_BUTTON = "has_button";
	public static final String HAS_BUTTON_COLUMN_DEFINITION = "bit comment '是否有按钮' default 0";

	
	
	
	/**
	 * 角色菜单关系表
	 */
	public static final String ROLE_MENU = "role_menu";
	
	/**
	 * 组织/部门表
	 */
	public static final String DEPARTMENT = "department";
	public static final String DEPARTMENT_NAME_COLUMN_DEFINITION = "varchar(100) comment '部门/组织名称'";
	public static final String DEPARTMENT_CODE_COLUMN_DEFINITION = "varchar(50) comment '部门/组织编号，不能重复，由系统自动生成'";
	public static final String DEPARTMENT_DESCR_COLUMN_DEFINITION = "varchar(500) comment '部门/组织描述'";
	public static final String DEPARTMENT_TYPE = "dept_type";
	public static final String DEPARTMENT_TYPE_COLUMN_DEFINITION = "varchar(20) comment '部门/组织类型'";
	
	/**
	 * 组织/部门用户关系表
	 */
	public static final String DEPT_USER = "dept_user";
	
	/**
	 * 职位表
	 */
	public static final String POSITION = "position";
	public static final String POSITION_NAME_COLUMN_DEFINITION = "varchar(50) comment '职位名称'";
	public static final String POSITION_CODE_COLUMN_DEFINITION = "varchar(10) comment '职位编号'";
	public static final String POSITION_DESCR_COLUMN_DEFINITION = "varchar(200) comment '职位描述'";
	
	/**
	 * 用户职位关系表
	 */
	public static final String USER_POSITION = "user_position";
	
	/**
	 * 产品类别
	 */
	public static final String PRODUCT_TYPE = "product_type";
	public static final String PRODUCT_TYPE_NAME_COLUMN_DEFINITION = "varchar(100) comment '产品类别名称'";
	public static final String PRODUCT_TYPE_CODE_COLUMN_DEFINITION = "varchar(100) comment '产品类别编号'";
	public static final String PRODUCT_TYPE_DESCR_COLUMN_DEFINITION = "varchar(200) comment '产品类别描述'";
	
	
	/**
	 * 行业类别
	 */
	public static final String INDUSTRY_TYPE = "industry_type";
	public static final String INDUSTRY_TYPE_NAME_COLUMN_DEFINITION = "varchar(100) comment '行业类别名称'";
	public static final String INDUSTRY_TYPE_CODE_COLUMN_DEFINITION = "varchar(100) comment '行业类别编号'";
	public static final String INDUSTRY_TYPE_DESCR_COLUMN_DEFINITION = "varchar(200) comment '行业类别描述'";

	
	/**
	 * 行业类别产品列表关系表
	 */
	public static final String INDUSTRY_TYPE_PRODUCT_TYPE = "product_type_industry_type";
	
	/**
	 * 产品
	 */
	public static final String PRODUCT = "product";
	public static final String PRODUCT_NAME_COLUMN_DEFINITION = "varchar(100) comment '产品名称'";
	public static final String PRODUCT_CHINESE_NAME_COLUMN_DEFINITION = "varchar(20) comment '产品中文名称'";
	public static final String PRODUCT_DEVICE_TYPE = "product_device_type";
	public static final String PRODUCT_DEVICE_TYPE_COLUMN_DEFINITION = "int comment '产品设备类型，枚举（1网关设备、2网关子设备、3独立设备），如果为网关设备或者独立设备，则必须确认产品接入方式（直接接入、边缘节点接入）'";
	public static final String PRODUCT_ACCESS_MODE = "product_access_mode";
	public static final String PRODUCT_ACCESS_MODE_COLUMN_DEFINITION = "int comment '产品接入方式，枚举（1直接接入，2边缘节点接入），直接接入--设备通过网络直接接入平台，边缘节点接入--设备首先接入边缘服务器节点并通过边缘节点间接接入。'";
	public static final String PRODUCT_COMMUNICATION_PROTOCOL = "communication_protocol";
	public static final String PRODUCT_COMMUNICATION_PROTOCOL_COLUMN_DEFINITION = "varchar(50) comment '通信协议，枚举（MQTT、HTTP、CoAP等）'";
	public static final String PRODUCT_NETWORKING_PROTOCOL = "networking_protocol";
	public static final String PRODUCT_NETWORKING_PROTOCOL_COLUMN_DEFINITION = "varchar(50) comment '组网协议，枚举（NB-IoT、LoRa、433、Wi-Fi、BLE、ZigBee、Z-Wave、485、以太网，其他）'";
	public static final String PRODUCT_DESCR_COLUMN_DEFINITION = "varchar(200) comment '产品描述'";
	public static final String PRODUCT_COPY_FLAG = "copy_flag";
	public static final String PRODUCT_COPY_FLAG_COLUMN_DEFINITION = "bit comment '是否复制标准物模型，复制标准物模型，则将当前标准物模型完全复制到产品，不跟随后续标准物模型的修改而改动' default 1";
	public static final String PRODUCT_STANDARD_MODEL = "standard_model";
	public static final String PRODUCT_STANDARD_MODEL_COLUMN_DEFINITION = "varchar(8000) comment '标准物模型，JSON格式'";
	
	/**
	 * (产品类别)标准物模型(属性 事件 命令)
	 * 
	 */
	public static final String STANDARD_MODEL_DATA_TYPE = "data_type";
	public static final String STANDARD_MODEL_DATA_TYPE_COLUMN_DEFINITION = "varchar(50) comment '数据类型，枚举（数值型、枚举型、布尔型、字符型等）'";
	public static final String STANDARD_MODEL_DATA_SPECS = "data_specs";
	public static final String STANDARD_MODEL_DATA_SPECS_COLUMN_DEFINITION = "json comment '数据类型数据定义'";
	public static final String STANDARD_MODEL_ACCESS_TYPE = "access_type";
	public static final String STANDARD_MODEL_ACCESS_TYPE_COLUMN_DEFINITION = "int comment '访问类型，枚举（1只读，2:读写）'";
	public static final String STANDARD_MODEL_STATUS_COLUMN_DEFINITION = "bit comment '发布状态，0未发布，1发布' default 0";
	public static final String STANDARD_MODEL_REMARK = "remark";
	public static final String STANDARD_MODEL_REMARK_COLUMN_DEFINITION = "varchar(200) comment '备注'";
	public static final String STANDARD_MODEL_PARAM_NAME_COLUMN_DEFINITION = "varchar(100) comment '参数名称'";
	public static final String STANDARD_MODEL_PARAM_CODE_COLUMN_DEFINITION = "varchar(50) comment '参数标识符'";
	public static final String STANDARD_MODEL_PARAM_DIRECTION = "direction";
	public static final String STANDARD_MODEL_PARAM_DIRECTION_COLUMN_DEFINITION = "int comment '参数方向：1入参，2出参' default 1";
	public static final String STANDARD_COMMOND_MODEL_PARAMS = "cmd_params";
	public static final String STANDARD_COMMOND_MODEL_PARAMS_COLUMN_DEFINITION = "varchar(600) comment '物模型参数集（物模型参数ID集），多ID以逗号隔开'";
	
	/**
	 *   标准属性模型
	 */
	public static final String STANDARD_PROPERTY_MODEL = "standard_property_model";
	public static final String STANDARD_PROPERTY_MODEL_NAME_COLUMN_DEFINITION = "varchar(100) comment '属性名称'";
	public static final String STANDARD_PROPERTY_MODEL_CODE_COLUMN_DEFINITION = "varchar(50) comment '属性标识符'";

	/**
	 * 标准属性模型参数
	 */
	public static final String STANDARD_PROPERTY_MODEL_PARAM = "standard_property_model_param";
	
	/**
	 * 标准事件模型
	 */
	public static final String STANDARD_EVENT_MODEL = "standard_event_model";
	public static final String STANDARD_EVENT_MODEL_NAME_COLUMN_DEFINITION = "varchar(100) comment '事件名称'";
	public static final String STANDARD_EVENT_MODEL_CODE_COLUMN_DEFINITION = "varchar(50) comment '事件标识符'";
	public static final String STANDARD_EVENT_MODEL_EVENT_TYPE = "event_type";
	public static final String STANDARD_EVENT_MODEL_EVENT_TYPE_COLUMN_DEFINITION = "int comment '事件类型：枚举（1信息，2告警，3故障）'";

	/**
	 * 标准事件模型参数
	 */
	public static final String STANDARD_EVENT_MODEL_PARAM = "standard_event_model_param";
	
	/**
	 * 标准指令模型
	 */
	public static final String STANDARD_COMMOND_MODEL = "standard_commond_model";
	public static final String STANDARD_COMMOND_MODEL_NAME_COLUMN_DEFINITION = "varchar(100) comment '指令名称'";
	public static final String STANDARD_COMMOND_MODEL_CODE_COLUMN_DEFINITION = "varchar(50) comment '指令标识符'";
	public static final String STANDARD_COMMOND_MODEL_CALL_MODE = "call_mode";
	public static final String STANDARD_COMMOND_MODEL_CALL_MODE_COLUMN_DEFINITION = "int comment '调用方式：枚举（1异步，2同步）'";
	
	/**
	 * 标准指令模型参数
	 */
	public static final String STANDARD_COMMAND_MODEL_PARAM = "standard_command_model_param";
	
	/**
	 * 产品物模型
	 */
	public static final String PRODUCT_MODEL = "product_model";
	
	/**
	 * 产品属性物模型
	 */
	public static final String PRODUCT_PROPERTY_MODEL = "product_property_model";
	
	/**
	 * 产品属性模型参数
	 */
	public static final String PRODUCT_PROPERTY_MODEL_PARAM = "product_property_model_param";
	
	/**
	 * 产品事件物模型
	 */
	public static final String PRODUCT_EVENT_MODEL = "product_event_model";
	
	/**
	 * 产品指令物模型
	 */
	public static final String PRODUCT_COMMAND_MODEL = "product_command_model";
	
	/**
	 * 产品事件模型参数
	 */
	public static final String PRODUCT_EVENT_MODEL_PARAM = "product_event_model_param";
	
	/**
	 * 产品物模型历史版本
	 */
	public static final String PRODUCT_MODEL_VERSION = "product_model_version";
	public static final String PRODUCT_MODEL_VERSION_MODEL_CONTENT = "model_content";
	public static final String PRODUCT_MODEL_VERSION_MODEL_CONTENT_COLUMN_DEFINITION = "json comment '物模型内容,Json格式'";
	public static final String PRODUCT_MODEL_VERSION_USE_FLAG = "use_flag";
	public static final String PRODUCT_MODEL_VERSION_USE_FLAG_COLUMN_DEFINITION = "bit comment '当前使用标志，0不使用，1使用。一个产品只能有一个在使用。物模型发布后默认为使用该发布物模型。其他历史物模型设置为当前不使用'";
	public static final String PRODUCT_MODEL_VERSION_VERSION_NUMBER = "version_number";
	public static final String PRODUCT_MODEL_VERSION_VERSION_NUMBER_COLUMN_DEFINITION = "bigint comment '物模型版本号'";
	
	/**
	 * 设备信息
	 */
	public static final String DEVICE_INFO = "device_info";
	public static final String DEVICE_INFO_NAME_COLUMN_DEFINITION = "varchar(100) comment '设备名称'";
	public static final String DEVICE_INFO_NICKNAME = "nickname";
	public static final String DEVICE_INFO_NICKNAME_COLUMN_DEFINITION = "varchar(100) comment '设备昵称'";
	public static final String DEVICE_INFO_DEVICE_SN = "device_sn";
	public static final String DEVICE_INFO_DEVICE_SN_COLUMN_DEFINITION = "varchar(100) comment '设备唯一标识号'";
	public static final String DEVICE_INFO_NETWORKING_PROTOCOL = "networking_protocol";
	public static final String DEVICE_INFO_NETWORKING_PROTOCOL_COLUMN_DEFINITION = "varchar(50) comment '设备组网协议，枚举（NB-IoT、LoRa、433、Wi-Fi、BLE、ZigBee、Z-Wave、485、以太网，其他）'";
	public static final String DEVICE_INFO_EQUIPMENT_MODEL = "equipment_model";
	public static final String DEVICE_INFO_EQUIPMENT_MODEL_COLUMN_DEFINITION = "varchar(50) comment '设备型号'";
	public static final String DEVICE_INFO_FIRMWARE_NAME = "firmware_name";
	public static final String DEVICE_INFO_FIRMWARE_NAME_COLUMN_DEFINITION = "varchar(100) comment '固件版本名称'";
	public static final String DEVICE_INFO_FIRMWARE_VERSION = "firmware_version";
	public static final String DEVICE_INFO_FIRMWARE_VERSION_COLUMN_DEFINITION = "varchar(50) comment '固件版本号'";
	public static final String DEVICE_INFO_HARDWARE_VERSION = "hardware_version";
	public static final String DEVICE_INFO_HARDWARE_VERSION_COLUMN_DEFINITION = "varchar(50) comment '硬件版本'";
	public static final String DEVICE_INFO_LAST_LINK_TIME = "last_link_time";
	public static final String DEVICE_INFO_LAST_LINK_TIME_COLUMN_DEFINITION = "datetime comment '最后连接时间（或上传数据时间）'";
	public static final String DEVICE_INFO_ONLINE_STATUS = "online_status";
	public static final String DEVICE_INFO_ONLINE_STATUS_COLUMN_DEFINITION = "int comment '在线状态，枚举（在线、离线、激活、未激活、未知）'";
	public static final String DEVICE_INFO_LONGITUDE = "longitude";
	public static final String DEVICE_INFO_LONGITUDE_COLUMN_DEFINITION = "varchar(50) comment '安装位置经度'";
	public static final String DEVICE_INFO_LATITUDE = "latitude";
	public static final String DEVICE_INFO_LATITUDE_COLUMN_DEFINITION = "varchar(50) comment '安装位置纬度'";
	public static final String DEVICE_INFO_POSITION_DETAIL = "position_detail";
	public static final String DEVICE_INFO_POSITION_DETAIL_COLUMN_DEFINITION = "varchar(300) comment '详细位置（安装位置）'";
	public static final String DEVICE_INFO_EXPIRY_WARRANTY_DATE = "expiry_warranty_date";
	public static final String DEVICE_INFO_EXPIRY_WARRANTY_DATE_COLUMN_DEFINITION = "date comment '过保日期'";
	public static final String DEVICE_INFO_DEVICE_ACCESS_TYPE = "device_access_type";
	public static final String DEVICE_INFO_DEVICE_ACCESS_TYPE_COLUMN_DEFINITION = "varchar(20) comment '设备接入类型，枚举（直接接入，第三方平台接入）'";
	public static final String DEVICE_INFO_GATEWAY_DEVICE_ID = "gateway_device_id";
	public static final String DEVICE_INFO_GATEWAY_DEVICE_ID_COLUMN_DEFINITION = "bigint comment '所属网关设备，通过网关设备接入的传感器，是通过哪个网关设备接入'";
	public static final String DEVICE_INFO_GATEWAY_FLAG = "gateway_flag";
	public static final String DEVICE_INFO_GATEWAY_FLAG_COLUMN_DEFINITION = "bit comment '是否网关设备'";
	public static final String DEVICE_INFO_THIRD_PLATFORM_ID = "third_platform_id";
	public static final String DEVICE_INFO_THIRD_PLATFORM_ID_COLUMN_DEFINITION = "bigint comment '所属第三方接入平台'";
	public static final String DEVICE_INFO_LASTED_UPGRADE_TIME = "lasted_upgrade_time";
	public static final String DEVICE_INFO_LASTED_UPGRADE_TIME_COLUMN_DEFINITION = "datetime comment '所属第三方接入平台'";
	public static final String DEVICE_INFO_FIRMWARE_UPGRADE_QUANTITY = "firmware_upgrade_quantity";
	public static final String DEVICE_INFO_FIRMWARE_UPGRADE_QUANTITY_COLUMN_DEFINITION = "int comment '固件升级次数'";
	
	/**
	 * 设备固件升级历史记录
	 */
	public static final String DEVICE_UPGRADE_INFO = "device_upgrade_info";
	public static final String DEVICE_UPGRADE_INFO_UPGRADE_LOGS = "upgrade_logs";
	public static final String DEVICE_UPGRADE_INFO_UPGRADE_LOGS_COLUMN_DEFINITION = "varchar(5000) comment '升级日志'";
	public static final String DEVICE_UPGRADE_INFO_UPGRADE_TIME = "upgrade_time";
	public static final String DEVICE_UPGRADE_INFO_UPGRADE_TIME_COLUMN_DEFINITION = "datetime comment '固件升级时间'";

	public static final String DEVICE_NEW_DATA = "device_new_data";
	public static final String DEVICE_NEW_DATA_MODEL_CODE = "model_code";
	public static final String DEVICE_NEW_DATA_MODEL_CODE_COLUMN_DEFINITION = "varchar(50) comment '物模型标识'";
	public static final String DEVICE_NEW_DATA_MODEL_TYPE = "model_type";
	public static final String DEVICE_NEW_DATA_MODEL_TYPE_COLUMN_DEFINITION = "int comment '物模型类型(属性模型：1，事件模型：2，指令模型：3)'";
	public static final String DEVICE_NEW_DATA_RECEIVED_DATA = "received_data";
	public static final String DEVICE_NEW_DATA_RECEIVED_DATA_COLUMN_DEFINITION = "varchar(2000) comment '物模型数据'";
}
