package com.beidouiot.alllink.community.product.server.dao.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.product.ProductCommandModel;
import com.beidouiot.alllink.community.common.data.entity.product.ProductCommandModelParam;
import com.beidouiot.alllink.community.common.data.entity.product.ProductEventModel;
import com.beidouiot.alllink.community.common.data.entity.product.ProductEventModelParam;
import com.beidouiot.alllink.community.common.data.entity.product.ProductModel;
import com.beidouiot.alllink.community.common.data.entity.product.ProductModelVersion;
import com.beidouiot.alllink.community.common.data.entity.product.ProductPropertyModel;
import com.beidouiot.alllink.community.common.data.entity.product.ProductPropertyModelParam;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelJsonDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductCommandModelParamJsonDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelJsonDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductEventModelParamJsonDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelJsonDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductModelUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductPropertyModelJsonDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductCommandModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductCommandModelRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductEventModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductEventModelRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductModelVersionRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductPropertyModelParamRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductPropertyModelRepository;
import com.beidouiot.alllink.community.product.server.dao.service.api.ProductModelService;

/**
 *
 * @Description 产品物模型管理业务逻辑实现
 * @author longww
 * @date 2022年1月5日
 */
@Service
public class ProductModelServiceImpl implements ProductModelService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductModelServiceImpl.class);

	@Autowired
	private ProductModelVersionRepository productModelVersionRepository;

	@Autowired
	private ProductPropertyModelRepository productPropertyModelRepository;

	@Autowired
	private ProductEventModelRepository productEventModelRepository;

	@Autowired
	private ProductCommandModelRepository productCommandModelRepository;
	
	@Autowired
	private ProductEventModelParamRepository productEventModelParamRepository;
	
	@Autowired
	private ProductPropertyModelParamRepository productPropertyModelParamRepository;
	
	@Autowired
	private ProductCommandModelParamRepository productCommandModelParamRepository;

	@Override
	public void saveEntity(ProductModelDto productModelDto) throws ServiceException {

	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveAllEntity(List<ProductModelDto> productModelDtoList) throws ServiceException {

	}

	@Override
	public void delete(Long id) throws ServiceException {

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {

	}

	@Override
	public void updateEntity(ProductModelUpdateDto productModelUpdateDto) throws ServiceException {
	
	}

	@Override
	public ProductModelDto findById(Long id) throws ServiceException {
		return null;
	}

	@Override
	public List<ProductModelDto> findAll() throws ServiceException {
		return null;
	}

	@Override
	public Page<ProductModelDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return null;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void publishModel(ProductModelDto productModelDto) throws ServiceException {
		try {
			ProductModelJsonDto productModelJsonDto = new ProductModelJsonDto();
			productModelJsonDto.setProductId(productModelDto.getProductId());
			List<ProductPropertyModel> productPropertyModelList = productPropertyModelRepository
					.findByProductIdAndStatusAndDeleteFlag(productModelDto.getProductId(), Constants.FALSE,
							Constants.FALSE);
			modifyList(productPropertyModelList, productModelDto);
			if (productPropertyModelList != null) {
				productPropertyModelRepository.saveAll(productPropertyModelList);
				productModelJsonDto.setProperties(productPropertyModelsJsonList(productPropertyModelList));
			}
			
			List<ProductPropertyModelParam> productPropertyModelParamList = productPropertyModelParamRepository.findByProductIdAndStatusAndDeleteFlag(productModelDto.getProductId(), Constants.FALSE,
					Constants.FALSE);
			modifyList(productPropertyModelParamList, productModelDto);
			if (productPropertyModelParamList != null) {
				productPropertyModelParamRepository.saveAll(productPropertyModelParamList);
			}
			
			List<ProductEventModelParam> productEventModelParamList = productEventModelParamRepository.findByProductIdAndStatusAndDeleteFlag(productModelDto.getProductId(), Constants.FALSE,
					Constants.FALSE);
			modifyList(productEventModelParamList, productModelDto);
			if (productEventModelParamList != null) {
				productEventModelParamRepository.saveAll(productEventModelParamList);
			}
			
			List<ProductEventModel> productEventModelList = productEventModelRepository
					.findByProductIdAndStatusAndDeleteFlag(productModelDto.getProductId(), Constants.FALSE,
							Constants.FALSE);
			modifyList(productEventModelList, productModelDto);
			if (productEventModelList != null) {
				productEventModelRepository.saveAll(productEventModelList);
				productModelJsonDto.setEvents(productEventModelsJsonList(productEventModelList, productEventModelParamsJsonList(productEventModelParamList)));
			}
			
			List<ProductCommandModelParam> productCommandModelParamList = productCommandModelParamRepository.findByProductIdAndStatusAndDeleteFlag(productModelDto.getProductId(), Constants.FALSE,
					Constants.FALSE);
			modifyList(productCommandModelParamList, productModelDto);
			if (productCommandModelParamList != null) {
				productCommandModelParamRepository.saveAll(productCommandModelParamList);
			}
			
			List<ProductCommandModel> productCommandModelList = productCommandModelRepository
					.findByProductIdAndStatusAndDeleteFlag(productModelDto.getProductId(), Constants.FALSE,
							Constants.FALSE);
			modifyList(productCommandModelList, productModelDto);
			if (productCommandModelList != null) {
				productCommandModelRepository.saveAll(productCommandModelList);
				productModelJsonDto.setCommands(productCommandModelsJsonList(productCommandModelList, productCommandModelParamsJsonList(productCommandModelParamList)));
			}
			
			ProductModel productModel = new ProductModel();
			productModel.setProductId(productModelJsonDto.getProductId());
			productModel.setCommands(productModelJsonDto.getCommands());
			productModel.setEvents(productModelJsonDto.getEvents());
			productModel.setProperties(productModelJsonDto.getProperties());
			ProductModelVersion productModelVersion = new ProductModelVersion();
			productModelVersion.setCreatedBy(productModelDto.getUpdatedBy());
			productModelVersion.setUpdatedBy(productModelDto.getUpdatedBy());
			productModelVersion.setDeleteFlag(Constants.FALSE);
			productModelVersion.setProductId(productModel.getProductId());
			productModelVersion.setProductModel(productModel);
			productModelVersion.setUserFlag(Constants.TRUE);
			productModelVersion.setVersionNumber(System.currentTimeMillis());
			
			ProductModelVersion historyProductModelVersion = productModelVersionRepository.findByProductIdAndUserFlagAndDeleteFlag(productModelJsonDto.getProductId(), Constants.TRUE,Constants.FALSE).get(0);
			historyProductModelVersion.setUserFlag(Constants.FALSE);
			historyProductModelVersion.setUpdatedBy(productModelDto.getUpdatedBy());
			productModelVersionRepository.save(historyProductModelVersion);
			
			productModelVersionRepository.save(productModelVersion);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			LOGGER.error("发布失败", e);
			throw new ServiceException("发布失败");
		}

	}
	
	private List modifyList(List list, ProductModelDto productModelDto)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		if (list == null || list.size() == 0) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			Field status = obj.getClass().getDeclaredField("status");
			status.setAccessible(true);
			status.set(obj, Constants.TRUE);
			Field updatedBy = obj.getClass().getSuperclass().getDeclaredField("updatedBy");
			updatedBy.setAccessible(true);
			updatedBy.set(obj, productModelDto.getUpdatedBy());
			list.set(i, obj);
		}
		return list;
	}
	
	private List<ProductPropertyModelJsonDto> productPropertyModelsJsonList(List<ProductPropertyModel> list) {
		List<ProductPropertyModelJsonDto> ppmjdList = new ArrayList<ProductPropertyModelJsonDto>();
		for (ProductPropertyModel productPropertyModel : list) {
			ProductPropertyModelJsonDto productPropertyModelJsonDto = new ProductPropertyModelJsonDto();
			productPropertyModelJsonDto.setName(productPropertyModel.getName());
			productPropertyModelJsonDto.setCode(productPropertyModel.getCode());
			productPropertyModelJsonDto.setDataType(productPropertyModel.getDataType());
			productPropertyModelJsonDto.setAccessType(productPropertyModel.getAccessType());
			productPropertyModelJsonDto.setDataSpecs(productPropertyModel.getDataSpecs());
			ppmjdList.add(productPropertyModelJsonDto);
		}
		return ppmjdList;
	}
	
	private List<ProductEventModelJsonDto> productEventModelsJsonList(List<ProductEventModel> list, List<ProductEventModelParamJsonDto> pempList) {
		List<ProductEventModelJsonDto> pemjdList = new ArrayList<ProductEventModelJsonDto>();
		for (ProductEventModel productEventModel : list) {
			ProductEventModelJsonDto productEventModelJsonDto = new ProductEventModelJsonDto();
			productEventModelJsonDto.setName(productEventModel.getName());
			productEventModelJsonDto.setCode(productEventModel.getCode());
			productEventModelJsonDto.setRemark(productEventModel.getRemark());
			productEventModelJsonDto.setEventType(productEventModel.getEventType());
			productEventModelJsonDto.setParams(pempList);
			pemjdList.add(productEventModelJsonDto);
		}
		return pemjdList;
	}
	
	private List<ProductEventModelParamJsonDto> productEventModelParamsJsonList(List<ProductEventModelParam> list) {
		List<ProductEventModelParamJsonDto> pemjdList = new ArrayList<ProductEventModelParamJsonDto>();
		for (ProductEventModelParam productEventModelParam : list) {
			ProductEventModelParamJsonDto productEventModelJsonDto = new ProductEventModelParamJsonDto();
			productEventModelJsonDto.setName(productEventModelParam.getName());
			productEventModelJsonDto.setCode(productEventModelParam.getCode());
			productEventModelJsonDto.setDirection(productEventModelParam.getDirection());
			productEventModelJsonDto.setDataType(productEventModelParam.getDataType());
			productEventModelJsonDto.setProductEventModelId(productEventModelParam.getProductEventModelId());
			productEventModelJsonDto.setDataSpecs(productEventModelParam.getDataSpecs());
			pemjdList.add(productEventModelJsonDto);
		}
		return pemjdList;
	}
	
	private List<ProductCommandModelJsonDto> productCommandModelsJsonList(List<ProductCommandModel> list, List<ProductCommandModelParamJsonDto> pempList) {
		List<ProductCommandModelJsonDto> pcmjdList = new ArrayList<ProductCommandModelJsonDto>();
		for (ProductCommandModel productCommandModel : list) {
			ProductCommandModelJsonDto productCommandModelJsonDto = new ProductCommandModelJsonDto();
			productCommandModelJsonDto.setName(productCommandModel.getName());
			productCommandModelJsonDto.setCode(productCommandModel.getCode());
			productCommandModelJsonDto.setRemark(productCommandModel.getRemark());
			productCommandModelJsonDto.setCallMode(productCommandModel.getCallMode());
			productCommandModelJsonDto.setParams(pempList);
			pcmjdList.add(productCommandModelJsonDto);
		}
		return pcmjdList;
	}
	
	private List<ProductCommandModelParamJsonDto> productCommandModelParamsJsonList(List<ProductCommandModelParam> list) {
		List<ProductCommandModelParamJsonDto> pcmjdList = new ArrayList<ProductCommandModelParamJsonDto>();
		for (ProductCommandModelParam productCommandModelParam : list) {
			ProductCommandModelParamJsonDto productCommandModelParamJsonDto = new ProductCommandModelParamJsonDto();
			productCommandModelParamJsonDto.setName(productCommandModelParam.getName());
			productCommandModelParamJsonDto.setCode(productCommandModelParam.getCode());
			productCommandModelParamJsonDto.setDirection(productCommandModelParam.getDirection());
			productCommandModelParamJsonDto.setDataType(productCommandModelParam.getDataType());
			productCommandModelParamJsonDto.setProductCommandModelId(productCommandModelParam.getProductCommandModelId());
			productCommandModelParamJsonDto.setDataSpecs(productCommandModelParam.getDataSpecs());
			pcmjdList.add(productCommandModelParamJsonDto);
		}
		return pcmjdList;
	}

}
