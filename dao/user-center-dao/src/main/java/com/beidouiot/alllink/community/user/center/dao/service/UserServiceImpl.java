package com.beidouiot.alllink.community.user.center.dao.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.beidouiot.alllink.community.common.base.enums.ErrorCodeConstants;
import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.DataExistException;
import com.beidouiot.alllink.community.common.base.exception.OldPasswordException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.CacheKeyConstants;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.dao.api.service.datasercher.SearchFilter;
import com.beidouiot.alllink.community.common.data.entity.user.center.Role;
import com.beidouiot.alllink.community.common.data.entity.user.center.RoleUser;
import com.beidouiot.alllink.community.common.data.entity.user.center.User;
import com.beidouiot.alllink.community.common.data.mapping.user.center.user.UserDtoMapping;
import com.beidouiot.alllink.community.common.data.mapping.user.center.user.UserUpdateDtoMapping;
import com.beidouiot.alllink.community.common.data.xxo.rro.datasearch.SortRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.UserUpdateDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateEmailRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateHeadPortraitRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdateMobileRpo;
import com.beidouiot.alllink.community.common.data.xxo.user.center.rpo.user.UserUpdatePasswordRpo;
import com.beidouiot.alllink.community.common.utils.AlllinkStringUtils;
import com.beidouiot.alllink.community.user.center.dao.repository.RoleRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.RoleUserRepository;
import com.beidouiot.alllink.community.user.center.dao.repository.UserRepository;
import com.beidouiot.alllink.community.user.center.dao.service.api.UserService;

/**
 * 
 *
 * @Description 用户管理业务逻辑实现
 * @author longww
 * @date 2021年4月11日
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDtoMapping userDtoMapping;
	
	@Autowired
	private UserUpdateDtoMapping userUpdateDtoMapping;
	
	@Autowired
	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleUserRepository roleUserRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Bean
	public PasswordEncoder passwordEncoder() {
		passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}
	
	@Override
	public void saveEntity(UserDto userDto) throws ServiceException {
		LOGGER.debug("userDto = [ {} ]", userDto);
		User existUser = userRepository.findUserByUsernameOrEmailOrMobileAndDeleteFlag(userDto.getUsername(), userDto.getEmail(), userDto.getMobile(), Constants.FALSE);
		if ( existUser != null) {
			throw new DataExistException("用户名或邮箱或手机号已存在");
		}
		User user = userDtoMapping.targetToSource(userDto);
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public void delete(Long id) throws ServiceException {
		Optional<User> oUser = userRepository.findById(id);
		if (oUser == null) {
			throw new IllegalArgumentException("id不存在");
		}
		User user = oUser.get();
		if ( !user.getDeleteFlag() ) {
			throw new CanNotDeleteDataException("用户不能做物联删除，请先做逻辑删除");
		}
		userRepository.deleteById(id);
	}

	public void logicalDelete(Long id) throws ServiceException {
		Optional<User> oUser = userRepository.findById(id);
		if (!oUser.isPresent()) {
			throw new IllegalArgumentException("id不存在");
		}
		User user = oUser.get();
		if ( user.getUsername().equalsIgnoreCase("admin") || user.getUsername().equalsIgnoreCase("superadmin")) {
			throw new CanNotDeleteDataException("管理员用户不能删除");
		}
        user.setUpdatedBy(getHeaderUser().getString(Constants.USERNAME));
		user.setDeleteFlag(Constants.TRUE);
		userRepository.save(user);
	}

	public void updateEntity(UserUpdateDto userUpdateDto) throws ServiceException {
		LOGGER.debug("userDto = [ {} ]", userUpdateDto);
		User existUser = userRepository.findUserByUsernameOrEmailOrMobileAndDeleteFlag(userUpdateDto.getUsername(), userUpdateDto.getEmail(), userUpdateDto.getMobile(), Constants.FALSE);
		if (existUser != null) {
			if ( existUser.getId().longValue() != userUpdateDto.getId().longValue() ) {
				throw new DataExistException("用户名或邮箱或手机号已存在");
			}
			
			if ( existUser.getUsername().equalsIgnoreCase("admin") || existUser.getUsername().equalsIgnoreCase("superadmin") ) {
				if ( !existUser.getUsername().equalsIgnoreCase(userUpdateDto.getUsername())) {
					throw new CanNotDeleteDataException("管理员用户不能修改账号名称");
				}
			}
			existUser = userUpdateDtoMapping.targetToSourceForUpdate(userUpdateDto, existUser);
			userRepository.save(existUser);
		} else {
			User user = userRepository.findById(userUpdateDto.getId()).get();
			user = userUpdateDtoMapping.targetToSource(userUpdateDto);
			userRepository.save(user);
		}
	}

	public UserDto findById(Long id) throws ServiceException {
		return userDtoMapping.sourceToTarget(userRepository.findById(id).get());
	}

	@Cacheable(key = CacheKeyConstants.USER_CENTER_USER_ALL, value = CacheKeyConstants.USER_CACHE, condition = "#result != null", cacheManager = "cacheManager")
	public List<UserDto> findAll() throws ServiceException {
		List<User> list = userRepository.findByDeleteFlag(Constants.FALSE);
		if (null == list) {
			return null;
		}
		List<UserDto> dtoList = new ArrayList<UserDto>();
		for (User user : list) {
			UserDto userDto = userDtoMapping.sourceToTarget(user);
			dtoList.add(userDto);
		}
		return dtoList;
	}

	@SuppressWarnings("unchecked")
	public Page<UserDto> findPage(Map<String, Object> searchParams, int pageNumber, int pageSize, List<SortRpo> sortTypes)
			throws ServiceException {
		Map<String, Integer> hash = new HashMap<>();
        List<SearchFilter> list = SearchFilter.parseForList(searchParams, hash);
//        long hashcode = hash.get("hashcode").longValue() + pageNumber + pageSize;
//        for (SortRpo sortRpo : sortTypes) {
//			hashcode += sortRpo.toString().hashCode();
//		}
//        if (redisTemplate.hasKey(CacheKeyConstants.USER_CACHE+":"+CacheKeyConstants.USER_CENTER_USER_PAGE_PREFIX+hashcode)) {
//        	return (Page<UserDto>) redisTemplate.opsForValue().get(CacheKeyConstants.USER_CACHE+":"+CacheKeyConstants.USER_CENTER_USER_PAGE_PREFIX+hashcode);
//        }
        Page<UserDto> page = findPage(list, pageNumber, pageSize, sortTypes, userRepository, userDtoMapping);
//        redisTemplate.opsForValue().set(CacheKeyConstants.USER_CACHE+":"+CacheKeyConstants.USER_CENTER_USER_PAGE_PREFIX+hashcode, page);
		return page;
	}

	//@Cacheable(key = CacheKeyConstants.USER_CENTER_USER_USERNAME_PREFIX+"#username", value = CacheKeyConstants.USER_CACHE, cacheManager = "cacheManager")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsernameAndDeleteFlag(username, Constants.FALSE);
		if (user == null) {
			throw new UsernameNotFoundException(ErrorCodeConstants.JWT_USER_INVALID.getMsg());
		}
		if (user.getStatus()) {
			throw new UsernameNotFoundException(ErrorCodeConstants.JWT_USER_ENABLED.getMsg());
		}
		List<RoleUser> roleUserList = roleUserRepository.findRoleUsersByUserId(user.getId());

		if (roleUserList != null) {
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			for (RoleUser roleUser : roleUserList) {
				Role role = roleRepository.findById(roleUser.getId().getRoleId()).get();
				authorities.add(new SimpleGrantedAuthority(role.getCode()));
			}
			user.setAuthorities(authorities);
		}
		return user;
	}

	@Override
	public void updatePassword(UserUpdatePasswordRpo userUpdatePasswordRpo, UserDto userDto) throws ServiceException {
		Optional<User> optional = userRepository.findById(userUpdatePasswordRpo.getId());
		if ( null == optional ) {
			throw new IllegalArgumentException("用户不存在");
		}
		User user = optional.get();
		if ( !passwordEncoder.matches(userUpdatePasswordRpo.getOldPassword(), user.getPassword()) ) {
			throw new OldPasswordException("原密码不正确");
		}
		user.setUpdatedBy(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userUpdatePasswordRpo.getNewPassword()));
		userRepository.save(user);
		if (redisTemplate.hasKey(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_MOBILE_PREFIX+user.getMobile())) {
			redisTemplate.opsForValue().set(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_MOBILE_PREFIX+user.getMobile(),user);
		}
		if (redisTemplate.hasKey(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_USERNAME_PREFIX+user.getUsername())) {
			redisTemplate.opsForValue().set(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_USERNAME_PREFIX+user.getUsername(),user);
		}
	}

	@Override
	public void updateMobile(UserUpdateMobileRpo userUpdateMobileRpo, UserDto userDto) throws ServiceException {
		Optional<User> optional = userRepository.findById(userUpdateMobileRpo.getId());
		if ( null == optional ) {
			throw new IllegalArgumentException("用户不存在");
		}
		User user = optional.get();
		String oldMobile = user.getMobile();
		user.setUpdatedBy(userDto.getUsername());
		user.setMobile(userUpdateMobileRpo.getMobile());
		userRepository.save(user);
		if (redisTemplate.hasKey(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_MOBILE_PREFIX+oldMobile)) {
			redisTemplate.delete(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_MOBILE_PREFIX+userUpdateMobileRpo.getMobile());
		}
		if (redisTemplate.hasKey(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_USERNAME_PREFIX+user.getUsername())) {
			redisTemplate.opsForValue().set(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_USERNAME_PREFIX+user.getUsername(),user);
		}
	}

	@Override
	public void updateEmail(UserUpdateEmailRpo userUpdateEmailRpo, UserDto userDto) throws ServiceException {
		Optional<User> optional = userRepository.findById(userUpdateEmailRpo.getId());
		if ( null == optional ) {
			throw new IllegalArgumentException("用户不存在");
		}
		User user = optional.get();
		user.setUpdatedBy(userDto.getUsername());
		user.setMobile(userUpdateEmailRpo.getEmail());
		userRepository.save(user);
		if (redisTemplate.hasKey(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_MOBILE_PREFIX+user.getMobile())) {
			redisTemplate.opsForValue().set(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_MOBILE_PREFIX+user.getMobile(),user);
		}
		if (redisTemplate.hasKey(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_USERNAME_PREFIX+user.getUsername())) {
			redisTemplate.opsForValue().set(CacheKeyConstants.USER_CACHE + ":" +CacheKeyConstants.USER_CENTER_USER_USERNAME_PREFIX+user.getUsername(),user);
		}
	}

	@Override
	public void updateHeadPortrait(UserUpdateHeadPortraitRpo userUpdateHeadPortraitRpo, UserDto userDto)
			throws ServiceException {
		Optional<User> optional = userRepository.findById(userUpdateHeadPortraitRpo.getId());
		if ( null == optional ) {
			throw new IllegalArgumentException("用户不存在");
		}
		User user = optional.get();
		user.setUpdatedBy(userDto.getUsername());
		user.setMobile(userUpdateHeadPortraitRpo.getHeadPortrait());
		userRepository.save(user);
	}

	@Cacheable(key = CacheKeyConstants.USER_CENTER_USER_MOBILE_PREFIX+"#mobile", value = CacheKeyConstants.USER_CACHE, cacheManager = "cacheManager")
	@Override
	public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
		User user = userRepository.findUserByMobileAndDeleteFlag(mobile, Constants.FALSE);
		if (user == null) {
			throw new  UsernameNotFoundException(ErrorCodeConstants.JWT_USER_INVALID.getMsg());
		}
		if (user.getStatus()) {
			throw new UsernameNotFoundException(ErrorCodeConstants.JWT_USER_ENABLED.getMsg());
		}
		List<RoleUser> roleUserList = roleUserRepository.findRoleUsersByUserId(user.getId());

		if (roleUserList != null) {
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			for (RoleUser roleUser : roleUserList) {
				Role role = roleRepository.findById(roleUser.getId().getRoleId()).get();
				authorities.add(new SimpleGrantedAuthority(role.getCode()));
			}
			user.setAuthorities(authorities);
		}
		return user;
	}

	@Override
	public String sendSmsCode(String mobile) throws ServiceException {
		String smsCode = AlllinkStringUtils.getRand(6);
		stringRedisTemplate.opsForValue().set(CacheKeyConstants.MOBILE_SMS_CODE_CACHE_PREFIX+mobile, smsCode, 120, TimeUnit.SECONDS);
		//TODO 发送短信
		LOGGER.info("smsCode = {}"+smsCode);
		return smsCode;
	}


//	public static void main(String[] args) {
//		UserServiceImpl UserServiceImpl = new UserServiceImpl();
//		PasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
//		System.out.println(PasswordEncoder.encode("123456"));
//	}

}
