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
import com.beidouiot.alllink.community.common.data.entity.product.StandardEventModelParam;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardEventModelParamDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardEventModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardEventModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardEventModelParamService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class StandardEventModelParamServiceImpl implements StandardEventModelParamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardEventModelParamServiceImpl.class);

	@Autowired
	private StandardEventModelParamDtoMapping standardEventModelParamDtoMapping;
	
	@Autowired
	private StandardEventModelParamUpdateDtoMapping standardEventModelParamUpdateDtoMapping;

	@Autowired
	private StandardEventModelParamRepository standardEventModelParamRepository;	
	
	@Override
	public void saveEntity(StandardEventModelParamDto standardEventModelParamDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("standardEventModelParamDto = [ {} ]", standardEventModelParamDto);
		}
		StandardEventModelParam standardEventModelParam = standardEventModelParamDtoMapping.targetToSource(standardEventModelParamDto);
		standardEventModelParamRepository.save(standardEventModelParam);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		standardEventModelParamRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<StandardEventModelParam> optional = standardEventModelParamRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardEventModelParam standardEventModelParam = optional.get();
		Map<String, Object> map = getHeaderUser();
		standardEventModelParam.setUpdatedBy(map.get("username").toString());
		standardEventModelParam.setDeleteFlag(Constants.TRUE);
		standardEventModelParamRepository.save(standardEventModelParam);

	}

	@Override
	public void updateEntity(StandardEventModelParamUpdateDto standardEventModelParamUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("standardEventModelParamUpdateDto = [ {} ]", standardEventModelParamUpdateDto);
		}

		if (standardEventModelParamUpdateDto.getId() == null) {
			throw new IllegalArgumentException("id不能为空");
		}

		Optional<StandardEventModelParam> optional = standardEventModelParamRepository.findById(standardEventModelParamUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardEventModelParam standardEventModelParam = optional.get();
		standardEventModelParam = standardEventModelParamUpdateDtoMapping.targetToSourceForUpdate(standardEventModelParamUpdateDto,standardEventModelParam);
		standardEventModelParamRepository.save(standardEventModelParam);

	}

	@Override
	public StandardEventModelParamDto findById(Long id) throws ServiceException {
		return standardEventModelParamDtoMapping.sourceToTarget(standardEventModelParamRepository.findById(id).get());
	}

	@Override
	public List<StandardEventModelParamDto> findAll() throws ServiceException {
		List<StandardEventModelParam> list = standardEventModelParamRepository.findByDeleteFlag(Constants.FALSE);
		List<StandardEventModelParamDto> dtoList = new ArrayList<StandardEventModelParamDto>();
		for (StandardEventModelParam standardEventModelParam : list) {
			StandardEventModelParamDto standardEventModelParamDto = standardEventModelParamDtoMapping
					.sourceToTarget(standardEventModelParam);
			dtoList.add(standardEventModelParamDto);
		}
		return dtoList;
	}

	@Override
	public Page<StandardEventModelParamDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, standardEventModelParamRepository,
				standardEventModelParamDtoMapping);
	}

}
