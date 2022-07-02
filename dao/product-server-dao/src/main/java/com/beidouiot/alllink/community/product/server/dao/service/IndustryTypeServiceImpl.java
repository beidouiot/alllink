package com.beidouiot.alllink.community.product.server.dao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.product.IndustryType;
import com.beidouiot.alllink.community.common.data.entity.product.IndustryTypeProductType;
import com.beidouiot.alllink.community.common.data.mapping.product.server.industryType.IndustryTypeDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.industryType.IndustryTypeUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.IndustryTypeDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.IndustryTypeUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.IndustryTypeProductTypeRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.IndustryTypeRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.IndustryTypeService;

/**
*
* @Description 行业类别业务逻辑实现
* @author longww
* @date 2022年1月4日
*/
@Service
public class IndustryTypeServiceImpl implements IndustryTypeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndustryTypeServiceImpl.class);

	@Autowired
	private IndustryTypeDtoMapping industryTypeDtoMapping;
	
	@Autowired
	private IndustryTypeUpdateDtoMapping industryTypeUpdateDtoMapping;
	
	@Autowired
	private IndustryTypeRepository industryTypeRepository;
	
	@Autowired
	private IndustryTypeProductTypeRepository industryTypeProductTypeRepository;
	
	@Override
	public void saveEntity(IndustryTypeDto industryTypeDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("industryTypeDto = [ {} ]", industryTypeDto);
		}
		IndustryType industryType = industryTypeDtoMapping.targetToSource(industryTypeDto);
		industryTypeRepository.save(industryType);
	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		List<IndustryTypeProductType> industryTypeProductTypes = industryTypeProductTypeRepository.findIndustryTypeProductTypesByIndustryTypeId(id);
		if ( null != industryTypeProductTypes && industryTypeProductTypes.size() > 0 ) {
			throw new CanNotDeleteDataException("行业类别已使用，不能删除");
		}
		industryTypeRepository.deleteById(id);
	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<IndustryType> optional = industryTypeRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}
		
		List<IndustryTypeProductType> industryTypeProductTypes = industryTypeProductTypeRepository.findIndustryTypeProductTypesByIndustryTypeId(id);
		if ( null != industryTypeProductTypes && industryTypeProductTypes.size() > 0 ) {
			throw new CanNotDeleteDataException("行业类别已使用，不能删除");
		}

		IndustryType industryType = optional.get();
		Map<String, Object> map = getHeaderUser();
		industryType.setUpdatedBy(map.get("username").toString());
		industryType.setDeleteFlag(Constants.TRUE);
		industryTypeRepository.save(industryType);

	}

	@Override
	public void updateEntity(IndustryTypeUpdateDto industryTypeUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("industryTypeUpdateDto = [ {} ]", industryTypeUpdateDto);
		}
		
		if ( industryTypeUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<IndustryType> optional = industryTypeRepository.findById(industryTypeUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		IndustryType industryType = optional.get();
		industryType = industryTypeUpdateDtoMapping.targetToSourceForUpdate(industryTypeUpdateDto,industryType);
		industryTypeRepository.save(industryType);
		
	}

	@Override
	public IndustryTypeDto findById(Long id) throws ServiceException {
		return industryTypeDtoMapping.sourceToTarget(industryTypeRepository.findById(id).get());
	}

	@Override
	public List<IndustryTypeDto> findAll() throws ServiceException {
		List<IndustryType> list = industryTypeRepository.findByDeleteFlag(Constants.FALSE);
		List<IndustryTypeDto> dtoList = new ArrayList<IndustryTypeDto>();
		for (IndustryType industryType : list) {
			IndustryTypeDto industryTypeDto = industryTypeDtoMapping.sourceToTarget(industryType);
			dtoList.add(industryTypeDto);
		}
		return dtoList;
	}

	@Override
	public Page<IndustryTypeDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, industryTypeRepository, industryTypeDtoMapping);
	}

}
