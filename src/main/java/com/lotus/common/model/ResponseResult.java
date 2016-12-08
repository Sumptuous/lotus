/**
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 *
 * File generated at: 2015年1月20日下午4:57:31
 */
package com.lotus.common.model;


import com.lotus.common.exception.ErrorCode;
import com.lotus.core.web.Constants;

/**
 * HTTP 响应json值的标准bean
 *	
 * @author wyy
 */
public class ResponseResult<T> {

	private Integer errCode;
	private String errName;
	private String errMsg;
	private boolean wrapped;
	private T data;
	
	public ResponseResult() {
		this.wrapped = true;
		this.errCode = 0;
		this.errMsg = "success";
	}
	public ResponseResult(T data) {
		this.wrapped = true;
		this.errCode = 0;
		this.errMsg = "success";
		this.data = data;
	}
	public ResponseResult(ErrorCode errorCode, String errMsg, T data) {
		this.wrapped = true;
		this.errCode = Integer.valueOf(Constants.ERR_CODE_PREFIX+""+errorCode.getCode());
		this.errName= errorCode.name();
		this.errMsg = errMsg;
		this.data = data;
	}
	
	public ResponseResult(T data, boolean wrapped) {
		super();
		this.wrapped = wrapped;
		this.data = data;
		this.errCode = 0;
		this.errMsg = "success";
	}
	public Integer getErrCode() {
		return errCode;
	}
	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getErrName() {
		return errName;
	}
	public void setErrName(String errName) {
		this.errName = errName;
	}
	public boolean isWrapped() {
		return wrapped;
	}
	public void setWrapped(boolean wrapped) {
		this.wrapped = wrapped;
	}
}
