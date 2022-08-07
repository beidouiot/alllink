package com.beidouiot.alllink.community.common.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.beidouiot.alllink.community.common.base.enums.ErrorCodeConstants;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.base.utils.Constants;
import com.beidouiot.alllink.community.common.base.utils.IExceptionCode;
import com.beidouiot.alllink.community.common.data.xxo.dto.BaseDto;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;
import com.beidouiot.alllink.community.common.utils.AlllinkStringUtils;
import com.beidouiot.alllink.community.common.utils.BeanMapUtil;
import com.beidouiot.alllink.community.common.utils.Cryptos;



/**
 * 
 *
 * @Description controller 基类
 * @author longww
 * @date 2021年4月11日
 */
public abstract class BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 *     组装请求成功后的返回报文
	 * @param <T> 数据对象
	 * @param data 返回报文数据
	 * @param httpStatus http状态码
	 * @return  返回Json报文实体对象
	 */
	protected static <T> ResponseEntity<ResultDataRro<T>> makeSuccessResponseEntity(T data, HttpStatus httpStatus) {
		ResultDataRro<T> resultDataRro = new ResultDataRro<T>(ErrorCodeConstants.SUCCESS, data);
		LOGGER.debug("resultDataRro = {}", resultDataRro);
		return new ResponseEntity<ResultDataRro<T>>(resultDataRro, httpStatus);
	}
	
	/**
	 *     组装请求成功后的返回报文
	 * @param <T>
	 * @param httpStatus http状态码
	 * @return  返回Json报文实体对象
	 */
	protected static <T> ResponseEntity<ResultDataRro<T>> makeSuccessResponseEntity(HttpStatus httpStatus) {
		ResultDataRro<T> resultDataRro = new ResultDataRro<T>(ErrorCodeConstants.SUCCESS, null);
		LOGGER.debug("resultDataRro = {}", resultDataRro);
		return new ResponseEntity<ResultDataRro<T>>(resultDataRro, httpStatus);
	}
	
	/**
	 *     组装请求失败后的返回报文
	 * @param <T>  数据对象
	 * @param errCode  错误码
	 * @param errMsg   错误消息
	 * @param data       报文数据
	 * @param httpStatus Http状态码
	 * @return 返回Json报文实体对象
	 */
	protected static <T> ResponseEntity<ResultDataRro<T>> makeFailureResponseEntity(int errCode, String errMsg, T data,
			HttpStatus httpStatus) {
		ResultDataRro<T> resultDataRro = new ResultDataRro<T>(errCode,errMsg,data);
		LOGGER.debug("resultDataRro={}" + resultDataRro);
		return new ResponseEntity<ResultDataRro<T>>(resultDataRro, httpStatus);
	}
	
	/**
	 *     组装请求失败后的返回报文
	 * @param <T>             数据对象
	 * @param iExceptionCode  错误消息
	 * @param data            报文数据
	 * @param httpStatus      Http状态码
	 * @return   返回Json报文实体对象
	 */
	protected static <T> ResponseEntity<ResultDataRro<T>> makeFailureResponseEntity(IExceptionCode iExceptionCode, T data,
			HttpStatus httpStatus) {
		ResultDataRro<T> resultDataRro = new ResultDataRro<T>(iExceptionCode,data);
		LOGGER.debug("resultDataRro={}" + resultDataRro);
		return new ResponseEntity<ResultDataRro<T>>(resultDataRro, httpStatus);
	}
	
	/**
	 *     组装请求失败后的返回报文
	 * @param errorCode 错误码
	 * @param errorMsg 错误消息
	 * @param httpStatus Http状态码
	 * @return 返回Json报文实体对象
	 */
	protected static <T> ResponseEntity<ResultDataRro<T>> makeFailureResponseEntity(int errorCode, String errorMsg,
			HttpStatus httpStatus) {
		ResultDataRro<T> resultDataRro = new ResultDataRro<T>(errorCode,errorMsg,null);
		LOGGER.debug("resultDataRro={}" + resultDataRro);
		return new ResponseEntity<ResultDataRro<T>>(resultDataRro, httpStatus);
	}
	
	/**
	 *      组装请求失败后的返回报文
	 * @param errorCode 错误码
	 * @param errorMsg 错误消息
	 * @param httpStatus Http状态码
	 * @return 返回Json报文实体对象
	 */
	protected static <T> ResponseEntity<ResultDataRro<T>> makeFailureResponseEntity(IExceptionCode iExceptionCode,
			HttpStatus httpStatus) {
		ResultDataRro<T> resultDataRro = new ResultDataRro<T>(iExceptionCode,null);
		LOGGER.debug("resultDataRro={}" + resultDataRro);
		return new ResponseEntity<ResultDataRro<T>>(resultDataRro, httpStatus);
	}
	
	@SuppressWarnings({"static-access" })
	protected static <T> ResponseEntity<ResultDataRro<T>> makeResponseEntityWithHeader(ResultDataRro<T> resultDataRro, HttpStatus httpStatus, HttpHeaders responseHeaders) {
		if (responseHeaders == null) {
			if (resultDataRro == null) {
				ResultDataRro<T> rdr = new ResultDataRro<T>(ErrorCodeConstants.INTERNAL_SERVER_ERROR);
				return new ResponseEntity<ResultDataRro<T>>(rdr, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				return new ResponseEntity<ResultDataRro<T>>(resultDataRro, httpStatus);
			}
		} else {
			if (resultDataRro == null) {
				ResultDataRro<T> rdr = new ResultDataRro<T>(ErrorCodeConstants.INTERNAL_SERVER_ERROR);
				ResponseEntity<ResultDataRro<T>> re = ResponseEntity.ok().headers(responseHeaders).body(rdr);
				re.status(ErrorCodeConstants.INTERNAL_SERVER_ERROR.getCode());
				return re;//(ResponseEntity<ResultDataRro<T>>) ResponseEntity.ok().headers(responseHeaders).body(rdr);
			} else {
				ResponseEntity<ResultDataRro<T>> re = ResponseEntity.ok().headers(responseHeaders).body(resultDataRro);
				re.status(httpStatus);
				return re;//(ResponseEntity<ResultDataRro<T>>) ResponseEntity.ok().headers(responseHeaders).body(resultDataRro).status(httpStatus);
			}
		}
	}
	
	protected <T> HttpHeaders makeHttpHeaders(ResultDataRro<T> resultDataRro) {
		HttpHeaders responseHeaders = new HttpHeaders();
		MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<String, String>();
		headersMap.add(HttpHeaders.CONTENT_TYPE, "application/json");
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String appId = request.getHeader(Constants.APP_ID);
		responseHeaders.add("appId", appId);
		String nonceStr = AlllinkStringUtils.getRand(8);
		responseHeaders.add("nonceStr", nonceStr);//签名随机数
		String times = String.valueOf(System.currentTimeMillis());
		responseHeaders.add("times", times);
		String signature = signatureWithoutToken(appId,nonceStr,times,resultDataRro);
		responseHeaders.add("signature",signature);// appid+nonceStr+times+body,SHA-256签名
		return responseHeaders;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> String signatureWithoutToken(String appId,String nonceStr,String times, T  data) {
		try {
			if(data == null) {
				throw new ServiceException(ErrorCodeConstants.SIGNATURE_ERROR);
			}
			Map<String, Object> map;
			if (data instanceof Map) {
				map = (Map<String, Object>)data;
			} else if(data.getClass().isPrimitive()){
				throw new ServiceException(ErrorCodeConstants.SIGNATURE_ERROR);
			} else {
				map = BeanMapUtil.beanToMap(data);
			}
			String str = "appId="+appId+"&nonceStr="+nonceStr+"&times="+times+"&"+createLinkString(map); // appid+nonceStr+times+body,SHA-256签名
			return Cryptos.generateSha256(str);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorCodeConstants.SIGNATURE_ERROR.getCode(),e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> String signatureWithToken(String appId,String nonceStr,String times, String token, T data) {
		try {
			if(data == null) {
				throw new ServiceException(ErrorCodeConstants.SIGNATURE_ERROR);
			}
			Map<String, Object> map;
			if (data instanceof Map) {
				map = (Map<String, Object>)data;
			} else if(data.getClass().isPrimitive()){
				throw new ServiceException(ErrorCodeConstants.SIGNATURE_ERROR);
			} else {
				map = BeanMapUtil.beanToMap(data);
			}
			
			String str = "appId="+appId+"&nonceStr="+nonceStr+"&times="+times+"&"+"token="+token+"&"+createLinkString(map); // appId+nonceStr+times+token+body,SHA-256签名
			return Cryptos.generateSha256(str);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new ServiceException(ErrorCodeConstants.SIGNATURE_ERROR.getCode(),e.getMessage());
		}
		
	}
	
	protected void addUsernameAndDate(BaseDto baseDto, boolean isAdd) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader(Constants.USER);
        JSONObject userJsonObject = JSONObject.parseObject(userStr);
        baseDto.setUpdatedBy(userJsonObject.getString("username"));
        if (isAdd) {
        	baseDto.setCreatedBy(baseDto.getUpdatedBy());
        }
	}
	
	protected void addUsername(Object obj, boolean isAdd) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader(Constants.USER);
        JSONObject userJsonObject = JSONObject.parseObject(userStr);
        Class<?>[] paramsTypes = new Class<?>[1];
        Field field = obj.getClass().getDeclaredField("updatedBy");
        paramsTypes[0] = field.getType();
		Method  setUpdatedBy = obj.getClass().getMethod("setUpdatedBy",
				paramsTypes);
		setUpdatedBy.invoke(obj, userJsonObject.getString("username"));
		
        if (isAdd) {
        	Field field1 = obj.getClass().getDeclaredField("createdBy");
            paramsTypes[0] = field1.getType();
        	Method  setCreatedBy = obj.getClass().getMethod("setCreatedBy",
        			paramsTypes);
        	setCreatedBy.invoke(obj, userJsonObject.getString("username"));
        }
	}
	
	protected HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    
	protected JSONObject getJwtUser() {
        String jwtUser = getRequest().getHeader(Constants.USER);
        JSONObject jsonObject = JSONObject.parseObject(jwtUser);
        return jsonObject;
    }

	/**
	 * 排序，如果map嵌套，则嵌套层的value要继续排序，并且value只用“{}”括起来
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static String createLinkString(Map<String, Object> params) {
		Object[] keys = params.keySet().toArray();
		Arrays.sort(keys);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < keys.length; i++) {
			if (params.get(keys[i]) != null) {
				if(Map.class.isInstance(params.get(keys[i]))) {
					sb.append(keys[i]).append("={").append(createLinkString((Map<String, Object>) params.get(keys[i]))).append("}");
					if (i != keys.length - 1) {
						sb.append("&");
					}
					continue;
				}
				if (!StringUtils.isEmpty(params.get(keys[i]))) {
					sb.append(keys[i]).append("=").append(params.get(keys[i]));
					if (i != keys.length - 1) {
						sb.append("&");
					}
				}
			}

		}
		if (sb.toString().endsWith("&")) {
			return sb.deleteCharAt(sb.length() - 1).toString();
		}
		return sb.toString();
	}
	

}
