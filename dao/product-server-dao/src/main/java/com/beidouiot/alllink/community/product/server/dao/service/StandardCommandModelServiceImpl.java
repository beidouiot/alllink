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
import com.beidouiot.alllink.community.common.data.entity.product.StandardCommandModel;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardCommandModelRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardCommandModelService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class StandardCommandModelServiceImpl implements StandardCommandModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardCommandModelServiceImpl.class);
	
	@Autowired
	private StandardCommandModelDtoMapping standardCommandModelDtoMapping;
	
	@Autowired
	private StandardCommandModelUpdateDtoMapping standardCommandModelUpdateDtoMapping;
	
	@Autowired
	private StandardCommandModelRepository standardCommandModelRepository;	
	
	@Override
	public void saveEntity(StandardCommandModelDto standardCommandModelDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardCommandModelDto = [ {} ]", standardCommandModelDto);
		}
		StandardCommandModel standardCommandModel = standardCommandModelDtoMapping.targetToSource(standardCommandModelDto);
		standardCommandModelRepository.save(standardCommandModel);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		standardCommandModelRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<StandardCommandModel> optional = standardCommandModelRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}


		StandardCommandModel standardCommandModel = optional.get();
		Map<String, Object> map = getHeaderUser();
		standardCommandModel.setUpdatedBy(map.get("username").toString());
		standardCommandModel.setDeleteFlag(Constants.TRUE);
		standardCommandModelRepository.save(standardCommandModel);

	}

	@Override
	public void updateEntity(StandardCommandModelUpdateDto standardCommandModelUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardCommandModelDto = [ {} ]", standardCommandModelUpdateDto);
		}
		
		if ( standardCommandModelUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<StandardCommandModel> optional = standardCommandModelRepository.findById(standardCommandModelUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardCommandModel standardCommandModel = optional.get();
		standardCommandModel = standardCommandModelUpdateDtoMapping.targetToSourceForUpdate(standardCommandModelUpdateDto,standardCommandModel);
		standardCommandModelRepository.save(standardCommandModel);

	}

	@Override
	public StandardCommandModelDto findById(Long id) throws ServiceException {
		return standardCommandModelDtoMapping.sourceToTarget(standardCommandModelRepository.findById(id).get());
	}

	@Override
	public List<StandardCommandModelDto> findAll() throws ServiceException {
		List<StandardCommandModel> list = standardCommandModelRepository.findByDeleteFlag(Constants.FALSE);
		List<StandardCommandModelDto> dtoList = new ArrayList<StandardCommandModelDto>();
		for (StandardCommandModel standardCommandModel : list) {
			StandardCommandModelDto standardCommandModelDto = standardCommandModelDtoMapping.sourceToTarget(standardCommandModel);
			dtoList.add(standardCommandModelDto);
		}
		return dtoList;
	}

	@Override
	public Page<StandardCommandModelDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, standardCommandModelRepository, standardCommandModelDtoMapping);
	}

}
