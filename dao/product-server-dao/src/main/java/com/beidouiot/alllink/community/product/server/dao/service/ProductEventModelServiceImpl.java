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
import com.beidouiot.alllink.community.common.data.entity.product.ProductEventModel;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductEventModelDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductEventModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductEventModelRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductEventModelService;

/**
 *
 * @Description TODO
 * @author longww
 * @date 2022年2月14日
 */
@Service
public class ProductEventModelServiceImpl implements ProductEventModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventModelServiceImpl.class);

	@Autowired
	private ProductEventModelDtoMapping productEventModelDtoMapping;
	
	@Autowired
	private ProductEventModelUpdateDtoMapping productEventModelUpdateDtoMapping;

	@Autowired
	private ProductEventModelRepository productEventModelRepository;

	@Override
	public void saveEntity(ProductEventModelDto productEventModelDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productEventModelDto = [ {} ]", productEventModelDto);
		}
		ProductEventModel productEventModel = productEventModelDtoMapping.targetToSource(productEventModelDto);
		productEventModelRepository.save(productEventModel);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		productEventModelRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<ProductEventModel> optional = productEventModelRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductEventModel productEventModel = optional.get();
		Map<String, Object> map = getHeaderUser();
		productEventModel.setUpdatedBy(map.get("username").toString());
		productEventModel.setDeleteFlag(Constants.TRUE);
		productEventModelRepository.save(productEventModel);

	}

	@Override
	public void updateEntity(ProductEventModelUpdateDto productEventModelUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productEventModelUpdateDto = [ {} ]", productEventModelUpdateDto);
		}

		if (productEventModelUpdateDto.getId() == null) {
			throw new IllegalArgumentException("id不能为空");
		}

		Optional<ProductEventModel> optional = productEventModelRepository.findById(productEventModelUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductEventModel productEventModel = optional.get();
		productEventModel = productEventModelUpdateDtoMapping.targetToSourceForUpdate(productEventModelUpdateDto,productEventModel);
		productEventModelRepository.save(productEventModel);

	}

	@Override
	public ProductEventModelDto findById(Long id) throws ServiceException {
		return productEventModelDtoMapping.sourceToTarget(productEventModelRepository.findById(id).get());
	}

	@Override
	public List<ProductEventModelDto> findAll() throws ServiceException {
		List<ProductEventModel> list = productEventModelRepository.findByDeleteFlag(Constants.FALSE);
		List<ProductEventModelDto> dtoList = new ArrayList<ProductEventModelDto>();
		for (ProductEventModel productEventModel : list) {
			ProductEventModelDto productEventModelDto = productEventModelDtoMapping
					.sourceToTarget(productEventModel);
			dtoList.add(productEventModelDto);
		}
		return dtoList;
	}

	@Override
	public Page<ProductEventModelDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, productEventModelRepository,
				productEventModelDtoMapping);
	}

}
