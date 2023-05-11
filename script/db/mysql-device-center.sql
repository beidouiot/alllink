CREATE DATABASE `iot_device_center` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- iot_device_center.device_data definition

CREATE TABLE `device_data` (
  `id` bigint NOT NULL COMMENT '主键id',
  `device_id` bigint NOT NULL COMMENT '设备id',
  `device_sn` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备唯一标识号',
  `model_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '模型标识代码',
  `model_value` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '模型标识值',
  `collect_time` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据采集时间',
  `model_type` int NOT NULL COMMENT '物模型类型，1属性模型，2事件，3指令',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_device_center.device_info definition

CREATE TABLE `device_info` (
  `id` bigint NOT NULL COMMENT '主键id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '行业类别名称',
  `nickname` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备昵称',
  `device_sn` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备唯一标识号',
  `networking_protocol` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备组网协议，枚举（NB-IoT、LoRa、433、Wi-Fi、BLE、ZigBee、Z-Wave、485、以太网，其他）',
  `equipment_model` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '设备型号',
  `firmware_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '固件版本名称',
  `firmware_version` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '固件版本号',
  `hardware_version` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '硬件版本',
  `last_link_time` datetime DEFAULT NULL COMMENT '最后连接时间（或上传数据时间）',
  `online_status` int NOT NULL COMMENT '在线状态，枚举（在线、离线、激活、未激活、未知）',
  `longitude` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '安装位置经度',
  `latitude` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '安装位置纬度',
  `position_detail` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '详细位置（安装位置）',
  `expiry_warranty_date` date DEFAULT NULL COMMENT '过保日期',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `device_access_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备接入类型，枚举（直接接入，第三方平台接入）',
  `gateway_device_id` bigint DEFAULT NULL COMMENT '所属网关设备，通过网关设备接入的传感器，是通过哪个网关设备接入',
  `gateway_flag` bit(1) NOT NULL COMMENT '是否网关设备',
  `third_platform_id` bigint DEFAULT NULL COMMENT '所属第三方接入平台',
  `tenant_id` bigint DEFAULT NULL COMMENT '所属租户id',
  `activation_time` datetime DEFAULT NULL COMMENT '激活时间',
  `enable_flag` bit(1) NOT NULL COMMENT '是否启用',
  `lasted_upgrade_time` datetime DEFAULT NULL COMMENT '所属第三方接入平台',
  `firmware_upgrade_quantity` int DEFAULT NULL COMMENT '固件升级次数',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_device_center.device_new_data definition

CREATE TABLE `device_new_data` (
  `id` bigint NOT NULL COMMENT '主键id',
  `device_id` bigint NOT NULL COMMENT '设备id',
  `model_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '物模型标识',
  `model_type` int NOT NULL COMMENT '物模型类型(属性模型：1，事件模型：2，指令模型：3)',
  `received_data` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '物模型数据',
  `tenant_id` bigint NOT NULL COMMENT '所属租户id',
  `product_id` bigint NOT NULL COMMENT '产品id',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_device_center.device_upgrade_info definition

CREATE TABLE `device_upgrade_info` (
  `id` bigint NOT NULL COMMENT '主键id',
  `device_id` bigint NOT NULL COMMENT '设备id',
  `device_sn` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备唯一标识号',
  `firmware_version` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '固件版本号',
  `upgrade_logs` varchar(5000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '升级日志',
  `upgrade_time` datetime NOT NULL COMMENT '固件升级时间',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `updated_date` datetime NOT NULL COMMENT '修改时间',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',
  `opt_lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;