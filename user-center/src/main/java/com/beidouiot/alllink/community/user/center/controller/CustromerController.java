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
* @Description ??????????????????
* @author longww
* @date 2021???4???11???
*/
@Api(tags = "??????????????????")
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

	@ApiOperation(value = "??????????????????", notes = "??????????????????")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(
			@RequestBody @ApiParam(name = "??????????????????", value = "????????????????????????", required = true) @Valid CustomerAddRpo customerAddRpo) {
		LOGGER.debug("customerAddRpo= [ {} ]", customerAddRpo);
		CustomerDto customerDto = custromerRpoDtoMapping.targetToSource(customerAddRpo);
		addUsernameAndDate(customerDto,true);
		customerService.saveEntity(customerDto);

		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "????????????", notes = "??????????????????")
	@PostMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(
			@RequestBody @ApiParam(name = "??????????????????", value = "????????????????????????", required = true) @Valid CustomerUpdateRpo customerUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("customerUpdateRpo= [ {} ]", customerUpdateRpo);
		CustomerUpdateDto customerUpdateDto = customerUpdateRpoToCustomerUpdateDtoMapping.targetToSource(customerUpdateRpo);
		addUsername(customerUpdateDto, false);
		customerService.updateEntity(customerUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}

	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(
			@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		customerService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}

	@ApiOperation(value = "????????????", notes = "????????????(????????????)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(
			@RequestBody @ApiParam(name = "????????????", value = "??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		customerService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "??????????????????", notes = "??????id????????????")
	@PostMapping("v1/findOne")
	public ResponseEntity<?> findONe(
			@RequestBody @ApiParam(name = "????????????", value = "??????id??????????????????????????????", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		CustomerDto customeDto = customerService.findById(id.getId());
		return makeSuccessResponseEntity(customeDto, HttpStatus.OK);
	}

	@ApiOperation(value = "??????????????????", notes = "????????????????????????")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<CustomerDto>>> findAll() {
		List<CustomerDto> list = customerService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}

	@ApiOperation(value = "???????????????????????????", notes = "?????????????????????????????????")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<CustomerDto>>> findPage(
			@RequestBody @ApiParam(name = "????????????", value = "?????????????????????????????????", required = true) CustomerSearchRpo customerSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(customerSearchRpo);
		Page<CustomerDto> customers = customerService.findPage(searchParams, customerSearchRpo.getPageNumber(),
				customerSearchRpo.getPageSize(), customerSearchRpo.getSortTypes());
		SmartPage<CustomerDto> smartPage = new SmartPage<CustomerDto>(customers.getSize(), customers.getTotalPages(),
				customers.getTotalElements(), customers.getNumber() + 1, customers.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
}
