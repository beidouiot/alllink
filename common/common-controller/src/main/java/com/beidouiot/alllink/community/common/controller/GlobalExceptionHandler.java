package com.beidouiot.alllink.community.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.beidouiot.alllink.community.common.base.enums.ErrorCodeConstants;
import com.beidouiot.alllink.community.common.base.exception.CanNotDeleteDataException;
import com.beidouiot.alllink.community.common.base.exception.DataExistException;
import com.beidouiot.alllink.community.common.base.exception.ServiceException;
import com.beidouiot.alllink.community.common.data.xxo.rro.ResultDataRro;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value= {BindException.class,MethodArgumentNotValidException.class })
	public ResponseEntity<ResultDataRro<Object>> validatorbinException(Throwable e, HttpServletRequest req) {
//		String uri = req.getRequestURI();
		BindingResult bindingResult = null;
		if(e instanceof BindException) {
			bindingResult = ((BindException)e).getBindingResult();
		} else if(e instanceof MethodArgumentNotValidException) {
			bindingResult = ((MethodArgumentNotValidException)e).getBindingResult();
		}
		StringBuilder sb = new StringBuilder();
		if (bindingResult.hasErrors()) {
			List<FieldError> list = bindingResult.getFieldErrors();
			for (FieldError fieldError : list) {
				sb.append(fieldError.getDefaultMessage() + ",");
			}
			sb = sb.delete(sb.length() - 1, sb.length());
		}
		ResultDataRro<Object> rdr = new ResultDataRro<Object>(ErrorCodeConstants.BASE_VALID_PARAM,sb.toString());
		return new ResponseEntity<ResultDataRro<Object>>(rdr, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=ServiceException.class)
	public ResponseEntity<ResultDataRro<Object>> serviceException(ServiceException e) {
		ResultDataRro<Object> rdr = new ResultDataRro<Object>(e.getCode(),e.getMessage(),null);
		return new ResponseEntity<ResultDataRro<Object>>(rdr, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=IllegalArgumentException.class)
	public ResponseEntity<ResultDataRro<Object>> illegalArgumentException(IllegalArgumentException e) {
		ResultDataRro<Object> rdr = new ResultDataRro<Object>(ErrorCodeConstants.ILLEGALA_ARGUMENT_EX.getCode(),ErrorCodeConstants.ILLEGALA_ARGUMENT_EX.getMsg()+" [ "+e.getMessage()+" ]",null);
		return new ResponseEntity<ResultDataRro<Object>>(rdr, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=CanNotDeleteDataException.class)
	public ResponseEntity<ResultDataRro<Object>> canNotDeleteDataException(CanNotDeleteDataException e) {
		ResultDataRro<Object> rdr = new ResultDataRro<Object>(ErrorCodeConstants.CAN_NOT_DELETE_DATA.getCode(),e.getMessage(),null);
		return new ResponseEntity<ResultDataRro<Object>>(rdr, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=DataExistException.class)
	public ResponseEntity<ResultDataRro<Object>> dataExistException(DataExistException e) {
		ResultDataRro<Object> rdr = new ResultDataRro<Object>(ErrorCodeConstants.PARAM_EXIST.getCode(),ErrorCodeConstants.PARAM_EXIST.getMsg()+" [ "+e.getMessage()+" ]",null);
		return new ResponseEntity<ResultDataRro<Object>>(rdr, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<ResultDataRro<Object>> exception(Exception e) {
		ResultDataRro<Object> rdr = new ResultDataRro<Object>(ErrorCodeConstants.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<ResultDataRro<Object>>(rdr, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RestController
	class NotFoundExceptionHandler implements ErrorController {

		@Override
		public String getErrorPath() {
			return "/error";
		}
		
		@RequestMapping("/error")
		@ResponseBody
		public ResponseEntity<ResultDataRro<Object>> error() {
			ResultDataRro<Object> rdr = new ResultDataRro<Object>(ErrorCodeConstants.NOT_FOUND);
			return new ResponseEntity<ResultDataRro<Object>>(rdr, HttpStatus.NOT_FOUND);
		}
	}
}
