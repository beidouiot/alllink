CREATE DATABASE `iot_product_center` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- iot_product_center.industry_type definition

CREATE TABLE `industry_type` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��ҵ�������',
  `code` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��ҵ�����',
  `descr` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ҵ�������',
  `sort_no` int DEFAULT '1' COMMENT '�����',
  `pid` bigint DEFAULT NULL COMMENT '��id',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product definition

CREATE TABLE `product` (
  `id` bigint NOT NULL COMMENT '����id',
  `chinese_name` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��Ʒ��������',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��Ʒ����',
  `product_device_type` int NOT NULL COMMENT '��Ʒ�豸���ͣ�ö�٣�1�����豸��2�������豸��3�����豸�������Ϊ�����豸���߶����豸�������ȷ�ϲ�Ʒ���뷽ʽ��ֱ�ӽ��롢��Ե�ڵ���룩',
  `product_access_mode` int DEFAULT NULL COMMENT '��Ʒ���뷽ʽ��ö�٣�1ֱ�ӽ��룬2��Ե�ڵ���룩��ֱ�ӽ���--�豸ͨ������ֱ�ӽ���ƽ̨����Ե�ڵ����--�豸���Ƚ����Ե�������ڵ㲢ͨ����Ե�ڵ��ӽ��롣',
  `communication_protocol` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ͨ��Э�飬ö�٣�MQTT��HTTP��CoAP�ȣ�',
  `networking_protocol` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '����Э�飬ö�٣�NB-IoT��LoRa��433��Wi-Fi��BLE��ZigBee��Z-Wave��485����̫����������',
  `descr` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��Ʒ����',
  `copy_flag` bit(1) NOT NULL DEFAULT b'1' COMMENT '�Ƿ��Ʊ�׼��ģ�ͣ����Ʊ�׼��ģ�ͣ��򽫵�ǰ��׼��ģ����ȫ���Ƶ���Ʒ�������������׼��ģ�͵��޸Ķ��Ķ�',
  `product_type_id` bigint NOT NULL COMMENT '��Ʒ���id',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  `tenant_id` bigint DEFAULT NULL COMMENT '�����⻧id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_command_model definition

CREATE TABLE `product_command_model` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ָ������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ָ���ʶ��',
  `call_mode` int NOT NULL COMMENT '���÷�ʽ��ö�٣�1�첽��2ͬ����',
  `cmd_params` varchar(600) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ģ�Ͳ���������ģ�Ͳ���ID��������ID�Զ��Ÿ���',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ע',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_event_model definition

CREATE TABLE `product_event_model` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�¼�����',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�¼���ʶ��',
  `event_type` int NOT NULL COMMENT '�¼����ͣ�ö�٣�1��Ϣ��2�澯��3���ϣ�',
  `cmd_params` varchar(600) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ģ�Ͳ���������ģ�Ͳ���ID��������ID�Զ��Ÿ���',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ע',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_event_model_param definition

CREATE TABLE `product_event_model_param` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '������ʶ��',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�������ͣ�ö�٣���ֵ�͡�ö���͡������͡��ַ��͵ȣ�',
  `data_specs` json DEFAULT NULL COMMENT '�����������ݶ���',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `direction` int NOT NULL DEFAULT '1' COMMENT '��������1��Σ�2����',
  `product_event_model_id` bigint NOT NULL COMMENT '��Ʒ�¼�ģ��id',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_model_version definition

CREATE TABLE `product_model_version` (
  `id` bigint NOT NULL COMMENT '����id',
  `model_content` json NOT NULL COMMENT '��ģ������,Json��ʽ',
  `use_flag` bit(1) NOT NULL COMMENT '��ǰʹ�ñ�־��0��ʹ�ã�1ʹ�á�һ����Ʒֻ����һ����ʹ�á���ģ�ͷ�����Ĭ��Ϊʹ�ø÷�����ģ�͡�������ʷ��ģ������Ϊ��ǰ��ʹ��',
  `version_number` bigint NOT NULL COMMENT '��ģ�Ͱ汾��',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_property_model definition

CREATE TABLE `product_property_model` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '���Ա�ʶ��',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�������ͣ�ö�٣���ֵ�͡�ö���͡������͡��ַ��͵ȣ�',
  `data_specs` json DEFAULT NULL COMMENT '�����������ݶ���',
  `access_type` int NOT NULL COMMENT '�������ͣ�ö�٣�1ֻ����2:��д��',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_property_model_param definition

CREATE TABLE `product_property_model_param` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '������ʶ��',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�������ͣ�ö�٣���ֵ�͡�ö���͡������͡��ַ��͵ȣ�',
  `data_specs` json DEFAULT NULL COMMENT '�����������ݶ���',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `direction` int NOT NULL DEFAULT '1' COMMENT '��������1��Σ�2����',
  `product_property_model_id` bigint NOT NULL COMMENT '��Ʒ����ģ��id',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_type definition

CREATE TABLE `product_type` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��Ʒ�������',
  `code` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��Ʒ�����',
  `descr` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��Ʒ�������',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.product_type_industry_type definition

CREATE TABLE `product_type_industry_type` (
  `industry_type_id` bigint NOT NULL COMMENT '��ҵ���id',
  `product_type_id` bigint NOT NULL COMMENT '��Ʒ���id',
  PRIMARY KEY (`industry_type_id`,`product_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_command_model_param definition

CREATE TABLE `standard_command_model_param` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '������ʶ��',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�������ͣ�ö�٣���ֵ�͡�ö���͡������͡��ַ��͵ȣ�',
  `data_specs` json DEFAULT NULL COMMENT '�����������ݶ���',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `direction` int NOT NULL DEFAULT '1' COMMENT '��������1��Σ�2����',
  `product_command_model_id` bigint NOT NULL COMMENT '��Ʒָ��ģ��id',
  `product_id` bigint NOT NULL COMMENT '��Ʒid',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  `standard_command_model_id` bigint NOT NULL COMMENT '��׼ָ��ģ��id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_commond_model definition

CREATE TABLE `standard_commond_model` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ָ������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ָ���ʶ��',
  `call_mode` int NOT NULL COMMENT '���÷�ʽ��ö�٣�1�첽��2ͬ����',
  `cmd_params` varchar(600) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ģ�Ͳ���������ģ�Ͳ���ID��������ID�Զ��Ÿ���',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ע',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `product_type_id` bigint NOT NULL COMMENT '��Ʒ���id',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_event_model definition

CREATE TABLE `standard_event_model` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�¼�����',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�¼���ʶ��',
  `event_type` int NOT NULL COMMENT '�¼����ͣ�ö�٣�1��Ϣ��2�澯��3���ϣ�',
  `cmd_params` varchar(600) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ģ�Ͳ���������ģ�Ͳ���ID��������ID�Զ��Ÿ���',
  `remark` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '��ע',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `product_type_id` bigint NOT NULL COMMENT '��Ʒ���id',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_event_model_param definition

CREATE TABLE `standard_event_model_param` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '������ʶ��',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�������ͣ�ö�٣���ֵ�͡�ö���͡������͡��ַ��͵ȣ�',
  `data_specs` json DEFAULT NULL COMMENT '�����������ݶ���',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `direction` int NOT NULL DEFAULT '1' COMMENT '��������1��Σ�2����',
  `standard_event_model_id` bigint NOT NULL COMMENT '��׼�¼�ģ��id',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_model definition

CREATE TABLE `standard_model` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `func_type` int DEFAULT NULL,
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�������ͣ�ö�٣���ֵ�͡�ö���͡������͡��ַ��͵ȣ�',
  `access_type` int NOT NULL COMMENT '�������ͣ�ö�٣�1ֻ����2:��д��',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `product_type_id` bigint NOT NULL COMMENT '��Ʒ���id',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_property_model definition

CREATE TABLE `standard_property_model` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '���Ա�ʶ��',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�������ͣ�ö�٣���ֵ�͡�ö���͡������͡��ַ��͵ȣ�',
  `data_specs` json DEFAULT NULL COMMENT '�����������ݶ���',
  `access_type` int NOT NULL COMMENT '�������ͣ�ö�٣�1ֻ����2:��д��',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `product_type_id` bigint NOT NULL COMMENT '��Ʒ���id',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- iot_product_center.standard_property_model_param definition

CREATE TABLE `standard_property_model_param` (
  `id` bigint NOT NULL COMMENT '����id',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '��������',
  `code` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '������ʶ��',
  `data_type` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�������ͣ�ö�٣���ֵ�͡�ö���͡������͡��ַ��͵ȣ�',
  `data_specs` json DEFAULT NULL COMMENT '�����������ݶ���',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '����״̬��0δ������1����',
  `direction` int NOT NULL DEFAULT '1' COMMENT '��������1��Σ�2����',
  `standard_property_model_id` bigint NOT NULL COMMENT '��׼����ģ��id',
  `create_date` datetime NOT NULL COMMENT '����ʱ��',
  `updated_date` datetime NOT NULL COMMENT '�޸�ʱ��',
  `created_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�����ˣ��û���¼����',
  `updated_by` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '�޸��ˣ��û���¼����',
  `delete_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT 'ɾ����־',
  `opt_lock_version` int DEFAULT NULL COMMENT '�ֹ����汾��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--insert 