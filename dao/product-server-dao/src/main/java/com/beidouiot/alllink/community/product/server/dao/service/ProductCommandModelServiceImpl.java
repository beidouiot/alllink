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
import com.beidouiot.alllink.community.common.data.entity.product.ProductCommandModel;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductCommandModelDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productModel.ProductCommandModelUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductCommandModelRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductCommandModelService;

/**
*
* @Description TODO
* @author longww
* @date 2022年2月14日
*/
@Service
public class ProductCommandModelServiceImpl implements ProductCommandModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCommandModelServiceImpl.class);
	
	@Autowired
	private ProductCommandModelDtoMapping productCommandModelDtoMapping;
	
	@Autowired
	private ProductCommandModelUpdateDtoMapping productCommandModelUpdateDtoMapping;
	
	@Autowired
	private ProductCommandModelRepository productCommandModelRepository;	
	
	@Override
	public void saveEntity(ProductCommandModelDto productCommandModelDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelDto = [ {} ]", productCommandModelDto);
		}
		ProductCommandModel productCommandModel = productCommandModelDtoMapping.targetToSource(productCommandModelDto);
		productCommandModelRepository.save(productCommandModel);

	}

	@Override
	public void delete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		productCommandModelRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if ( id == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<ProductCommandModel> optional = productCommandModelRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}


		ProductCommandModel productCommandModel = optional.get();
		Map<String, Object> map = getHeaderUser();
		productCommandModel.setUpdatedBy(map.get("username").toString());
		productCommandModel.setDeleteFlag(Constants.TRUE);
		productCommandModelRepository.save(productCommandModel);

	}

	@Override
	public void updateEntity(ProductCommandModelUpdateDto productCommandModelUpdateDto) throws ServiceException {
		if ( LOGGER.isDebugEnabled() ) {
			LOGGER.debug("productCommandModelUpdateDto = [ {} ]", productCommandModelUpdateDto);
		}
		
		if ( productCommandModelUpdateDto.getId() == null ) {
			throw new IllegalArgumentException("id不能为空");
		}
		
		Optional<ProductCommandModel> optional = productCommandModelRepository.findById(productCommandModelUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductCommandModel productCommandModel = optional.get();
		productCommandModel = productCommandModelUpdateDtoMapping.targetToSourceForUpdate(productCommandModelUpdateDto,productCommandModel);
		productCommandModelRepository.save(productCommandModel);

	}

	@Override
	public ProductCommandModelDto findById(Long id) throws ServiceException {
		return productCommandModelDtoMapping.sourceToTarget(productCommandModelRepository.findById(id).get());
	}

	@Override
	public List<ProductCommandModelDto> findAll() throws ServiceException {
		List<ProductCommandModel> list = productCommandModelRepository.findByDeleteFlag(Constants.FALSE);
		List<ProductCommandModelDto> dtoList = new ArrayList<ProductCommandModelDto>();
		for (ProductCommandModel productCommandModel : list) {
			ProductCommandModelDto productCommandModelDto = productCommandModelDtoMapping.sourceToTarget(productCommandModel);
			dtoList.add(productCommandModelDto);
		}
		return dtoList;
	}

	@Override
	public Page<ProductCommandModelDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, productCommandModelRepository, productCommandModelDtoMapping);
	}

}
