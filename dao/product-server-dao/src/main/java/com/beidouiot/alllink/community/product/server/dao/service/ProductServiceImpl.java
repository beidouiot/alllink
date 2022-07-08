package com.beidouiot.alllink.community.product.server.dao.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.product.Product;
import com.beidouiot.alllink.community.common.data.entity.product.ProductCommandModel;
import com.beidouiot.alllink.community.common.data.entity.product.ProductEventModel;
import com.beidouiot.alllink.community.common.data.entity.product.ProductPropertyModel;
import com.beidouiot.alllink.community.common.data.entity.product.StandardCommandModel;
import com.beidouiot.alllink.community.common.data.entity.product.StandardEventModel;
import com.beidouiot.alllink.community.common.data.entity.product.StandardPropertyModel;
import com.beidouiot.alllink.community.common.data.mapping.product.server.product.ProductDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.product.server.product.ProductUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductDto;
import com.beidouiot.alllink.community.common.data.xxo.product.dto.ProductUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductCommandModelRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductEventModelRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductPropertyModelRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.ProductRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardCommandModelRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardEventModelRepository;
import com.beidouiot.alllink.community.product.server.dao.repository.StandardPropertyModelRepository;
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

	@Autowired
	private StandardPropertyModelRepository standardPropertyModelRepository;

	@Autowired
	private StandardEventModelRepository standardEventModelRepository;

	@Autowired
	private StandardCommandModelRepository standardCommandModelRepository;

	@Autowired
	private ProductPropertyModelRepository productPropertyModelRepository;

	@Autowired
	private ProductEventModelRepository productEventModelRepository;

	@Autowired
	private ProductCommandModelRepository productCommandModelRepository;

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveEntity(ProductDto productDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productDto = [ {} ]", productDto);
		}
		Product product = productDtoMapping.targetToSource(productDto);
		productRepository.save(product);
		if (productDto.getCopyFlag()) {
			List<StandardPropertyModel> spList = standardPropertyModelRepository
					.findByProductTypeIdAndDeleteFlagAndStatus(product.getProductTypeId(), Constants.FALSE,
							Constants.TRUE);
			List<StandardEventModel> seList = standardEventModelRepository
					.findByProductTypeIdAndDeleteFlagAndStatus(product.getProductTypeId(), Constants.FALSE,
							Constants.TRUE);
			List<StandardCommandModel> scList = standardCommandModelRepository
					.findByProductTypeIdAndDeleteFlagAndStatus(product.getProductTypeId(), Constants.FALSE,
							Constants.TRUE);
		
			List<ProductPropertyModel> ppList = new ArrayList<ProductPropertyModel>();
			spList.stream().forEach(standardPropertyModel ->{
				ProductPropertyModel ppm = new ProductPropertyModel();
				BeanUtils.copyProperties(standardPropertyModel, ppm);
				ppm.setId(null);
				ppm.setCreatedBy(productDto.getUpdatedBy());
				ppm.setUpdatedBy(productDto.getUpdatedBy());
				ppm.setOptLockVersion(null);
				ppm.setStatus(Constants.FALSE);
				ppm.setCreatedDate(null);
				ppm.setUpdatedDate(null);
				ppm.setProductId(product.getId());
				ppList.add(ppm);
			});
			
			List<ProductEventModel> peList = new ArrayList<ProductEventModel>();
			seList.stream().forEach(standardEventModel ->{
				ProductEventModel pem = new ProductEventModel();
				BeanUtils.copyProperties(standardEventModel, pem);
				pem.setId(null);
				pem.setCreatedBy(productDto.getUpdatedBy());
				pem.setUpdatedBy(productDto.getUpdatedBy());
				pem.setOptLockVersion(null);
				pem.setStatus(Constants.FALSE);
				pem.setCreatedDate(null);
				pem.setUpdatedDate(null);
				pem.setProductId(product.getId());
				peList.add(pem);
			});
			
			List<ProductCommandModel> pcList = new ArrayList<ProductCommandModel>();
			scList.stream().forEach(standardCommandModel ->{
				ProductCommandModel pcm = new ProductCommandModel();
				BeanUtils.copyProperties(standardCommandModel, pcm);
				pcm.setId(null);
				pcm.setCreatedBy(productDto.getUpdatedBy());
				pcm.setUpdatedBy(productDto.getUpdatedBy());
				pcm.setOptLockVersion(null);
				pcm.setStatus(Constants.FALSE);
				pcm.setCreatedDate(null);
				pcm.setUpdatedDate(null);
				pcm.setProductId(product.getId());
				pcList.add(pcm);
			});
			
			productPropertyModelRepository.saveAll(ppList);
			productEventModelRepository.saveAll(peList);
			productCommandModelRepository.saveAll(pcList);

		}
	}

	@Override
	public void delete(Long id) throws ServiceException {

		productRepository.deleteById(id);

	}

	@Override
	public void logicalDelete(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		Optional<Product> o = productRepository.findById(id);
		Product p = o.get();
		p.setDeleteFlag(Constants.TRUE);
		Map<String, Object> map = getHeaderUser();
		p.setUpdatedBy(map.get("username").toString());
		productRepository.save(p);

	}

	@Override
	public void updateEntity(ProductUpdateDto productUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("productUpdateDto = [ {} ]", productUpdateDto);
		}

		if (productUpdateDto.getId() == null) {
			throw new IllegalArgumentException("id不能为空");
		}

		Optional<Product> optional = productRepository.findById(productUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}

		Product product = optional.get();
		product = productUpdateDtoMapping.targetToSourceForUpdate(productUpdateDto, product);
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
