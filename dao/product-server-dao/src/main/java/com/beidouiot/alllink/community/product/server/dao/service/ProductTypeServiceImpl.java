package com.beidouiot.alllink.community.product.server.dao.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.product.IndustryTypeProductType;
import com.beidouiot.alllink.community.common.data.entity.product.IndustryTypeProductTypeKey;
import com.beidouiot.alllink.community.common.data.entity.product.ProductType;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productType.ProductTypeDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.productType.ProductTypeUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeIndustryTypeDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductTypeUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.IndustryTypeProductTypeRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductTypeRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductTypeService;

/**
 *
 * @Description 产品类别业务逻辑实现
 * @author longww
 * @date 2022年1月4日
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTypeServiceImpl.class);

	@Autowired
	private ProductTypeDtoMapping productTypeDtoMapping;
	
	@Autowired
	private ProductTypeUpdateDtoMapping productTypeUpdateDtoMapping;

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Autowired
	private IndustryTypeProductTypeRepository industryTypeProductTypeRepository;

	@Override
	public void saveEntity(ProductTypeDto productTypeDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productTypeDto = [ {} ]", productTypeDto);
		}
		ProductType productType = productTypeDtoMapping.targetToSource(productTypeDto);
		productTypeRepository.save(productType);
	}

	@Override
	public void delete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		List<IndustryTypeProductType> industryTypeProductTypes = industryTypeProductTypeRepository
				.findIndustryTypeProductTypesByProductTypeId(id);
		if (null != industryTypeProductTypes && industryTypeProductTypes.size() > 0) {
			throw new CanNotDeleteDataException("行业类别已使用，不能删除");
		}
		productTypeRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		if (id == null) {
			throw new IllegalArgumentException("id不能为空");
		}
		Optional<ProductType> optional = productTypeRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		List<IndustryTypeProductType> industryTypeProductTypes = industryTypeProductTypeRepository
				.findIndustryTypeProductTypesByProductTypeId(id);
		if (null != industryTypeProductTypes && industryTypeProductTypes.size() > 0) {
			throw new CanNotDeleteDataException("行业类别已使用，不能删除");
		}

		ProductType productType = optional.get();
		Map<String, Object> map = getHeaderUser();
		productType.setUpdatedBy(map.get("username").toString());
		productType.setDeleteFlag(Constants.TRUE);
		productTypeRepository.save(productType);

	}

	@Override
	public void updateEntity(ProductTypeUpdateDto productTypeUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productTypeUpdateDto = [ {} ]", productTypeUpdateDto);
		}

		if (productTypeUpdateDto.getId() == null) {
			throw new IllegalArgumentException("id不能为空");
		}

		Optional<ProductType> optional = productTypeRepository.findById(productTypeUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		ProductType productType = optional.get();
		productType = productTypeUpdateDtoMapping.targetToSourceForUpdate(productTypeUpdateDto,productType);
		productTypeRepository.save(productType);

	}

	@Override
	public ProductTypeDto findById(Long id) throws ServiceException {
		return productTypeDtoMapping.sourceToTarget(productTypeRepository.findById(id).get());
	}

	@Override
	public List<ProductTypeDto> findAll() throws ServiceException {
		List<ProductType> list = productTypeRepository.findByDeleteFlag(Constants.FALSE);
		List<ProductTypeDto> dtoList = new ArrayList<ProductTypeDto>();
		for (ProductType productType : list) {
			ProductTypeDto productTypeDto = productTypeDtoMapping.sourceToTarget(productType);
			dtoList.add(productTypeDto);
		}
		return dtoList;

	}

	@Override
	public Page<ProductTypeDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, productTypeRepository, productTypeDtoMapping);
	}

	@Override
	public void saveProductTypeIndustryType(ProductTypeIndustryTypeDto productTypeIndustryTypeDto)
			throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productTypeIndustryTypeDto = [ {} ]", productTypeIndustryTypeDto);
		}

		List<Long> list = productTypeIndustryTypeDto.getIndustryTypeIds();
		List<IndustryTypeProductType> industryTypeProductTypeList = industryTypeProductTypeRepository
				.findIndustryTypeProductTypesByProductTypeId(productTypeIndustryTypeDto.getProductTypeId());
		
		if (list!= null && list.size() > 0) {
			IndustryTypeProductType industryTypeProductTypes[] = new IndustryTypeProductType[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Long industryTypeId = list.get(i);
				IndustryTypeProductTypeKey industryTypeProductTypeKey = new IndustryTypeProductTypeKey();
				industryTypeProductTypeKey.setIndustryTypeId(industryTypeId);
				industryTypeProductTypeKey.setProductTypeId(productTypeIndustryTypeDto.getProductTypeId());
				IndustryTypeProductType industryTypeProductType = new IndustryTypeProductType();
				industryTypeProductType.setId(industryTypeProductTypeKey);
				industryTypeProductTypes[i] = industryTypeProductType;
				if (industryTypeProductTypeList != null && industryTypeProductTypeList.size() > 0) {
					industryTypeProductTypeList.remove(industryTypeProductType);
				}
			}
			industryTypeProductTypeRepository.saveAll(Arrays.asList(industryTypeProductTypes));
			if (industryTypeProductTypeList != null && industryTypeProductTypeList.size() > 0) {
				IndustryTypeProductType deleteIndustryTypeProductTypes[] = new IndustryTypeProductType[industryTypeProductTypeList.size()];
				for (int i = 0; i < industryTypeProductTypeList.size(); i++) {
					deleteIndustryTypeProductTypes[i] = industryTypeProductTypeList.get(i);
				}
				industryTypeProductTypeRepository.deleteAll(Arrays.asList(deleteIndustryTypeProductTypes));
			}
		} else {
			if (industryTypeProductTypeList != null && industryTypeProductTypeList.size() > 0) {
				IndustryTypeProductType deleteIndustryTypeProductTypes[] = new IndustryTypeProductType[industryTypeProductTypeList.size()];
				for (int i = 0; i < industryTypeProductTypeList.size(); i++) {
					deleteIndustryTypeProductTypes[i] = industryTypeProductTypeList.get(i);
				}
				industryTypeProductTypeRepository.deleteAll(Arrays.asList(deleteIndustryTypeProductTypes));
			}
		}
	}

}
