package com.lance.code.generation.common;

import java.util.List;

/**
 * 通用基础接口
 * @author Administrator
 *
 */
public interface MyBaseService<T> {
	
	
	/**
	 * 根据属性获取单个
	 * @since 2018-01-10 15:49:33
	 */
	public T getOne(T record);
	
	/**
	 * 根据主键获取对象
	 * @since 2018-01-10 15:49:33
	 */
	public T getByPrimaryKey(Object value);
	
	/**
	 * 
	 * 获取列表
	 * @since 2018-01-10 15:49:33
	 */
	public List<T> list(T record);
	
	/**
	 * 新增
	 * @since 2018-01-10 15:49:33
	 */
	public int save(T record);
	
	/**
	 * 修改
	 * @since 2018-01-10 15:49:33
	 */
	public int update(T record);
	
	/**
	 * 根据对象删除
	 * @since 2018-01-10 15:49:33
	 */
	public int delete(T record);
	
	
	/**
	 * 根据主键删除
	 * @since 2018-01-10 15:49:33
	 */
	public int deleteByPrimaryKey(Integer id);
}
