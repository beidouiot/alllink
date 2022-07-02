package com.beidouiot.alllink.community.user.center.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.utils.CacheKeyConstants;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.base.utils.ServiceConstants;
import com.beidouiot.alllink.community.common.controller.BaseController;
import com.beidouiot.alllink.community.common.dao.api.service.datasercher.SearchParamsUtils;
import com.beidouiot.alllink.community.common.data.mapping.user.center.user.UserRpoDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.user.UserUpdateRpoToUserUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SmartPage;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.ID;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserAddRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserMobileSmsCodeRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserSearchRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateEmailRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateHeadPortraitRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateMobileRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdatePasswordRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateRpo;
import com.beidouiot.alllink.community.user.center.dao.service.api.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "用户信息管理")
@RestController
@RequestMapping(value = ServiceConstants.USER_URI_BASE, produces = "application/json; charset=UTF-8")
public class UserController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRpoDtoMapping userRpoDtoMapping;
	
	@Autowired
	private UserUpdateRpoToUserUpdateDtoMapping userUpdateRpoToUserUpdateDtoMapping;
	
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;
	
	@ApiOperation(value = "用户信息添加", notes = "用户信息添加")
	@PostMapping("v1/add")
	public ResponseEntity<ResultDataRro<Object>> add(@RequestBody @ApiParam(name = "新增用户", value = "新增用户信息请求参数", required = true) @Valid UserAddRpo userAddRpo) {
		LOGGER.debug("userAddRpo= [ {} ]", userAddRpo);
		UserDto userDto = userRpoDtoMapping.targetToSource(userAddRpo);
		addUsernameAndDate(userDto,true);
		userService.saveEntity(userDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
//	@ApiOperation(value = "用户注册", notes = "用户信息注册")
//	@PostMapping("v1/reg")
//	public ResponseEntity<ResultDataRro<Object>> reg(@RequestBody @ApiParam(name = "新增用户", value = "新增用户信息请求参数", required = true) @Valid UserAddRpo userAddRpo) {
//		LOGGER.debug("userAddRpo= [ {} ]", userAddRpo);
//		UserDto userDto = userRpoDtoMapping.targetToSource(userAddRpo);
//		addUsernameAndDate(userDto,true);
//		userService.saveEntity(userDto);
//		return makeSuccessResponseEntity(HttpStatus.CREATED);
//	}
	
	@ApiOperation(value = "用户信息修改", notes = "用户信息修改")
	@PutMapping("v1/update")
	public ResponseEntity<ResultDataRro<Object>> update(@RequestBody @ApiParam(name = "修改用户", value = "修改用户信息请求参数", required = true) @Valid UserUpdateRpo userUpdateRpo) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		LOGGER.debug("userUpdateRpo= [ {} ]", userUpdateRpo);
		UserUpdateDto userUpdateDto = userUpdateRpoToUserUpdateDtoMapping.targetToSource(userUpdateRpo);
		addUsername(userUpdateDto,false);
		userService.updateEntity(userUpdateDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "删除用户", notes = "删除用户(逻辑删除)")
	@DeleteMapping("v1/logicalDelete")
	public ResponseEntity<?> logicalDelete(@RequestBody @ApiParam(name = "删除用户", value = "删除用户信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		userService.logicalDelete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "删除用户", notes = "删除用户(物理删除)")
	@DeleteMapping("v1/realDelete")
	public ResponseEntity<?> realDelete(@RequestBody @ApiParam(name = "删除用户", value = "删除用户信息请求参数", required = true) @Valid ID id) {
		LOGGER.debug("id= {}", id);
		userService.delete(id.getId());
		return makeSuccessResponseEntity(null, HttpStatus.OK);
	}
	
	@ApiOperation(value = "查询所有用户", notes = "查询所有用户信息")
	@GetMapping("v1/findAll")
	public ResponseEntity<ResultDataRro<List<UserDto>>> findAll() {
		List<UserDto> list = userService.findAll();
		return makeSuccessResponseEntity(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "按条件分页查询用户", notes = "按条件分页查询用户信息")
	@PostMapping("v1/findPage")
	public ResponseEntity<ResultDataRro<SmartPage<UserDto>>> findPage(@RequestBody @ApiParam(name = "查询用户", value = "按条件用户信息请求参数", required = true) UserSearchRpo userSearchRpo) {
		Map<String, Object> searchParams = SearchParamsUtils.makeSearchParams(userSearchRpo);
		Page<UserDto> users = userService.findPage(searchParams, userSearchRpo.getPageNumber(),
				userSearchRpo.getPageSize(), userSearchRpo.getSortTypes());
		SmartPage<UserDto> smartPage = new SmartPage<UserDto>(users.getSize(), users.getTotalPages(), users.getTotalElements(),
				users.getNumber() + 1, users.getContent());
		return makeSuccessResponseEntity(smartPage, HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据id查询用户", notes = "根据id查询用户信息")
	@PostMapping("v1/findOne")
	public ResponseEntity<ResultDataRro<UserDto>> findOne(@RequestBody @ApiParam(name = "查询用户", value = "按条件用户信息请求参数", required = true) ID id) {
		UserDto user = userService.findById(id.getId());
		return makeSuccessResponseEntity(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = "修改用户密码", notes = "修改用户密码")
	@PutMapping("v1/updatePassword")
	public ResponseEntity<?> updatePassword(@RequestBody @ApiParam(name = "修改用户密码", value = "修改用户密码请求参数对象") @Valid UserUpdatePasswordRpo userUpdatePasswordRpo) {
		LOGGER.debug("userUpdatePasswordRpo= {}", userUpdatePasswordRpo);
		UserDto userDto = new UserDto();
		addUsernameAndDate(userDto,false);
		userService.updatePassword(userUpdatePasswordRpo, userDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "修改手机号", notes = "修改用户手机号")
	@PutMapping("v1/updateMobile")
	public ResponseEntity<?> updateMobile(@RequestBody @ApiParam(name = "修改手机号", value = "修改用户手机号请求参数对象") @Valid UserUpdateMobileRpo userUpdateMobileRpo) {
		LOGGER.debug("userUpdateMobileRpo= {}", userUpdateMobileRpo);
		UserDto userDto = new UserDto();
		addUsernameAndDate(userDto,false);
		userService.updateMobile(userUpdateMobileRpo, userDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "修改邮箱", notes = "修改用户邮箱")
	@PutMapping("v1/updateEmail")
	public ResponseEntity<?> updateEmail(@RequestBody @ApiParam(name = "修改邮箱", value = "修改用户邮箱请求参数对象") @Valid UserUpdateEmailRpo userUpdateEmailRpo) {
		LOGGER.debug("userUpdateEmailRpo= {}", userUpdateEmailRpo);
		UserDto userDto = new UserDto();
		addUsernameAndDate(userDto,false);
		userService.updateEmail(userUpdateEmailRpo, userDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "修改头像", notes = "修改用户头像")
	@PutMapping("v1/updateHeadPortrait")
	public ResponseEntity<?> updateHeadPortrait(@RequestBody @ApiParam(name = "修改头像", value = "修改用户头像请求参数对象") @Valid UserUpdateHeadPortraitRpo userUpdateHeadPortraitRpo) {
		LOGGER.debug("userUpdateHeadPortraitRpo= {}", userUpdateHeadPortraitRpo);
		UserDto userDto = new UserDto();
		addUsernameAndDate(userDto,false);
		userService.updateHeadPortrait(userUpdateHeadPortraitRpo, userDto);
		return makeSuccessResponseEntity(HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "登出", notes = "logout")
    @DeleteMapping(value = "v1/logout")
    public ResultDataRro<Object> logout() {
    	JSONObject jsonObject = getJwtUser();
        String jti = jsonObject.getString(Constants.JTI); // JWT唯一标识
        long exp = jsonObject.getLong(Constants.EXP); // JWT过期时间戳

        long currentTimeSeconds = System.currentTimeMillis() / 1000;

        if (exp < currentTimeSeconds) { // token已过期，无需加入黑名单
            return ResultDataRro.success();
        }
        
        redisTemplate.opsForValue().set(CacheKeyConstants.TOKEN_BLACKLIST_PREFIX + jti, null, (exp - currentTimeSeconds), TimeUnit.SECONDS);
 
        return ResultDataRro.success();
    }
	
	@ApiOperation(value = "发送手机短信", notes = "发送手机短信")
	@PostMapping("v1/sendSmsCode")
	public ResponseEntity<?> sendSmsCode(@RequestBody @ApiParam(name = "发送手机短信", value = "发送手机短信请求参数对象") @Valid UserMobileSmsCodeRpo userMobileSmsCodeRpo) {
		LOGGER.debug("userMobileSmsCodeRpo= {}", userMobileSmsCodeRpo);
		userService.sendSmsCode(userMobileSmsCodeRpo.getMobile());
		return makeSuccessResponseEntity(HttpStatus.OK);
	}
    
}
