package com.lance.code.generation.common;

import java.util.Date;

/**
*
* @author zhangchangyuan
* @version 创建时间：2018年9月11日 上午11:12:57
*/
public class BaseDomain {
	
	/**
	 * 创建人
	 */
	private Integer creator;
	/**
	 * 修改人
	 */
	private Integer modifier;
	/**
	 * 创建时间	
	 */
	private Date create_date;
	/**
	 * 修改时间	
	 */
	private Date modify_date;
	/**
	 * 是否有效说明：1：有效 0：无效
	 */
	private Integer is_valid;
	
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Integer getModifier() {
		return modifier;
	}
	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public Integer getIs_valid() {
		return is_valid;
	}
	public void setIs_valid(Integer is_valid) {
		this.is_valid = is_valid;
	}
	
	

}
