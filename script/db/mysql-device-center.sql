CREATE DATABASE `iot_device_center` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- iot_device_center.device_data definition

CREATE TABLE `device_data` (
  `id` bigint NOT NULL COMMENT '����id',
  `device_id` bigint NOT NULL COMMENT '�豸id',
  `device_sn` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�豸Ψһ��ʶ��',
  `model_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ģ�ͱ�ʶ����',
  `model_value` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ģ�ͱ�ʶֵ',
  `collect_time` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '���ݲɼ�ʱ��',
  `model_type` int NOT NULL COMMENT '��ģ�����ͣ�1����ģ�ͣ�2�¼���3ָ��',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_device_center.device_info definition

CREATE TABLE `device_info` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��ҵ�������',
  `nickname` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '�豸�ǳ�',
  `device_sn` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�豸Ψһ��ʶ��',
  `networking_protocol` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '�豸����Э�飬ö�٣�NB-IoT��LoRa��433��Wi-Fi��BLE��ZigBee��Z-Wave��485����̫����������',
  `equipment_model` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '�豸�ͺ�',
  `firmware_name` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '�̼��汾����',
  `firmware_version` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '�̼��汾��',
  `hardware_version` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'Ӳ���汾',
  `last_link_time` datetime DEFAULT NULL COMMENT '�������ʱ�䣨���ϴ�����ʱ�䣩',
  `online_status` int NOT NULL COMMENT '����״̬��ö�٣����ߡ����ߡ����δ���δ֪��',
  `longitude` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��װλ�þ���',
  `latitude` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��װλ��γ��',
  `position_detail` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ϸλ�ã���װλ�ã�',
  `expiry_warranty_date` date DEFAULT NULL COMMENT '��������',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ע',
  `device_access_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�豸�������ͣ�ö�٣�ֱ�ӽ��룬������ƽ̨���룩',
  `gateway_device_id` bigint DEFAULT NULL COMMENT '���������豸��ͨ�������豸����Ĵ���������ͨ���ĸ������豸����',
  `gateway_flag` bit(1) NOT NULL COMMENT '�Ƿ������豸',
  `third_platform_id` bigint DEFAULT NULL COMMENT '��������������ƽ̨',
  `tenant_id` bigint DEFAULT NULL COMMENT '�����⻧id',
  `activation_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `enable_flag` bit(1) NOT NULL COMMENT '�Ƿ�����',
  `lasted_upgrade_time` datetime DEFAULT NULL COMMENT '��������������ƽ̨',
  `firmware_upgrade_quantity` int DEFAULT NULL COMMENT '�̼���������',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_device_center.device_new_data definition

CREATE TABLE `device_new_data` (
  `id` bigint NOT NULL COMMENT '����id',
  `device_id` bigint NOT NULL COMMENT '�豸id',
  `model_code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��ģ�ͱ�ʶ',
  `model_type` int NOT NULL COMMENT '��ģ������(����ģ�ͣ�1���¼�ģ�ͣ�2��ָ��ģ�ͣ�3)',
  `received_data` varchar(2000) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��ģ������',
  `tenant_id` bigint NOT NULL COMMENT '�����⻧id',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_device_center.device_upgrade_info definition

CREATE TABLE `device_upgrade_info` (
  `id` bigint NOT NULL COMMENT '����id',
  `device_id` bigint NOT NULL COMMENT '�豸id',
  `device_sn` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�豸Ψһ��ʶ��',
  `firmware_version` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '�̼��汾��',
  `upgrade_logs` varchar(5000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '������־',
  `upgrade_time` datetime NOT NULL COMMENT '�̼�����ʱ��',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;