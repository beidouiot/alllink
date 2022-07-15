package com.beidouiot.alllink.community.device.service.dao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.enums.DeviceOnlineStatus;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.device.DeviceInfo;
import com.beidouiot.alllink.community.common.data.mapping.DeviceInfoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.DeviceInfoUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoDto;
import com.beidouiot.alllink.community.common.data.xxo.device.dto.DeviceInfoUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.device.dao.service.api.DeviceInfoService;
import com.beidouiot.alllink.community.device.service.dao.repository.DeviceInfoRepository;

@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DeviceInfoServiceImpl.class);

	@Autowired
	private DeviceInfoDtoMapping deviceInfoDtoMapping;

	@Autowired
	private DeviceInfoUpdateDtoMapping deviceInfoUpdateDtoMapping;

	@Autowired
	private DeviceInfoRepository deviceInfoRepository;

	@Override
	public void saveEntity(DeviceInfoDto deviceInfoDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deviceInfoDto = [ {} ]", deviceInfoDto);
		}

		DeviceInfo deviceInfo = deviceInfoDtoMapping.targetToSource(deviceInfoDto);
		Map<String,Object> map = getHeaderUser();
		String strTenantId = map.get("tenantId").toString();
		Long tenantId = strTenantId == null || strTenantId.equals("") ? null : Long.valueOf(strTenantId);
		deviceInfo.setOnlineStatus(DeviceOnlineStatus.UNACTIVATION.getCode());
		deviceInfo.setTenantId(tenantId);
		deviceInfoRepository.save(deviceInfo);
	}

	@Override
	public void delete(Long id) throws ServiceException {
		deviceInfoRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		Optional<DeviceInfo> optional = deviceInfoRepository.findById(id);
		DeviceInfo deviceInfo = optional.get();
		Map<String, Object> map = getHeaderUser();
		deviceInfo.setUpdatedBy(map.get("username").toString());
		deviceInfo.setDeleteFlag(Constants.TRUE);
		deviceInfoRepository.save(deviceInfo);

	}

	@Override
	public void updateEntity(DeviceInfoUpdateDto deviceInfoUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("deviceInfoUpdateDto = [ {} ]", deviceInfoUpdateDto);
		}

		Optional<DeviceInfo> optional = deviceInfoRepository.findById(deviceInfoUpdateDto.getId());

		DeviceInfo deviceInfo = optional.get();
		deviceInfo = deviceInfoUpdateDtoMapping.targetToSourceForUpdate(deviceInfoUpdateDto, deviceInfo);
		deviceInfoRepository.save(deviceInfo);

	}

	@Override
	public DeviceInfoDto findById(Long id) throws ServiceException {
		return deviceInfoDtoMapping.sourceToTarget(deviceInfoRepository.findById(id).get());
	}

	@Override
	public List<DeviceInfoDto> findAll() throws ServiceException {
		List<DeviceInfo> list = deviceInfoRepository.findByDeleteFlag(Constants.FALSE);
		return deviceInfoDtoMapping.sourceToTarget(list);
	}

	@Override
	public Page<DeviceInfoDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, deviceInfoRepository, deviceInfoDtoMapping);
	}

	@Override
	public List<DeviceInfoDto> findAllGateways() throws ServiceException {
		Map<String, Object> map = getHeaderUser();
		String username = map.get("username").toString();
		String tenantId = map.get("tenantId").toString();

		if ("admin".equals(username)) {
			List<DeviceInfo> deviceInfos = deviceInfoRepository.findByDeleteFlagAndGatewayFlag(Constants.FALSE,
					Constants.TRUE);
			return deviceInfoDtoMapping.sourceToTarget(deviceInfos);
		} else {
			if (tenantId != null && !tenantId.equals("")) {
				List<DeviceInfo> deviceInfos = new ArrayList<DeviceInfo>();
				deviceInfos = deviceInfoRepository.findByTenantIdAndDeleteFlagAndGatewayFlag(Long.valueOf(tenantId),
						Constants.FALSE, Constants.TRUE);

				return deviceInfoDtoMapping.sourceToTarget(deviceInfos);
			} else {
				List<DeviceInfo> deviceInfos = new ArrayList<DeviceInfo>();
				deviceInfos = deviceInfoRepository.findByDeleteFlagAndGatewayFlag(Constants.FALSE, Constants.TRUE);
				return deviceInfoDtoMapping.sourceToTarget(deviceInfos);
			}
		}
	}

	@Override
	public List<DeviceInfoDto> findAllDeviceInfo(Boolean gatewayFlag, Boolean enableFlag) throws ServiceException {
		Map<String, Object> map = getHeaderUser();
		String username = map.get("username").toString();
		String tenantId = map.get("tenantId").toString();

		if ("admin".equals(username)) {
			List<DeviceInfo> deviceInfos = deviceInfoRepository
					.findByDeleteFlagAndEnableFlagAndGatewayFlag(Constants.FALSE, enableFlag, gatewayFlag);
			return deviceInfoDtoMapping.sourceToTarget(deviceInfos);
		} else {
			if (tenantId != null && !tenantId.equals("")) {
				List<DeviceInfo> deviceInfos = new ArrayList<DeviceInfo>();
				deviceInfos = deviceInfoRepository.findByTenantIdAndDeleteFlagAndGatewayFlagAndEnableFlag(
						Long.valueOf(tenantId), Constants.FALSE, gatewayFlag, enableFlag);

				return deviceInfoDtoMapping.sourceToTarget(deviceInfos);
			} else {
				List<DeviceInfo> deviceInfos = new ArrayList<DeviceInfo>();
				deviceInfos = deviceInfoRepository.findByDeleteFlagAndEnableFlagAndGatewayFlag(Constants.FALSE,
						enableFlag, gatewayFlag);
				return deviceInfoDtoMapping.sourceToTarget(deviceInfos);
			}
		}
	}

}
