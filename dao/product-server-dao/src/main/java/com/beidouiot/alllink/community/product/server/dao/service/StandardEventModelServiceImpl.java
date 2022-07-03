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
import com.beidouiot.alllink.community.common.data.entity.product.StandardEventModel;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardEventModelDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.standardModel.StandardEventModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.StandardEventModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardEventModelRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.StandardEventModelService;

/**
 *
 * @Description TODO
 * @author longww
 * @date 2022年2月14日
 */
@Service
public class StandardEventModelServiceImpl implements StandardEventModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StandardEventModelServiceImpl.class);

	@Autowired
	private StandardEventModelDtoMapping standardEventModelDtoMapping;
	
	@Autowired
	private StandardEventModelUpdateDtoMapping standardEventModelUpdateDtoMapping;

	@Autowired
	private StandardEventModelRepository standardEventModelRepository;

	@Override
	public void saveEntity(StandardEventModelDto standardEventModelDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("standardEventModelDto = [ {} ]", standardEventModelDto);
		}
		StandardEventModel standardEventModel = standardEventModelDtoMapping.targetToSource(standardEventModelDto);
		standardEventModelRepository.save(standardEventModel);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		standardEventModelRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<StandardEventModel> optional = standardEventModelRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardEventModel standardEventModel = optional.get();
		Map<String, Object> map = getHeaderUser();
		standardEventModel.setUpdatedBy(map.get("username").toString());
		standardEventModel.setDeleteFlag(Constants.TRUE);
		standardEventModelRepository.save(standardEventModel);

	}

	@Override
	public void updateEntity(StandardEventModelUpdateDto standardEventModelUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("standardEventModelDto = [ {} ]", standardEventModelUpdateDto);
		}

		if (standardEventModelUpdateDto.getId() == null) {
			throw new IllegalArgumentException("id不能为空");
		}

		Optional<StandardEventModel> optional = standardEventModelRepository.findById(standardEventModelUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		StandardEventModel standardEventModel = optional.get();
		standardEventModel = standardEventModelUpdateDtoMapping.targetToSourceForUpdate(standardEventModelUpdateDto,standardEventModel);
		standardEventModelRepository.save(standardEventModel);

	}

	@Override
	public StandardEventModelDto findById(Long id) throws ServiceException {
		return standardEventModelDtoMapping.sourceToTarget(standardEventModelRepository.findById(id).get());
	}

	@Override
	public List<StandardEventModelDto> findAll() throws ServiceException {
		List<StandardEventModel> list = standardEventModelRepository.findByDeleteFlag(Constants.FALSE);
		List<StandardEventModelDto> dtoList = new ArrayList<StandardEventModelDto>();
		for (StandardEventModel standardEventModel : list) {
			StandardEventModelDto standardEventModelDto = standardEventModelDtoMapping
					.sourceToTarget(standardEventModel);
			dtoList.add(standardEventModelDto);
		}
		return dtoList;
	}

	@Override
	public Page<StandardEventModelDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, standardEventModelRepository,
				standardEventModelDtoMapping);
	}

}
