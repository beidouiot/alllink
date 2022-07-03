package com.beidouiot.alllink.community.user.center.dao.service;

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
import com.beidouiot.alllink.community.common.data.entity.user.center.Customer;
import com.beidouiot.alllink.community.common.data.entity.user.center.Tenant;
import com.beidouiot.alllink.community.common.data.entity.user.center.User;
import com.beidouiot.alllink.community.common.data.mapping.user.center.tenant.TenantDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.tenant.TenantUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.TenantDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.TenantUpdateDto;
import com.beidouiot.alllink.community.user.center.dao.repository.CustomerRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.TenantRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.UserRepository;
import com.beidouiot.alllink.community.user.center.dao.service.api.TenantService;

/**
 * 租户管理业务逻辑实现
 * @author longww
 *
 */
@Service
public class TenantServiceImpl implements TenantService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TenantServiceImpl.class);
	
	@Autowired
	private TenantDtoMapping tenantDtoMapping;
	
	@Autowired
	private TenantUpdateDtoMapping tenantUpdateDtoMapping;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public void saveEntity(TenantDto tenantDto) throws ServiceException {
		LOGGER.debug("tenantDto = [ {} ]", tenantDto);
		Tenant tenant = tenantDtoMapping.targetToSource(tenantDto);
		tenantRepository.save(tenant);
	}

	public void delete(Long id) throws ServiceException {
		Optional<Tenant> optional = tenantRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}
		List<User> users = userRepository.findByTenantId(id);
		if ( null != users && users.size() > 0 ) {
			throw new CanNotDeleteDataException("租户已使用，不能删除");
		}
		
		List<Customer> customers = customerRepository.findByTenantId(id);
		if ( null != customers && customers.size() > 0 ) {
			throw new CanNotDeleteDataException("租户已使用，不能删除");
		}
		tenantRepository.deleteById(id);
	}

	public void logicalDelete(Long id) throws ServiceException {
		Optional<Tenant> optional = tenantRepository.findById(id);
		if ( optional == null ) {
			throw new IllegalArgumentException("id不存在");
		}
		List<User> users = userRepository.findByTenantId(id);
		if ( null != users && users.size() > 0 ) {
			throw new CanNotDeleteDataException("租户已使用，不能删除");
		}
		
		List<Customer> customers = customerRepository.findByTenantId(id);
		if ( null != customers && customers.size() > 0 ) {
			throw new CanNotDeleteDataException("租户已使用，不能删除");
		}
		Tenant tenant = optional.get();
		Map<String, Object> map = getHeaderUser();
		tenant.setUpdatedBy(map.get("username").toString());
		tenant.setDeleteFlag(Constants.TRUE);
		tenantRepository.save(tenant);

	}

	public void updateEntity(TenantUpdateDto tenantUpdateDto) throws ServiceException {
		LOGGER.debug("tenantUpdateDto = [ {} ]", tenantUpdateDto);
		Optional<Tenant> optional = tenantRepository.findById(tenantUpdateDto.getId());
		if (optional == null) {
			throw new IllegalArgumentException("id不存在");
		}
		Tenant tenant = optional.get();
		tenant = tenantUpdateDtoMapping.targetToSourceForUpdate(tenantUpdateDto, tenant);
		tenantRepository.save(tenant);
	}

	public TenantDto findById(Long id) throws ServiceException {
		return tenantDtoMapping.sourceToTarget(tenantRepository.findById(id).get());
	}

	public List<TenantDto> findAll() throws ServiceException {
		List<Tenant> list = tenantRepository.findByDeleteFlag(Constants.FALSE);
		if( null == list || list.size() == 0 ) {
			return null;
		}

		return tenantDtoMapping.sourceToTarget(list);
	}

	public Page<TenantDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize, List<SortRpo> sortTypes)
			throws ServiceException {
		return findPage(searchParams, pageNumber, pageSize, sortTypes, tenantRepository, tenantDtoMapping);
	}

}
