package com.lance.code.generation.common;

import java.io.Serializable;

/**
* API接口返回对象
* 
* @author zhangchangyuan
* @date 2016-02-24
*/
public class ApiReturnObject implements Serializable {

	private static final long serialVersionUID = -5179774700249383156L;

	/**
	 * 业务操作编码
	 */
	private String errorCode;
	
	/**
	 * 业务操作信息
	 */
	private String errorMessage;
	
	/**
	 * 业务操作返回结果
	 */
	private Object returnObject;

	public ApiReturnObject(Object returnObject) {
		StateCode stateCode = StateCode.CODE_000;
		this.errorCode = stateCode.getCode();
		this.errorMessage = stateCode.getDescription();
		this.returnObject = returnObject;
	}

	public ApiReturnObject(StateCode stateCode) {
		this.errorCode = stateCode.getCode();
		this.errorMessage = stateCode.getDescription();
		this.returnObject = null;
	}
	
	public ApiReturnObject(StateCode stateCode, String errorMessage, Object returnObject) {
		this.errorCode = stateCode.getCode();
		this.errorMessage = errorMessage;
		this.returnObject = returnObject;
	}
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	
	@Override
	public String toString() {
		return "errorCode: " + errorCode + ", errorMessage: " + errorMessage + "returnObject: " + returnObject;
	}
}
