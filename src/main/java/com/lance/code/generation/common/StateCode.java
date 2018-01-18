package com.lance.code.generation.common;

/**
 * 系统错误编码
 * @author Ezio
 * 2016-03-01
 */
public enum StateCode {

	/**
	 * 业务操作成功
	 */
	CODE_000("000", "业务操作成功"),
	
	/**
	 * 服务端的未知错误
	 */
	CODE_100("100", "业务操作失败"),
	
	/**
	 * 业务操作不正确错误,请求参数不合法，包括长度，是否可空，值的范围等
	 */
	CODE_101("101", "输入参数异常"),
	
	/**
	 * 请求参数服务端验签不通过：被恶意篡改等
	 */
	CODE_102("102", "请求参数服务端验签不通过"),
	
	/**
	 * 无权访问错误
	 */
	CODE_103("103", "无权访问错误"),
	
	/**
	 * 未登录
	 */
	CODE_104("104", "用户未登录"),
	
	/**
	 * 会话过期，重新登录
	 */
	CODE_105("105", "会话过期"),
	
	/**
	 * 重复提交
	 */
	CODE_106("106", "重复提交"),
	
	/**
	 * 用户资料不完善
	 */
	CODE_107("107", "用户资料不完善"),
	
	/**
	 * http响应成功
	 */
	CODE_200("200", "http响应成功"),
	
	/**
	 * 服务端异常
	 */
	CODE_500("500", "服务端异常"),
	
	
	/**
	 * 业务操作成功标识
	 */
	SUCCESS_STATE("success", "业务操作成功"),
	
	/**
	 * 业务操作失败标识
	 */
	FAIL_STATE("fail", "业务操作失败");
	
	private String code;
	private String description;

	
	private StateCode(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "code: " + code + ", description: " + description;
	}
}
