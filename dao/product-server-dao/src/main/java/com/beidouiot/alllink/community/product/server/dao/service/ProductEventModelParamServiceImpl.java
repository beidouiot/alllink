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
import com.beidouiot.alllink.community.common.data.entity.product.ProductEventModelParam;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductEventModelParamDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductEventModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductEventModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductEventModelParamService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class ProductEventModelParamServiceImpl implements ProductEventModelParamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventModelParamServiceImpl.class);

	@Autowired
	private ProductEventModelParamDtoMapping productEventModelParamDtoMapping;
	
	@Autowired
	private ProductEventModelParamUpdateDtoMapping productEventModelParamUpdateDtoMapping;

	@Autowired
	private ProductEventModelParamRepository productEventModelParamRepository;	
	
	@Override
	public void saveEntity(ProductEventModelParamDto productEventModelParamDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productEventModelParamDto = [ {} ]", productEventModelParamDto);
		}
		ProductEventModelParam productEventModelParam = productEventModelParamDtoMapping.targetToSource(productEventModelParamDto);
		productEventModelParamRepository.save(productEventModelParam);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		productEventModelParamRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<ProductEventModelParam> optional = productEventModelParamRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductEventModelParam productEventModelParam = optional.get();
		Map<String, Object> map = getHeaderUser();
		productEventModelParam.setUpdatedBy(map.get("username").toString());
		productEventModelParam.setDeleteFlag(Constants.TRUE);
		productEventModelParamRepository.save(productEventModelParam);

	}

	@Override
	public void updateEntity(ProductEventModelParamUpdateDto productEventModelParamUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productEventModelParamUpdateDto = [ {} ]", productEventModelParamUpdateDto);
		}

		if (productEventModelParamUpdateDto.getId() == null) {
			throw new IllegalArgumentException("id不能为空");
		}

		Optional<ProductEventModelParam> optional = productEventModelParamRepository.findById(productEventModelParamUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductEventModelParam productEventModelParam = optional.get();
		productEventModelParam = productEventModelParamUpdateDtoMapping.targetToSourceForUpdate(productEventModelParamUpdateDto,productEventModelParam);
		productEventModelParamRepository.save(productEventModelParam);

	}

	@Override
	public ProductEventModelParamDto findById(Long id) throws ServiceException {
		return productEventModelParamDtoMapping.sourceToTarget(productEventModelParamRepository.findById(id).get());
	}

	@Override
	public List<ProductEventModelParamDto> findAll() throws ServiceException {
		List<ProductEventModelParam> list = productEventModelParamRepository.findByDeleteFlag(Constants.FALSE);
		List<ProductEventModelParamDto> dtoList = new ArrayList<ProductEventModelParamDto>();
		for (ProductEventModelParam productEventModelParam : list) {
			ProductEventModelParamDto productEventModelParamDto = productEventModelParamDtoMapping
					.sourceToTarget(productEventModelParam);
			dtoList.add(productEventModelParamDto);
		}
		return dtoList;
	}

	@Override
	public Page<ProductEventModelParamDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, productEventModelParamRepository,
				productEventModelParamDtoMapping);
	}

}
