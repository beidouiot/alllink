package com.beidouiot.alllink.community.user.center.dao.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.DataExistException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.user.center.Industry;
import com.beidouiot.alllink.community.common.data.entity.user.center.Tenant;
import com.beidouiot.alllink.community.common.data.mapping.user.center.industry.IndustryDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.industry.IndustryUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.IndustryDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.IndustryUpdateDto;
import com.beidouiot.alllink.community.user.center.dao.repository.IndustryRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.TenantRepository;
import com.beidouiot.alllink.community.user.center.dao.service.api.IndustryService;

/**
 * 行业管理业务逻辑实现
 * 
 * @author longww
 *
 */
@Service
public class IndustryServiceImpl implements IndustryService {
	private static final Logger LOGGER = LoggerFactory.getLogger(IndustryServiceImpl.class);

	@Autowired
	private IndustryDtoMapping industryDtoMapping;
	
	@Autowired
	private IndustryUpdateDtoMapping industryUpdateDtoMapping;

	@Autowired
	private IndustryRepository industryRepository;

	@Autowired
	private TenantRepository tenantRepository;

	public void saveEntity(@Valid IndustryDto industryDto) throws ServiceException {
		LOGGER.debug("industryDto = [ {} ]", industryDto);
		Industry exist = industryRepository.findIndustryByCodeAndDeleteFlag(industryDto.getCode(), Constants.FALSE);
		if (null != exist) {
			throw new DataExistException("编号已存在");
		}
		Industry industry = industryDtoMapping.targetToSource(industryDto);
		industryRepository.save(industry);
	}

	public void delete(Long id) throws ServiceException {
		Optional<Industry> optional = industryRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		List<Tenant> tenants = tenantRepository.findByIndustryId(id);
		if (null != tenants && tenants.size() > 0) {
			throw new CanNotDeleteDataException("该行业已在使用，不能删除");
		}
		industryRepository.deleteById(id);
	}

	public void logicalDelete(Long id) throws ServiceException {
		Optional<Industry> optional = industryRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		List<Tenant> tenants = tenantRepository.findByIndustryId(id);
		if (null != tenants && tenants.size() > 0) {
			throw new CanNotDeleteDataException("该行业已在使用，不能删除");
		}
		Industry industry = optional.get();
		Map<String, Object> map = getHeaderUser();
		industry.setUpdatedBy(map.get("username").toString());
		industry.setDeleteFlag(Constants.TRUE);
		industryRepository.save(industry);
	}

	public void updateEntity(IndustryUpdateDto industryUpdateDto) throws ServiceException {
		LOGGER.debug("industryUpdateDto = [ {} ]", industryUpdateDto);
		Optional<Industry> optional = industryRepository.findById(industryUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		Industry industry = optional.get();
		Industry exist = industryRepository.findIndustryByCodeAndDeleteFlag(industryUpdateDto.getCode(), Constants.FALSE);
		if (null != exist && exist.getId().longValue() != industry.getId()) {
			throw new DataExistException("行业编号已存在");
		}
		exist = industryUpdateDtoMapping.targetToSourceForUpdate(industryUpdateDto,exist);
		industryRepository.save(exist);
	}

	public IndustryDto findById(Long id) throws ServiceException {
		return industryDtoMapping.sourceToTarget(industryRepository.findById(id).get());
	}

	public List<IndustryDto> findAll() throws ServiceException {
		List<Industry> list = industryRepository.findByDeleteFlag(Constants.FALSE);
		if( null == list || list.size() == 0) {
			return null;
		}
		
		return industryDtoMapping.sourceToTarget(list);
	}

	@Override
	public Page<IndustryDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return this.findPage(searchParams, pageNumber, pageSize, sortTypes, industryRepository, industryDtoMapping);
	}

}
