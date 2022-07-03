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
import com.beidouiot.alllink.community.common.data.entity.product.ProductPropertyModel;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductPropertyModelDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductPropertyModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductPropertyModelRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductPropertyModelService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class ProductPropertyModelServiceImpl implements ProductPropertyModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductPropertyModelServiceImpl.class);
	
	@Autowired
	private ProductPropertyModelDtoMapping productPropertyModelDtoMapping;
	
	@Autowired
	private ProductPropertyModelUpdateDtoMapping productPropertyModelUpdateDtoMapping;
	
	@Autowired
	private ProductPropertyModelRepository productPropertyModelRepository;
	
	@Override
	public void saveEntity(ProductPropertyModelDto productPropertyModelDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelDto = [ {} ]", productPropertyModelDto);
		}
		ProductPropertyModel productPropertyModel = productPropertyModelDtoMapping.targetToSource(productPropertyModelDto);
		productPropertyModelRepository.save(productPropertyModel);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		productPropertyModelRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<ProductPropertyModel> optional = productPropertyModelRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductPropertyModel productPropertyModel = optional.get();
		Map<String, Object> map = getHeaderUser();
		productPropertyModel.setUpdatedBy(map.get("username").toString());
		productPropertyModel.setDeleteFlag(Constants.TRUE);
		productPropertyModelRepository.save(productPropertyModel);

	}

	@Override
	public void updateEntity(ProductPropertyModelUpdateDto productPropertyModelUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productPropertyModelUpdateDto = [ {} ]", productPropertyModelUpdateDto);
		}
		
		if ( productPropertyModelUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<ProductPropertyModel> optional = productPropertyModelRepository.findById(productPropertyModelUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductPropertyModel productPropertyModel = optional.get();
		productPropertyModel = productPropertyModelUpdateDtoMapping.targetToSourceForUpdate(productPropertyModelUpdateDto,productPropertyModel);
		productPropertyModelRepository.save(productPropertyModel);

	}

	@Override
	public ProductPropertyModelDto findById(Long id) throws ServiceException {
		return productPropertyModelDtoMapping.sourceToTarget(productPropertyModelRepository.findById(id).get());
	}

	@Override
	public List<ProductPropertyModelDto> findAll() throws ServiceException {
		List<ProductPropertyModel> list = productPropertyModelRepository.findByDeleteFlag(Constants.FALSE);
		List<ProductPropertyModelDto> dtoList = new ArrayList<ProductPropertyModelDto>();
		for (ProductPropertyModel productPropertyModel : list) {
			ProductPropertyModelDto productPropertyModelDto = productPropertyModelDtoMapping.sourceToTarget(productPropertyModel);
			dtoList.add(productPropertyModelDto);
		}
		return dtoList;
	}

	@Override
	public Page<ProductPropertyModelDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, productPropertyModelRepository, productPropertyModelDtoMapping);
	}

}
