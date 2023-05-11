CREATE DATABASE `iot_product_center` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- iot_product_center.industry_type definition

CREATE TABLE `industry_type` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '行业类别名称',
  `code` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '行业类别编号',
  `descr` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '行业类别描述',
  `sort_no` int DEFAULT '1' COMMENT '排序号',
  `pid` bigint DEFAULT NULL COMMENT '父id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product definition

CREATE TABLE `product` (
  `id` bigint NOT NULL COMMENT '主键id',
  `chinese_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品中文名称',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名称',
  `product_device_type` int NOT NULL COMMENT '产品设备类型，枚举（1网关设备、2网关子设备、3独立设备），如果为网关设备或者独立设备，则必须确认产品接入方式（直接接入、边缘节点接入）',
  `product_access_mode` int DEFAULT NULL COMMENT '产品接入方式，枚举（1直接接入，2边缘节点接入），直接接入--设备通过网络直接接入平台，边缘节点接入--设备首先接入边缘服务器节点并通过边缘节点间接接入。',
  `communication_protocol` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '通信协议，枚举（MQTT、HTTP、CoAP等）',
  `networking_protocol` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '组网协议，枚举（NB-IoT、LoRa、433、Wi-Fi、BLE、ZigBee、Z-Wave、485、以太网，其他）',
  `descr` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品描述',
  `copy_flag` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否复制标准物模型，复制标准物模型，则将当前标准物模型完全复制到产品，不跟随后续标准物模型的修改而改动',
  `product_type_id` bigint NOT NULL COMMENT '产品类别id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  `tenant_id` bigint DEFAULT NULL COMMENT '所属租户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_command_model definition

CREATE TABLE `product_command_model` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '指令名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '指令标识符',
  `call_mode` int NOT NULL COMMENT '调用方式：枚举（1异步，2同步）',
  `cmd_params` varchar(600) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '物模型参数集（物模型参数ID集），多ID以逗号隔开',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_event_model definition

CREATE TABLE `product_event_model` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件标识符',
  `event_type` int NOT NULL COMMENT '事件类型：枚举（1信息，2告警，3故障）',
  `cmd_params` varchar(600) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '物模型参数集（物模型参数ID集），多ID以逗号隔开',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_event_model_param definition

CREATE TABLE `product_event_model_param` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数标识符',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据类型，枚举（数值型、枚举型、布尔型、字符型等）',
  `data_specs` json DEFAULT NULL COMMENT '数据类型数据定义',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `direction` int NOT NULL DEFAULT '1' COMMENT '参数方向：1入参，2出参',
  `product_event_model_id` bigint NOT NULL COMMENT '产品事件模型id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_model_version definition

CREATE TABLE `product_model_version` (
  `id` bigint NOT NULL COMMENT '主键id',
  `model_content` json NOT NULL COMMENT '物模型内容,Json格式',
  `use_flag` bit(1) NOT NULL COMMENT '当前使用标志，0不使用，1使用。一个产品只能有一个在使用。物模型发布后默认为使用该发布物模型。其他历史物模型设置为当前不使用',
  `version_number` bigint NOT NULL COMMENT '物模型版本号',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_property_model definition

CREATE TABLE `product_property_model` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '属性名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '属性标识符',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据类型，枚举（数值型、枚举型、布尔型、字符型等）',
  `data_specs` json DEFAULT NULL COMMENT '数据类型数据定义',
  `access_type` int NOT NULL COMMENT '访问类型，枚举（1只读，2:读写）',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_property_model_param definition

CREATE TABLE `product_property_model_param` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数标识符',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据类型，枚举（数值型、枚举型、布尔型、字符型等）',
  `data_specs` json DEFAULT NULL COMMENT '数据类型数据定义',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `direction` int NOT NULL DEFAULT '1' COMMENT '参数方向：1入参，2出参',
  `product_property_model_id` bigint NOT NULL COMMENT '产品属性模型id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_type definition

CREATE TABLE `product_type` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品类别名称',
  `code` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品类别编号',
  `descr` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '产品类别描述',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_type_industry_type definition

CREATE TABLE `product_type_industry_type` (
  `industry_type_id` bigint NOT NULL COMMENT '行业类别id',
  `product_type_id` bigint NOT NULL COMMENT '产品类别id',
  PRIMARY KEY (`industry_type_id`,`product_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_command_model_param definition

CREATE TABLE `standard_command_model_param` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数标识符',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据类型，枚举（数值型、枚举型、布尔型、字符型等）',
  `data_specs` json DEFAULT NULL COMMENT '数据类型数据定义',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `direction` int NOT NULL DEFAULT '1' COMMENT '参数方向：1入参，2出参',
  `product_command_model_id` bigint NOT NULL COMMENT '产品指令模型id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  `standard_command_model_id` bigint NOT NULL COMMENT '标准指令模型id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_commond_model definition

CREATE TABLE `standard_commond_model` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '指令名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '指令标识符',
  `call_mode` int NOT NULL COMMENT '调用方式：枚举（1异步，2同步）',
  `cmd_params` varchar(600) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '物模型参数集（物模型参数ID集），多ID以逗号隔开',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `product_type_id` bigint NOT NULL COMMENT '产品类别id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_event_model definition

CREATE TABLE `standard_event_model` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '事件标识符',
  `event_type` int NOT NULL COMMENT '事件类型：枚举（1信息，2告警，3故障）',
  `cmd_params` varchar(600) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '物模型参数集（物模型参数ID集），多ID以逗号隔开',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `product_type_id` bigint NOT NULL COMMENT '产品类别id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_event_model_param definition

CREATE TABLE `standard_event_model_param` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数标识符',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据类型，枚举（数值型、枚举型、布尔型、字符型等）',
  `data_specs` json DEFAULT NULL COMMENT '数据类型数据定义',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `direction` int NOT NULL DEFAULT '1' COMMENT '参数方向：1入参，2出参',
  `standard_event_model_id` bigint NOT NULL COMMENT '标准事件模型id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_model definition

CREATE TABLE `standard_model` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `func_type` int DEFAULT NULL,
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据类型，枚举（数值型、枚举型、布尔型、字符型等）',
  `access_type` int NOT NULL COMMENT '访问类型，枚举（1只读，2:读写）',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `product_type_id` bigint NOT NULL COMMENT '产品类别id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_property_model definition

CREATE TABLE `standard_property_model` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '属性名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '属性标识符',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据类型，枚举（数值型、枚举型、布尔型、字符型等）',
  `data_specs` json DEFAULT NULL COMMENT '数据类型数据定义',
  `access_type` int NOT NULL COMMENT '访问类型，枚举（1只读，2:读写）',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `product_type_id` bigint NOT NULL COMMENT '产品类别id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_property_model_param definition

CREATE TABLE `standard_property_model_param` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数名称',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数标识符',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据类型，枚举（数值型、枚举型、布尔型、字符型等）',
  `data_specs` json DEFAULT NULL COMMENT '数据类型数据定义',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '发布状态，0未发布，1发布',
  `direction` int NOT NULL DEFAULT '1' COMMENT '参数方向：1入参，2出参',
  `standard_property_model_id` bigint NOT NULL COMMENT '标准属性模型id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--insert 