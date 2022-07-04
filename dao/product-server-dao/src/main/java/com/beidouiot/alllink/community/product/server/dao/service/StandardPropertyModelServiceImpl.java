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
import com.beidouiot.alllink.community.common.data.entity.product.StandardPropertyModel;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardPropertyModelDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardPropertyModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardPropertyModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardPropertyModelRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardPropertyModelService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class StandardPropertyModelServiceImpl implements StandardPropertyModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardPropertyModelServiceImpl.class);
	
	@Autowired
	private StandardPropertyModelDtoMapping standardPropertyModelDtoMapping;
	
	@Autowired
	private StandardPropertyModelUpdateDtoMapping standardPropertyModelUpdateDtoMapping;
	
	@Autowired
	private StandardPropertyModelRepository standardPropertyModelRepository;
	
	@Override
	public void saveEntity(StandardPropertyModelDto standardPropertyModelDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardPropertyModelDto = [ {} ]", standardPropertyModelDto);
		}
		StandardPropertyModel standardPropertyModel = standardPropertyModelDtoMapping.targetToSource(standardPropertyModelDto);
		standardPropertyModelRepository.save(standardPropertyModel);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		standardPropertyModelRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<StandardPropertyModel> optional = standardPropertyModelRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardPropertyModel standardPropertyModel = optional.get();
		Map<String, Object> map = getHeaderUser();
		standardPropertyModel.setUpdatedBy(map.get("username").toString());
		standardPropertyModel.setDeleteFlag(Constants.TRUE);
		standardPropertyModelRepository.save(standardPropertyModel);

	}

	@Override
	public void updateEntity(StandardPropertyModelUpdateDto standardPropertyModelUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardPropertyModelUpdateDto = [ {} ]", standardPropertyModelUpdateDto);
		}
		
		if ( standardPropertyModelUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<StandardPropertyModel> optional = standardPropertyModelRepository.findById(standardPropertyModelUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardPropertyModel standardPropertyModel = optional.get();
		standardPropertyModel = standardPropertyModelUpdateDtoMapping.targetToSourceForUpdate(standardPropertyModelUpdateDto,standardPropertyModel);
		standardPropertyModelRepository.save(standardPropertyModel);

	}

	@Override
	public StandardPropertyModelDto findById(Long id) throws ServiceException {
		return standardPropertyModelDtoMapping.sourceToTarget(standardPropertyModelRepository.findById(id).get());
	}

	@Override
	public List<StandardPropertyModelDto> findAll() throws ServiceException {
		List<StandardPropertyModel> list = standardPropertyModelRepository.findByDeleteFlag(Constants.FALSE);
		List<StandardPropertyModelDto> listDto = standardPropertyModelDtoMapping.sourceToTarget(list);
		return listDto;
	}

	@Override
	public Page<StandardPropertyModelDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, standardPropertyModelRepository, standardPropertyModelDtoMapping);
	}

}
