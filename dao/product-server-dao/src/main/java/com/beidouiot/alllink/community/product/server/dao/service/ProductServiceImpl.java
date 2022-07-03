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
import com.beidouiot.alllink.community.common.data.entity.product.Product;
import com.beidouiot.alllink.community.common.data.mapping.product.server.product.ProductDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.product.ProductUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductService;

/**
*
* @Description 产品管理业务逻辑实现
* @author longww
* @date 2022年1月5日
*/
@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeServiceImpl.class);
	
	@Autowired
	private ProductDtoMapping productDtoMapping;
	
	@Autowired
	private ProductUpdateDtoMapping productUpdateDtoMapping;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public void saveEntity(ProductDto productDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productDto = [ {} ]", productDto);
		}
		Product product = productDtoMapping.targetToSource(productDto);
		productRepository.save(product);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		
		productRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateEntity(ProductUpdateDto productUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productUpdateDto = [ {} ]", productUpdateDto);
		}
		
		if ( productUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<Product> optional = productRepository.findById(productUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		Product product = optional.get();
		product = productUpdateDtoMapping.targetToSourceForUpdate(productUpdateDto,product);
		productRepository.save(product);

	}

	@Override
	public ProductDto findById(Long id) throws ServiceException {
		return productDtoMapping.sourceToTarget(productRepository.findById(id).get());
	}

	@Override
	public List<ProductDto> findAll() throws ServiceException {
		List<Product> list = productRepository.findByDeleteFlag(Constants.FALSE);
		List<ProductDto> dtoList = new ArrayList<ProductDto>();
		for (Product product : list) {
			ProductDto productDto = productDtoMapping.sourceToTarget(product);
			dtoList.add(productDto);
		}
		return dtoList;
	}

	@Override
	public Page<ProductDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, productRepository, productDtoMapping);
	}

}
