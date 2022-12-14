package com.beidouiot.alllink.community.user.center.dao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ClientDetailsDto;
import com.beidouiot.alllink.community.common.data.xxo.user.center.dto.ClientDetailsUpdateDto;
import com.beidouiot.alllink.community.user.center.dao.service.api.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private PasswordEncoder passwordEncoder;

	private static final String CLIENT_FIELDS_FOR_INSERT = "client_id, resource_ids, client_secret, scope, "
			+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
			+ "refresh_token_validity, additional_information, autoapprove, created_date, updated_date, created_by, updated_by, delete_flag";

	private static final String CLIENT_FIELDS_FOR_UPDATE = "resource_ids, scope, "
			+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
			+ "refresh_token_validity, additional_information, autoapprove, updated_date, updated_by";

	private static final String DEFAULT_INSERT_STATEMENT = "insert into oauth_client_details("
			+ CLIENT_FIELDS_FOR_INSERT + ") values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String DEFAULT_UPDATE_STATEMENT = "update oauth_client_details set "
			+ CLIENT_FIELDS_FOR_UPDATE.replaceAll(", ", "=?, ") + "=? where client_id = ?";

	private static final String DEFAULT_UPDATE_SECRET_STATEMENT = "update oauth_client_details "
			+ "set client_secret = ?, updated_date = ?, updated_by = ? where client_id = ?";

	private static final String DEFAULT_DELETE_STATEMENT = "delete from oauth_client_details " + " where client_id = ?";

	private static final String DEFAULT_SELECT_BY_ID_STATEMENT = "select client_id as clientId, resource_ids as resourceIds, client_secret as clientSecret, scope, "
			+ "authorized_grant_types as authorizedGrantTypes, web_server_redirect_uri as webServerRedirectUri, authorities, access_token_validity as accessTokenValidity, refresh_token_validity as refreshTokenValidity, "
			+ "additional_information as additionalInformation, autoapprove, created_by as createdBy, updated_by as updatedBy from oauth_client_details "
			+ " where client_id = ?";

	private static final String DEFAULT_SELECT_ALL_STATEMENT = "select client_id as clientId, resource_ids as resourceIds, client_secret as clientSecret, scope, "
			+ "authorized_grant_types as authorizedGrantTypes, web_server_redirect_uri as webServerRedirectUri, authorities, access_token_validity as accessTokenValidity, refresh_token_validity as refreshTokenValidity, "
			+ "additional_information as additionalInformation, autoapprove, created_by as createdBy, updated_by as updatedBy from oauth_client_details";

	@Bean
	public PasswordEncoder passwordEncoder() {
		passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	@Override
	public void addClient(ClientDetailsDto clientDetailsDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("clientDetailsDto = [ {} ]", clientDetailsDto);
		}

		Object[] fields = new Object[] { clientDetailsDto.getClientId(), clientDetailsDto.getResourceIds(),
				passwordEncoder().encode(clientDetailsDto.getClientSecret()), clientDetailsDto.getScope(),
				clientDetailsDto.getAuthorizedGrantTypes(), clientDetailsDto.getWebServerRedirectUri(),
				clientDetailsDto.getAuthorities(), clientDetailsDto.getAccessTokenValidity(),
				clientDetailsDto.getRefreshTokenValidity(), clientDetailsDto.getAdditionalInformation(),
				clientDetailsDto.getAutoapprove(), clientDetailsDto.getCreatedDate(), clientDetailsDto.getUpdatedDate(),
				clientDetailsDto.getCreatedBy(), clientDetailsDto.getUpdatedBy(), clientDetailsDto.getDeleteFlag() };

		jdbcTemplate.update(DEFAULT_INSERT_STATEMENT, fields);

	}

	@Override
	public void updateClient(ClientDetailsUpdateDto clientDetailsUpdateDto) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("clientDetailsUpdateDto = [ {} ]", clientDetailsUpdateDto);
		}

		Object[] fields = new Object[] { clientDetailsUpdateDto.getResourceIds(), clientDetailsUpdateDto.getScope(),
				clientDetailsUpdateDto.getAuthorizedGrantTypes(), clientDetailsUpdateDto.getWebServerRedirectUri(),
				clientDetailsUpdateDto.getAuthorities(), clientDetailsUpdateDto.getAccessTokenValidity(),
				clientDetailsUpdateDto.getRefreshTokenValidity(), clientDetailsUpdateDto.getAdditionalInformation(),
				clientDetailsUpdateDto.getAutoapprove(), new Date(), getHeaderUser().get("username").toString(),
				clientDetailsUpdateDto.getClientId() };

		jdbcTemplate.update(DEFAULT_UPDATE_STATEMENT, fields);

	}

	@Override
	public void updateClientSecret(String clientId, String secret) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("clientId = [ {} ], clientSecret = [ {} ]", clientId, secret);
		}

		Object[] fields = new Object[] { passwordEncoder().encode(secret), new Date(),
				getHeaderUser().get("username").toString(), clientId };

		jdbcTemplate.update(DEFAULT_UPDATE_SECRET_STATEMENT, fields);

	}

	@Override
	public void delete(String clientId) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("clientId = [ {} ]", clientId);
		}

		Object[] fields = new Object[] { clientId };

		jdbcTemplate.update(DEFAULT_DELETE_STATEMENT, fields);

	}

	@Override
	public ClientDetailsDto findById(String clientId) throws ServiceException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("clientId = [ {} ]", clientId);
		}

		Object[] fields = new Object[] { clientId };
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(DEFAULT_SELECT_BY_ID_STATEMENT,fields);
		ClientDetailsDto cdd = new ClientDetailsDto();
		for (Map<String, Object> map : maps) {
			BeanMap bm = BeanMap.create(cdd);
			bm.putAll(map);
		}

		return cdd;
	}

	@Override
	public List<ClientDetailsDto> findAll() throws ServiceException {
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(DEFAULT_SELECT_ALL_STATEMENT);
		List<ClientDetailsDto> rs = new ArrayList<>();
		for (Map<String, Object> map : maps) {
			ClientDetailsDto cdd = new ClientDetailsDto();
			BeanMap bm = BeanMap.create(cdd);
			bm.putAll(map);
			rs.add(cdd);
		}
//		List<ClientDetailsDto> list = jdbcTemplate.queryForList(DEFAULT_SELECT_ALL_STATEMENT, ClientDetailsDto.class);
		return rs;
	}

	JSONObject getHeaderUser() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		String userStr = request.getHeader(Constants.USER);
		JSONObject userJsonObject = JSONObject.parseObject(userStr);
		return userJsonObject;
	}

}
