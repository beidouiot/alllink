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

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.product.StandardPropertyModelParam;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardPropertyModelParamDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardPropertyModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardPropertyModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardPropertyModelParamService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class StandardPropertyModelParamServiceImpl implements StandardPropertyModelParamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardPropertyModelParamServiceImpl.class);
	
	@Autowired
	private StandardPropertyModelParamDtoMapping standardPropertyModelParamDtoMapping;
	
	@Autowired
	private StandardPropertyModelParamUpdateDtoMapping standardPropertyModelParamUpdateDtoMapping;
	
	@Autowired
	private StandardPropertyModelParamRepository standardPropertyModelParamRepository;
	
	@Override
	public void saveEntity(StandardPropertyModelParamDto standardPropertyModelParamDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardPropertyModelParamDto = [ {} ]", standardPropertyModelParamDto);
		}
		StandardPropertyModelParam standardPropertyModelParam = standardPropertyModelParamDtoMapping.targetToSource(standardPropertyModelParamDto);
		standardPropertyModelParamRepository.save(standardPropertyModelParam);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		standardPropertyModelParamRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<StandardPropertyModelParam> optional = standardPropertyModelParamRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardPropertyModelParam standardPropertyModelParam = optional.get();
		Map<String, Object> map = getHeaderUser();
		standardPropertyModelParam.setUpdatedBy(map.get("username").toString());
		standardPropertyModelParam.setDeleteFlag(Constants.TRUE);
		standardPropertyModelParamRepository.save(standardPropertyModelParam);

	}

	@Override
	public void updateEntity(StandardPropertyModelParamUpdateDto standardPropertyModelParamUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardPropertyModelParamUpdateDto = [ {} ]", standardPropertyModelParamUpdateDto);
		}
		
		if ( standardPropertyModelParamUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<StandardPropertyModelParam> optional = standardPropertyModelParamRepository.findById(standardPropertyModelParamUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardPropertyModelParam standardPropertyModelParam = optional.get();
		standardPropertyModelParam = standardPropertyModelParamUpdateDtoMapping.targetToSourceForUpdate(standardPropertyModelParamUpdateDto,standardPropertyModelParam);
		standardPropertyModelParamRepository.save(standardPropertyModelParam);

	}

	@Override
	public StandardPropertyModelParamDto findById(Long id) throws ServiceException {
		return standardPropertyModelParamDtoMapping.sourceToTarget(standardPropertyModelParamRepository.findById(id).get());
	}

	@Override
	public List<StandardPropertyModelParamDto> findAll() throws ServiceException {
		List<StandardPropertyModelParam> list = standardPropertyModelParamRepository.findByDeleteFlag(Constants.FALSE);
		List<StandardPropertyModelParamDto> dtoList = new ArrayList<StandardPropertyModelParamDto>();
		for (StandardPropertyModelParam standardPropertyModelParam : list) {
			StandardPropertyModelParamDto standardPropertyModelParamDto = standardPropertyModelParamDtoMapping.sourceToTarget(standardPropertyModelParam);
			dtoList.add(standardPropertyModelParamDto);
		}
		return dtoList;
	}

	@Override
	public Page<StandardPropertyModelParamDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, standardPropertyModelParamRepository, standardPropertyModelParamDtoMapping);
	}

}
