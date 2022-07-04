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
import com.beidouiot.alllink.community.common.data.entity.product.StandardModel;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardModelDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardModelRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardModelService;

/**
*
* @Description 标准物模型业务逻辑实现
* @author longww
* @date 2022年1月5日
*/
@Service
public class StandardModelServiceImpl implements StandardModelService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StandardModelServiceImpl.class);
	
	@Autowired
	private StandardModelDtoMapping standardModelDtoMapping;
	
	@Autowired
	private StandardModelUpdateDtoMapping standardModelUpdateDtoMapping;
	
	@Autowired
	private StandardModelRepository standardModelRepository;
	
	@Override
	public void saveEntity(StandardModelDto standardModelDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardModelDto = [ {} ]", standardModelDto);
		}
		StandardModel standardModel = standardModelDtoMapping.targetToSource(standardModelDto);
		standardModelRepository.save(standardModel);
		
	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		standardModelRepository.deleteById(id);
		
	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<StandardModel> optional = standardModelRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}


		StandardModel standardModel = optional.get();
		Map<String, Object> map = getHeaderUser();
		standardModel.setUpdatedBy(map.get("username").toString());
		standardModel.setDeleteFlag(Constants.TRUE);
		standardModelRepository.save(standardModel);
		
	}

	@Override
	public void updateEntity(StandardModelUpdateDto standardModelUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardModelUpdateDto = [ {} ]", standardModelUpdateDto);
		}
		
		if ( standardModelUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<StandardModel> optional = standardModelRepository.findById(standardModelUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardModel standardModel = optional.get();
		standardModel = standardModelUpdateDtoMapping.targetToSourceForUpdate(standardModelUpdateDto,standardModel);
		standardModelRepository.save(standardModel);
		
	}

	@Override
	public StandardModelDto findById(Long id) throws ServiceException {
		return standardModelDtoMapping.sourceToTarget(standardModelRepository.findById(id).get());
	}

	@Override
	public List<StandardModelDto> findAll() throws ServiceException {
		List<StandardModel> list = standardModelRepository.findByDeleteFlag(Constants.FALSE);
		List<StandardModelDto> dtoList = new ArrayList<StandardModelDto>();
		for (StandardModel standardModel : list) {
			StandardModelDto standardModelDto = standardModelDtoMapping.sourceToTarget(standardModel);
			dtoList.add(standardModelDto);
		}
		return dtoList;
	}

	@Override
	public Page<StandardModelDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, standardModelRepository, standardModelDtoMapping);
	}

}