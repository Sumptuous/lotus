/**
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 *
 * File generated at: 2014年12月11日下午3:28:18
 */
package com.lotus.common.exception;

import com.lotus.common.model.ResponseResult;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常捕获
 *	
 * @author 李昊龙 
 * @created 2014年12月11日下午3:28:18
 * @version aslan 2.0
 */
@Component("handlerExceptionResolver")
public class ControllerExceptionHandler implements HandlerExceptionResolver {

	private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ResponseResult<Object> code = null;
		ErrorCode errorCode = null;
		Class<?> errorInClass = null;

		String message = ex.getMessage();
		if (StringUtils.containsIgnoreCase(message, "get connection failed,dbKey")) {
			LOG.error(message, ex);
			return null;
		}
	 	String detailMessage = (ex.getCause() == null ? message : ex.getCause().getMessage());
		//因IDE前端异常透传，不适合再将Exception的className加到异常上
		//detailMessage = CommonUtils.getExcepClassSimpleName(ex) + ": " + detailMessage;

		if (ex instanceof BaseException) {
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			BaseException BaseException = (BaseException)ex;
			errorCode = BaseException.getErrorCode();
			errorInClass = BaseException.getErrorInClass();
			if (errorInClass == null) {
				errorInClass = ControllerExceptionHandler.class;
			}
		} else if (ex instanceof MissingServletRequestParameterException) {
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			errorCode = ErrorCode.COMMON_MISSING_HTTP_REQ_PARAM;
		} else if (ex instanceof SQLSyntaxErrorException) {
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			errorCode = ErrorCode.COMMON_SQL_SYNTAX_ERROR;
		} else if (ex instanceof UnexpectedRollbackException) {
			//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			errorCode = ErrorCode.COMMON_TRANSCATION_ROLLBACK;
		} else {
			//response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			errorCode = ErrorCode.COMMON_UNDEFINED_ERROR;
		}
		if (!(ex instanceof BaseException)) {
			detailMessage = "系统错误";
		}
		code = new ResponseResult<Object>(errorCode, StringEscapeUtils.escapeHtml4(detailMessage), null);
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
				.getHeader("X-Requested-With") != null && request.getHeader(
				"X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			// 如果不是ajax，JSP格式返回
			// 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			if (ex instanceof BaseException) {
				map.put("errorMsg", ex.getMessage());
			} else {
				map.put("errorMsg", "系统异常！");
			}
			return new ModelAndView("/error", map);
		}else {
			try {
				String respStr = mapper.writeValueAsString(code);
				//COMMON_MISSING_HTTP_REQ_PARAM的错太多，不做记录
//			if (errorCode != ErrorCode.COMMON_MISSING_HTTP_REQ_PARAM) {
//				String remoteAddress = EagleEyeRequestTracer.getRemoteAddress(request);
//				detailMessage += ". by " + request.getRequestURL() + " from client " + remoteAddress;
//				ControllerExceptionHandler.handle(errorCode, ex, errorInClass, detailMessage);
//			}
				if (ex instanceof MissingServletRequestParameterException || ex instanceof BaseException) {
					// 避免这类异常刷屏
					LOG.error("{} : {} {}", ex.getClass().getName(), respStr, ex);
				} else {
					LOG.error(respStr, ex);
				}
				response.setContentType("application/json;charset=UTF-8");
				response.getOutputStream().write(respStr.getBytes("utf-8"));
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
			return new ModelAndView();
		}
	}
}
