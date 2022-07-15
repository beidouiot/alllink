package com.beidouiot.alllink.community.user.center.dao.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.user.center.Park;
import com.beidouiot.alllink.community.common.data.mapping.user.center.park.ParkDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.park.ParkUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ParkDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ParkUpdateDto;
import com.beidouiot.alllink.community.user.center.dao.repository.ParkRepository;
import com.beidouiot.alllink.community.user.center.dao.service.api.ParkService;

/**
 * 园区/小区管理业务逻辑实现
 * @author longww
 *
 */
@Service
public class ParkServiceImpl implements ParkService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkServiceImpl.class);
	
	@Autowired
	private ParkDtoMapping parkDtoMapping;
	
	@Autowired
	private ParkUpdateDtoMapping parkUpdateDtoMapping;
	
	@Autowired
	private ParkRepository parkRepository;
	
	public void saveEntity(@Valid ParkDto parkDto) throws ServiceException {
		LOGGER.debug("parkDto = [ {} ]", parkDto);
		Park park = parkDtoMapping.targetToSource(parkDto);
		Map<String, Object> map = getHeaderUser();
		String strTenantId = map.get("tenantId").toString();
		Long tenantId = strTenantId == null || strTenantId.equals("") ? null : Long.valueOf(strTenantId);
		park.setTenantId(tenantId);
		parkRepository.save(park);
	}

	public void delete(@Valid @NotNull Long id) throws ServiceException {
		Optional<Park> optional = parkRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}
		
		parkRepository.deleteById(id);
	}

	public void logicalDelete(@Valid @NotNull Long id) throws ServiceException {
		Optional<Park> optional = parkRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}

		Park park = optional.get();
		Map<String, Object> map = getHeaderUser();
		park.setUpdatedBy(map.get("username").toString());
		park.setDeleteFlag(Constants.TRUE);
		parkRepository.save(park);

	}

	public void updateEntity(ParkUpdateDto parkUpdateDto) throws ServiceException {
		LOGGER.debug("parkUpdateDto = [ {} ]", parkUpdateDto);
		Optional<Park> optional = parkRepository.findById(parkUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		Park park = optional.get();
		park = parkUpdateDtoMapping.targetToSourceForUpdate(parkUpdateDto,park);
		parkRepository.save(park);

	}

	public ParkDto findById(Long id) throws ServiceException {
		return parkDtoMapping.sourceToTarget(parkRepository.findById(id).get());
	}

	public List<ParkDto> findAll() throws ServiceException {
		List<Park> list = parkRepository.findByDeleteFlag(Constants.FALSE);
		if( null == list || list.size() == 0 ) {
			return null;
		}
		return parkDtoMapping.sourceToTarget(list);
	}

	public Page<ParkDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize, List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, parkRepository, parkDtoMapping);
	}

}
