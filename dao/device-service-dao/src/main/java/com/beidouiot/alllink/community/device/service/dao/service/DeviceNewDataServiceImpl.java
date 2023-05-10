package com.beidouiot.alllink.community.device.service.dao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceNewData;
import com.beidouiot.alllink.community.common.data.mapping.DeviceNewDataDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.DeviceNewDataUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceNewDataUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.dto.ID;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.product.ProductSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.product.rpo.standardmodel.StandardPropertyModelSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceDataService;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceNewDataService;
import com.beidouiot.alllink.community.device.service.dao.repository.DeviceInfoRepository;
import com.beidouiot.alllink.community.device.service.dao.repository.DeviceNewDataRepository;
import com.beidouiot.alllink.community.feign.product.ProductFeignClient;
import com.beidouiot.alllink.community.feign.product.ProductModelFeignClient;
import com.beidouiot.alllink.community.feign.product.StandardPropertyModelFeignClient;

@Service
public class DeviceNewDataServiceImpl implements DeviceNewDataService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceNewDataServiceImpl.class);
	
	@Autowired
	private DeviceNewDataDtoMapping deviceNewDataDtoMapping;

	@Autowired
	private DeviceNewDataUpdateDtoMapping deviceNewDataUpdateDtoMapping;

	@Autowired
	private DeviceNewDataRepository deviceNewDataRepository;
	
	@Autowired
	private ProductModelFeignClient productModelFeignClient;
	
	@Autowired
	private DeviceInfoRepository deviceInfoRepository;
	
	@Override
	@Transactional
	public void saveEntity(DeviceNewDataDto deviceNewDataDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deviceNewDataDto = [ {} ]", deviceNewDataDto);
		}
		
		List<DeviceNewData> existDeviceNewDatas = deviceNewDataRepository.findByDeviceIdAndModelCodeAndModelTypeAndDeleteFlag(deviceNewDataDto.getDeviceId(),deviceNewDataDto.getModelCode(),deviceNewDataDto.getModelType(),Constants.FALSE);
		if ( existDeviceNewDatas == null || existDeviceNewDatas.size() == 0 ) {
			DeviceNewData deviceNewData = deviceNewDataDtoMapping.targetToSource(deviceNewDataDto);
			Optional<DeviceInfo> o = deviceInfoRepository.findById(deviceNewDataDto.getDeviceId());
			if ( !o.isPresent() ) {
				throw new ServiceException("设备Id不存在");
			}
			DeviceInfo deviceInfo = o.get();
			deviceNewData.setProductId(deviceInfo.getProductId());
//			Map<String,Object> map = getHeaderUser();
//			String strTenantId = map.get("tenantId").toString();
//			Long tenantId = strTenantId == null || strTenantId.equals("") ? null : Long.valueOf(strTenantId);
			deviceNewData.setTentantId(deviceInfo.getTenantId());
			deviceNewDataRepository.save(deviceNewData);
		} else {
			DeviceNewData deviceNewData = existDeviceNewDatas.get(0);
			deviceNewData.setReceivedData(deviceNewDataDto.getReceivedData());
			deviceNewData.setUpdatedDate(new Date());
			deviceNewDataRepository.save(deviceNewData);
		}
	}

	@Override
	public void delete(Long id) throws ServiceException {
		deviceNewDataRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		Optional<DeviceNewData> optional = deviceNewDataRepository.findById(id);
		DeviceNewData deviceNewData = optional.get();
		Map<String,Object> map = getHeaderUser();
		deviceNewData.setUpdatedBy(map.get("username").toString());
		deviceNewData.setDeleteFlag(Constants.TRUE);
		deviceNewDataRepository.save(deviceNewData);

	}

	@Override
	@Transactional
	public void updateEntity(DeviceNewDataUpdateDto deviceNewDataUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deviceNewDataUpdateDto = [ {} ]", deviceNewDataUpdateDto);
		}
		
		if ( deviceNewDataUpdateDto.getId() == null | deviceNewDataUpdateDto.getId() == 0) {
			if ( deviceNewDataUpdateDto.getDeviceId() == null || deviceNewDataUpdateDto.getDeviceId() == 0 ) {
				throw new ServiceException("设备Id不能为空！");
			}
			List<DeviceNewData> list = deviceNewDataRepository.findByDeviceIdAndModelCodeAndModelTypeAndDeleteFlag(deviceNewDataUpdateDto.getDeviceId(),deviceNewDataUpdateDto.getModelCode(),deviceNewDataUpdateDto.getModelType(),Constants.FALSE);
			if ( list == null || list.size() == 0 ) {
				throw new ServiceException("没有该设备数据！");
			}
			DeviceNewData deviceNewData = list.get(0);
			deviceNewData = deviceNewDataUpdateDtoMapping.targetToSourceForUpdate(deviceNewDataUpdateDto, deviceNewData);
			deviceNewData.setUpdatedDate(new Date());
			deviceNewDataRepository.save(deviceNewData);
		} else {
			Optional<DeviceNewData> optional = deviceNewDataRepository.findById(deviceNewDataUpdateDto.getId());
			DeviceNewData deviceNewData = optional.get();
			deviceNewData = deviceNewDataUpdateDtoMapping.targetToSourceForUpdate(deviceNewDataUpdateDto, deviceNewData);
			deviceNewData.setUpdatedDate(new Date());
			deviceNewDataRepository.save(deviceNewData);
		}
	}

	@Override
	public DeviceNewDataDto findById(Long id) throws ServiceException {
		return deviceNewDataDtoMapping.sourceToTarget(deviceNewDataRepository.findById(id).get());
	}
	
	@Override
	public List<DeviceNewDataDto> findDeviceNewDatasByDeviceId(Long deviceId) throws ServiceException {
		List<DeviceNewDataDto> listDeviceNewDataDtos = deviceNewDataDtoMapping.sourceToTarget(deviceNewDataRepository.findByDeviceIdAndDeleteFlag(deviceId,Constants.FALSE));
		Map<String,DeviceNewDataDto> dataMap = null;
		if( listDeviceNewDataDtos != null && listDeviceNewDataDtos.size() > 0 ) {
			dataMap = listDeviceNewDataDtos.stream().collect(Collectors.toMap(DeviceNewDataDto::getModelCode, deviceNewDataDto -> deviceNewDataDto));
		}
		
		DeviceInfo deviceInfo = deviceInfoRepository.findById(deviceId).get();
		
		ResultDataRro<ProductModelDto> rdr = productModelFeignClient.findPubProductModelByProductId(new ID(deviceInfo.getProductId()));
		ProductModelDto productModelDto = rdr.getData();
		List<ProductPropertyModelDto>  productPropertyModelDtos = productModelDto.getProductPropertyModelList();
//		Map<String,String> propertyModelMap = productPropertyModelDtos.stream().collect(Collectors.toMap(ProductPropertyModelDto::getCode, ProductPropertyModelDto::getName));
		List<ProductEventModelDto>  productEventModelDtos = productModelDto.getProductEventModelList();
//		Map<String,String> eventModelMap = productEventModelDtos.stream().collect(Collectors.toMap(ProductEventModelDto::getCode, ProductEventModelDto::getName));
		List<ProductCommandModelDto>  productCommandModelDtos = productModelDto.getProductCommandModelList();
//		Map<String,String> commandModelMap = productCommandModelDtos.stream().collect(Collectors.toMap(ProductCommandModelDto::getCode, ProductCommandModelDto::getName));
		List<DeviceNewDataDto> list = new ArrayList<DeviceNewDataDto>();
		for (ProductPropertyModelDto productPropertyModelDto : productPropertyModelDtos) {
			DeviceNewDataDto dndd = new DeviceNewDataDto();
			if ( dataMap == null ) {
				dndd.setModelCode(productPropertyModelDto.getCode());
				dndd.setModelName(productPropertyModelDto.getName());
				dndd.setModelType(1);
				dndd.setProductId(productPropertyModelDto.getProductId());
				dndd.setReceivedData("无数据");
			} else {
				DeviceNewDataDto tmp = dataMap.get(productPropertyModelDto.getCode());
				if(tmp == null) {
					dndd.setModelCode(productPropertyModelDto.getCode());
					dndd.setModelName(productPropertyModelDto.getName());
					dndd.setModelType(1);
					dndd.setProductId(productPropertyModelDto.getProductId());
					dndd.setReceivedData("无数据");
				} else {
					dndd = tmp;
				}
			}
			list.add(dndd);
		}
		
		for (ProductEventModelDto productEventModelDto : productEventModelDtos) {
			DeviceNewDataDto dndd = new DeviceNewDataDto();
			if ( dataMap == null ) {
				dndd.setModelCode(productEventModelDto.getCode());
				dndd.setModelName(productEventModelDto.getName());
				dndd.setModelType(1);
				dndd.setProductId(productEventModelDto.getProductId());
				dndd.setReceivedData("无数据");
			} else {
				DeviceNewDataDto tmp = dataMap.get(productEventModelDto.getCode());
				if(tmp == null) {
					dndd.setModelCode(productEventModelDto.getCode());
					dndd.setModelName(productEventModelDto.getName());
					dndd.setModelType(1);
					dndd.setProductId(productEventModelDto.getProductId());
					dndd.setReceivedData("无数据");
				} else {
					dndd = tmp;
				}
			}
			list.add(dndd);
		}
		
		for (ProductCommandModelDto productCommandModelDto : productCommandModelDtos) {
			DeviceNewDataDto dndd = new DeviceNewDataDto();
			if ( dataMap == null ) {
				dndd.setModelCode(productCommandModelDto.getCode());
				dndd.setModelName(productCommandModelDto.getName());
				dndd.setModelType(1);
				dndd.setProductId(productCommandModelDto.getProductId());
				dndd.setReceivedData("无数据");
			} else {
				DeviceNewDataDto tmp = dataMap.get(productCommandModelDto.getCode());
				if(tmp == null) {
					dndd.setModelCode(productCommandModelDto.getCode());
					dndd.setModelName(productCommandModelDto.getName());
					dndd.setModelType(1);
					dndd.setProductId(productCommandModelDto.getProductId());
					dndd.setReceivedData("无数据");
				} else {
					dndd = tmp;
				}
			}
			list.add(dndd);
		}
		
		
//		for (DeviceNewDataDto deviceNewDataDto : listDeviceNewDataDtos) {
//			deviceNewDataDto.setDeviceName(deviceInfo.getName());
//			deviceNewDataDto.setDeviceNickname(deviceInfo.getNickname());
//			if ( deviceNewDataDto.getModelType() == 1 ) {
//				deviceNewDataDto.setModelName(propertyModelMap.get(deviceNewDataDto.getModelCode()));
//			} else if ( deviceNewDataDto.getModelType() == 2 ) {
//				deviceNewDataDto.setModelName(eventModelMap.get(deviceNewDataDto.getModelCode()));
//			} else {
//				deviceNewDataDto.setModelName(commandModelMap.get(deviceNewDataDto.getModelCode()));
//			}
//		}
		return list;
	}

	@Override
	public List<DeviceNewDataDto> findAll() throws ServiceException {
		List<DeviceNewData> list = deviceNewDataRepository.findByDeleteFlag(Constants.FALSE);
		return deviceNewDataDtoMapping.sourceToTarget(list);
	}

	@Override
	public Page<DeviceNewDataDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, deviceNewDataRepository, deviceNewDataDtoMapping);
	}

}
