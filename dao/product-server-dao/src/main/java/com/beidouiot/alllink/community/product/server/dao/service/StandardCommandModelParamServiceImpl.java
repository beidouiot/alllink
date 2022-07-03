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
import com.beidouiot.alllink.community.common.data.entity.product.StandardCommandModelParam;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelParamDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardCommandModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardCommandModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardCommandModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardCommandModelParamService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class StandardCommandModelParamServiceImpl implements StandardCommandModelParamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardCommandModelParamServiceImpl.class);
	
	@Autowired
	private StandardCommandModelParamDtoMapping standardCommandModelParamDtoMapping;
	
	@Autowired
	private StandardCommandModelParamUpdateDtoMapping standardCommandModelParamUpdateDtoMapping;
	
	@Autowired
	private StandardCommandModelParamRepository standardCommandModelParamRepository;		
	
	@Override
	public void saveEntity(StandardCommandModelParamDto standardCommandModelParamDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardCommandModelParamDto = [ {} ]", standardCommandModelParamDto);
		}
		StandardCommandModelParam standardCommandModelParam = standardCommandModelParamDtoMapping.targetToSource(standardCommandModelParamDto);
		standardCommandModelParamRepository.save(standardCommandModelParam);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		standardCommandModelParamRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<StandardCommandModelParam> optional = standardCommandModelParamRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}


		StandardCommandModelParam standardCommandModelParam = optional.get();
		Map<String, Object> map = getHeaderUser();
		standardCommandModelParam.setUpdatedBy(map.get("username").toString());
		standardCommandModelParam.setDeleteFlag(Constants.TRUE);
		standardCommandModelParamRepository.save(standardCommandModelParam);

	}

	@Override
	public void updateEntity(StandardCommandModelParamUpdateDto standardCommandModelParamUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("standardCommandModelParamUpdateDto = [ {} ]", standardCommandModelParamUpdateDto);
		}
		
		if ( standardCommandModelParamUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<StandardCommandModelParam> optional = standardCommandModelParamRepository.findById(standardCommandModelParamUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardCommandModelParam standardCommandModelParam = optional.get();
		standardCommandModelParam = standardCommandModelParamUpdateDtoMapping.targetToSourceForUpdate(standardCommandModelParamUpdateDto,standardCommandModelParam);
		standardCommandModelParamRepository.save(standardCommandModelParam);

	}

	@Override
	public StandardCommandModelParamDto findById(Long id) throws ServiceException {
		return standardCommandModelParamDtoMapping.sourceToTarget(standardCommandModelParamRepository.findById(id).get());
	}

	@Override
	public List<StandardCommandModelParamDto> findAll() throws ServiceException {
		List<StandardCommandModelParam> list = standardCommandModelParamRepository.findByDeleteFlag(Constants.FALSE);
		List<StandardCommandModelParamDto> dtoList = new ArrayList<StandardCommandModelParamDto>();
		for (StandardCommandModelParam standardCommandModelParam : list) {
			StandardCommandModelParamDto standardCommandModelParamDto = standardCommandModelParamDtoMapping.sourceToTarget(standardCommandModelParam);
			dtoList.add(standardCommandModelParamDto);
		}
		return dtoList;
	}

	@Override
	public Page<StandardCommandModelParamDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, standardCommandModelParamRepository, standardCommandModelParamDtoMapping);
	}

}
