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
import com.beidouiot.alllink.community.common.data.entity.product.ProductCommandModelParam;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductCommandModelParamDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductCommandModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductCommandModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductCommandModelParamService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class ProductCommandModelParamServiceImpl implements ProductCommandModelParamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCommandModelParamServiceImpl.class);
	
	@Autowired
	private ProductCommandModelParamDtoMapping productCommandModelParamDtoMapping;
	
	@Autowired
	private ProductCommandModelParamUpdateDtoMapping productCommandModelParamUpdateDtoMapping;
	
	@Autowired
	private ProductCommandModelParamRepository productCommandModelParamRepository;		
	
	@Override
	public void saveEntity(ProductCommandModelParamDto productCommandModelParamDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelParamDto = [ {} ]", productCommandModelParamDto);
		}
		ProductCommandModelParam productCommandModelParam = productCommandModelParamDtoMapping.targetToSource(productCommandModelParamDto);
		productCommandModelParamRepository.save(productCommandModelParam);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		productCommandModelParamRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<ProductCommandModelParam> optional = productCommandModelParamRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}


		ProductCommandModelParam productCommandModelParam = optional.get();
		Map<String, Object> map = getHeaderUser();
		productCommandModelParam.setUpdatedBy(map.get("username").toString());
		productCommandModelParam.setDeleteFlag(Constants.TRUE);
		productCommandModelParamRepository.save(productCommandModelParam);

	}

	@Override
	public void updateEntity(ProductCommandModelParamUpdateDto productCommandModelParamUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelParamUpdateDto = [ {} ]", productCommandModelParamUpdateDto);
		}
		
		if ( productCommandModelParamUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<ProductCommandModelParam> optional = productCommandModelParamRepository.findById(productCommandModelParamUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductCommandModelParam productCommandModelParam = optional.get();
		productCommandModelParam = productCommandModelParamUpdateDtoMapping.targetToSourceForUpdate(productCommandModelParamUpdateDto,productCommandModelParam);
		productCommandModelParamRepository.save(productCommandModelParam);

	}

	@Override
	public ProductCommandModelParamDto findById(Long id) throws ServiceException {
		return productCommandModelParamDtoMapping.sourceToTarget(productCommandModelParamRepository.findById(id).get());
	}

	@Override
	public List<ProductCommandModelParamDto> findAll() throws ServiceException {
		List<ProductCommandModelParam> list = productCommandModelParamRepository.findByDeleteFlag(Constants.FALSE);
		List<ProductCommandModelParamDto> dtoList = new ArrayList<ProductCommandModelParamDto>();
		for (ProductCommandModelParam productCommandModelParam : list) {
			ProductCommandModelParamDto productCommandModelParamDto = productCommandModelParamDtoMapping.sourceToTarget(productCommandModelParam);
			dtoList.add(productCommandModelParamDto);
		}
		return dtoList;
	}

	@Override
	public Page<ProductCommandModelParamDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, productCommandModelParamRepository, productCommandModelParamDtoMapping);
	}

}
