package com.beidouiot.alllink.community.device.dao.service.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceData;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceDataDto;

/**
 * 设备采集数据管理服务
 * @author longww
 *
 */
public interface DeviceDataService {
	
	/**
	 * 存储设备采集数据
	 * @param deviceId 设备id
	 * @param deviceSN 设备SN
	 * @param data 采集的数据
	 * @throws ServiceException
	 */
	void saveData(String deviceId, String deviceSN, Map<String,String> data) throws ServiceException;
	
	/**
	 * 查询设备数据
	 * @param deviceId 设备id
	 * @param start   开始采集时间
	 * @param end   结束采集时间
	 * @param pageNum     页码号
	 * @param pageSize    页大小
	 * @return
	 * @throws ServiceException
	 */
	Page<DeviceDataDto> findDeviceData(Long deviceId, Date start, Date end, Integer pageNum, Integer pageSize) throws ServiceException;
	
	/**
	 * 查询设备数据
	 * @param deviceId  设备Id
	 * @param hours     最近hours个小时
	 * @param pageNum     页码号
	 * @param pageSize    页大小
	 * @return
	 * @throws ServiceException
	 */
	Page<DeviceDataDto> findDeviceData(Long deviceId, Integer hours, Integer pageNum, Integer pageSize) throws ServiceException;
	
	/**
	 * 查询设备数据
	 * @param deviceSN  设备SN号
	 * @param start   开始采集时间
	 * @param end   结束采集时间
	 * @param pageNum     页码号
	 * @param pageSize    页大小
	 * @return
	 * @throws ServiceException
	 */
	Page<DeviceDataDto> findDeviceData(String deviceSN, Date start, Date end, Integer pageNum, Integer pageSize) throws ServiceException;
	
	/**
	 * 查询设备数据
	 * @param deviceSN  设备SN
	 * @param hours     最近hours个小时
	 * @param pageNum     页码号
	 * @param pageSize    页大小
	 * @return
	 * @throws ServiceException
	 */
	Page<DeviceDataDto> findDeviceData(String deviceSN, Integer hours, Integer pageNum, Integer pageSize) throws ServiceException;
	 
	/**
	 * 查询设备数据
	 * @param deviceId   设备id
	 * @param modelCode  模型唯一标识码
	 * @param start   开始采集时间
	 * @param end   结束采集时间
	 * @param pageNum     页码号
	 * @param pageSize    页大小
	 * @return
	 * @throws ServiceException
	 */
	Page<DeviceDataDto> findDeviceData(Long deviceId, String modelCode, Date start, Date end, Integer pageNum, Integer pageSize) throws ServiceException;
	
	/**
	 * 查询设备数据
	 * @param deviceId  设备Id
	 * @param modelCode  模型唯一标识码
	 * @param hours     最近hours个小时
	 * @return
	 * @throws ServiceException
	 */
	Page<DeviceDataDto> findDeviceData(Long deviceId, String modelCode, Integer hours, Integer pageNum, Integer pageSize) throws ServiceException;
	
	/**
	 * 查询设备数据
	 * @param deviceSN  设备SN号
	 * @param modelCode  模型唯一标识
	 * @param start   开始采集时间
	 * @param end   结束采集时间
	 * @param pageNum     页码号
	 * @param pageSize    页大小
	 * @return
	 * @throws ServiceException
	 */
	Page<DeviceDataDto> findDeviceData(String deviceSN, String modelCode, Date start, Date end, Integer pageNum, Integer pageSize) throws ServiceException;
	
	/**
	 * 查询设备数据
	 * @param deviceSN     设备SN号
	 * @param modelCode    模型唯一标识
	 * @param hours        最近hours个小时
	 * @param pageNum     页码号
	 * @param pageSize    页大小
	 * @return
	 * @throws ServiceException
	 */
	Page<DeviceDataDto> findDeviceData(String deviceSN, String modelCode, Integer hours, Integer pageNum, Integer pageSize) throws ServiceException;
	/**
	 * 创建设备数据库表（MySQL）
	 * @param deviceSN  设备SN号
	 * @throws ServiceException
	 */
	void saveDeviceSNTableStructure(String deviceSN) throws ServiceException;
	
	/**
	 * 	设备属性上报
	 * @param jsonObj
	 */
	void doPropertiesUp(JSONObject jsonObj);
	
	/**
	 * 	设备事件上报
	 * @param jsonObj
	 */
	void doEventsUp(JSONObject jsonObj);
	
	/**
	 * 	设备命令上报
	 * @param jsonObj
	 */
	void doCommandsUp(JSONObject jsonObj);
}
