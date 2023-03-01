package com.beidouiot.alllink.community.device.service.dao.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.beidouiot.alllink.community.common.data.entity.device.DeviceData;

public interface DeviceDataRepository extends PagingAndSortingRepository<DeviceData, Long>, JpaSpecificationExecutor<DeviceData>{

//	@Query("create table if not exists ?1 ('id' bigint NOT NULL COMMENT '主键id',"
//			+ "'device_id' bigint NOT NULL COMMENT '设备id',"
//			+ "'device_sn' varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备唯一标识号',"
//			+ "'model_code' varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '物模型标识',"
//			+ "'model_value' varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '模型标识值', "
//			+ "'collect_time' varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据采集时间',"
//			+ "'model_type' int NOT NULL COMMENT '物模型类型(属性模型：1，事件模型：2，指令模型：3)',"
//			+ "'create_date' datetime NOT NULL COMMENT '创建时间',"
//			+ "'updated_date' datetime NOT NULL COMMENT '修改时间',"
//			+ "'created_by' varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人（用户登录名）',"
//			+ "'updated_by' varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人（用户登录名）',"
//			+ "'delete_flag' bit(1) NOT NULL DEFAULT b'0' COMMENT '删除标志',"
//			+ "'opt_lock_version' int DEFAULT NULL COMMENT '乐观锁版本号',"
//			+ "PRIMARY KEY ('id')) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci, nativeQuery = true")
//	void saveDeviceSNTableStructure(String deviceSN);
}
