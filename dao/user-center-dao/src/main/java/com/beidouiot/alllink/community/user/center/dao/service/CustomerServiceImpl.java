package com.beidouiot.alllink.community.user.center.dao.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.entity.user.center.Customer;
import com.beidouiot.alllink.community.common.data.entity.user.center.User;
import com.beidouiot.alllink.community.common.data.mapping.user.center.customer.CustomerDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.customer.CustomerUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.CustomerDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.CustomerUpdateDto;
import com.beidouiot.alllink.community.user.center.dao.repository.CustomerRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.UserRepository;
import com.beidouiot.alllink.community.user.center.dao.service.api.CustomerService;

/**
 * 行业管理业务逻辑实现
 * 
 * @author longww
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDtoMapping customerDtoMapping;
	
	@Autowired
	private CustomerUpdateDtoMapping customerUpdateDtoMapping;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	public void saveEntity(@Valid CustomerDto customerDto) throws ServiceException {
		LOGGER.debug("customerDto = [ {} ]", customerDto);
		Customer customer = customerDtoMapping.targetToSource(customerDto);
		customerRepository.save(customer);
	}

	public void delete(Long id) throws ServiceException {
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		List<User> users = userRepository.findByCustomerId(id);
		if (null != users && users.size() > 0) {
			throw new CanNotDeleteDataException("该客户在用，不能删除");
		}
		customerRepository.deleteById(id);
	}

	public void logicalDelete(Long id) throws ServiceException {
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		List<User> users = userRepository.findByCustomerId(id);
		if (null != users && users.size() > 0) {
			throw new CanNotDeleteDataException("该客户在用，不能删除");
		}
		Customer customer = optional.get();
		Map<String, Object> map = getHeaderUser();
		customer.setUpdatedBy(map.get("username").toString());
		customer.setDeleteFlag(Constants.TRUE);
		customerRepository.save(customer);
	}

	public void updateEntity(CustomerUpdateDto customerUpdateDto) throws ServiceException {
		LOGGER.debug("customerUpdateDto = [ {} ]", customerUpdateDto);
		Optional<Customer> optional = customerRepository.findById(customerUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		Customer customer = optional.get();
		customer = customerUpdateDtoMapping.targetToSourceForUpdate(customerUpdateDto,customer);
		customerRepository.save(customer);
	}

	public CustomerDto findById(Long id) throws ServiceException {
		return customerDtoMapping.sourceToTarget(customerRepository.findById(id).get());
	}

	public List<CustomerDto> findAll() throws ServiceException {
		List<Customer> list = customerRepository.findByDeleteFlag(Constants.FALSE);
		if( null == list || list.size() == 0) {
			return null;
		}
		return customerDtoMapping.sourceToTarget(list);
	}

	@Override
	public Page<CustomerDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize,
			List<SortRpo> sortTypes) throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, customerRepository, customerDtoMapping);
	}

}
