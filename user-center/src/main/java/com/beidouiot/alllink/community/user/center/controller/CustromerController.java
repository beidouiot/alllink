package com.beidouiot.alllink.community.user.center.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.controller.BaseController;
import com.beidouiot.alllink.community.common.dao.api.service.datasercher.SearchParamsUtils;
import com.beidouiot.alllink.community.common.data.mapping.user.center.customer.CustomerRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.customer.CustomerUpdateRpoToCustomerUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.CustomerDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.CustomerUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.ID;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.customer.CustomerAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.customer.CustomerSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.customer.CustomerUpdateRpo;
import com.beidouiot.alllink.community.user.center.dao.service.api.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
*
* @Description 客户信息管理
* @author longww
* @date 2021年4月11日
*/
@Api(tags = "客户信息管理")
@RestController
@RequestMapping(value = ServiceConstants.CUSTOMER_URI_BASE, produces = "application/json; charset=UTF-8")
public class CustromerController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustromerController.class);

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRpoDtoMapping custromerRpoDtoMapping;

	@Autowired
	private CustomerUpdateRpoToCustomerUpdateDtoMapping customerUpdateRpoToCustomerUpdateDtoMapping;

	@ApiOperation(value = "客户信息添加", notes = "客户信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "新增客户信息", value = "客户信息请求参数", required = true) @Valid CustomerAddRpo customerAddRpo) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("customerAddRpo= [ {} ]", customerAddRpo);
		}
		
		CustomerDto customerDto = custromerRpoDtoMapping.targetToSource(customerAddRpo);
		addUsernameAndDate(customerDto,true);
		customerService.saveEntity(customerDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "客户修改", notes = "客户信息修改")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "修改客户信息", value = "客户信息请求参数", required = true) @Valid CustomerUpdateRpo customerUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("customerUpdateRpo= [ {} ]", customerUpdateRpo);
		}
		
		CustomerUpdateDto customerUpdateDto = customerUpdateRpoToCustomerUpdateDtoMapping.targetToSource(customerUpdateRpo);
		addUsername(customerUpdateDto, false);
		customerService.updateEntity(customerUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "删除客户", notes = "删除客户(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "删除客户", value = "删除客户信息请求参数", required = true) @Valid ID id) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("id= {}", id);
		}
		
		customerService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "删除客户", notes = "删除客户(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "删除客户", value = "删除客户信息请求参数", required = true) @Valid ID id) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("id= {}", id);
		}
		
		customerService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询一个客户", notes = "根据id查询客户")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findONe(
			@RequestBody @ApiParam(name = "查询客户", value = "根据id查询客户信息请求参数", required = true) @Valid ID id) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("id= {}", id);
		}
		
		CustomerDto customeDto = customerService.findById(id.getId());
		return makeSuccessResponseEntity(customeDto, HttpStatus.OK);
	}

	@ApiOperation(value = "查询所有客户", notes = "查询所有客户信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<CustomerDto>>> findAll() {
		List<CustomerDto> list = customerService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "按条件分页查询客户", notes = "按条件分页查询客户信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<CustomerDto>>> findPage(
			@RequestBody @ApiParam(name = "查询客户", value = "按条件客户信息请求参数", required = true) CustomerSearchRpo customerSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(customerSearchRpo);
		Page<CustomerDto> customers = customerService.findPage(searchParams, customerSearchRpo.getPageNumber(),
				customerSearchRpo.getPageSize(), customerSearchRpo.getSortTypes());
		SmartPage<CustomerDto> smartPage = new SmartPage<CustomerDto>(customers.getSize(), customers.getTotalPages(),
				customers.getTotalElements(), customers.getNumber() + 1, customers.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
