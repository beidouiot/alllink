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
import com.beidouiot.alllink.community.common.data.entity.product.ProductPropertyModelParam;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductPropertyModelParamDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductPropertyModelParamUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelParamDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelParamUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductPropertyModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductPropertyModelParamService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class ProductPropertyModelParamServiceImpl implements ProductPropertyModelParamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPropertyModelParamServiceImpl.class);
	
	@Autowired
	private ProductPropertyModelParamDtoMapping productPropertyModelParamDtoMapping;
	
	@Autowired
	private ProductPropertyModelParamUpdateDtoMapping productPropertyModelParamUpdateDtoMapping;
	
	@Autowired
	private ProductPropertyModelParamRepository productPropertyModelParamRepository;
	
	@Override
	public void saveEntity(ProductPropertyModelParamDto productPropertyModelParamDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelParamDto = [ {} ]", productPropertyModelParamDto);
		}
		ProductPropertyModelParam productPropertyModelParam = productPropertyModelParamDtoMapping.targetToSource(productPropertyModelParamDto);
		productPropertyModelParamRepository.save(productPropertyModelParam);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		productPropertyModelParamRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<ProductPropertyModelParam> optional = productPropertyModelParamRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductPropertyModelParam productPropertyModelParam = optional.get();
		Map<String, Object> map = getHeaderUser();
		productPropertyModelParam.setUpdatedBy(map.get("username").toString());
		productPropertyModelParam.setDeleteFlag(Constants.TRUE);
		productPropertyModelParamRepository.save(productPropertyModelParam);

	}

	@Override
	public void updateEntity(ProductPropertyModelParamUpdateDto productPropertyModelParamUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelParamUpdateDto = [ {} ]", productPropertyModelParamUpdateDto);
		}
		
		if ( productPropertyModelParamUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<ProductPropertyModelParam> optional = productPropertyModelParamRepository.findById(productPropertyModelParamUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductPropertyModelParam productPropertyModelParam = optional.get();
		productPropertyModelParam = productPropertyModelParamUpdateDtoMapping.targetToSourceForUpdate(productPropertyModelParamUpdateDto,productPropertyModelParam);
		productPropertyModelParamRepository.save(productPropertyModelParam);

	}

	@Override
	public ProductPropertyModelParamDto findById(Long id) throws ServiceException {
		return productPropertyModelParamDtoMapping.sourceToTarget(productPropertyModelParamRepository.findById(id).get());
	}

	@Override
	public List<ProductPropertyModelParamDto> findAll() throws ServiceException {
		List<ProductPropertyModelParam> list = productPropertyModelParamRepository.findByDeleteFlag(Constants.FALSE);
		List<ProductPropertyModelParamDto> dtoList = new ArrayList<ProductPropertyModelParamDto>();
		for (ProductPropertyModelParam productPropertyModelParam : list) {
			ProductPropertyModelParamDto productPropertyModelParamDto = productPropertyModelParamDtoMapping.sourceToTarget(productPropertyModelParam);
			dtoList.add(productPropertyModelParamDto);
		}
		return dtoList;
	}

	@Override
	public Page<ProductPropertyModelParamDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, productPropertyModelParamRepository, productPropertyModelParamDtoMapping);
	}

}
