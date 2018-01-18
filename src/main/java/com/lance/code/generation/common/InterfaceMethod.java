package com.lance.code.generation.common;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.lance.code.generation.domain.TableInfo;

public final class InterfaceMethod {

	/**
	 * Save Method
	 * @return
	 * 2016年8月24日上午10:00:59
	 */
	public static String mapperSave(TableInfo info) {
		StringBuilder builder = new StringBuilder();
		//实体类注释
		builder.append(KeyWords.Tab).append("/**")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* 保存对象")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ").append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("*/")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("int save(")
		.append(JavaBeanHandler.domainClassName(info.getTableName()))
		.append(" info);")
		.append(KeyWords.NEWLINE);
		
		return builder.toString();
	}
	
	/**
	 * Update Method
	 * @return
	 * 2016年8月24日上午10:00:59
	 */
	public static String mapperUpdate(TableInfo info) {
		StringBuilder builder = new StringBuilder();
		//实体类注释
		builder.append(KeyWords.Tab).append("/**")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* 修改对象")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ").append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("*/")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("int update(")
		.append(JavaBeanHandler.domainClassName(info.getTableName()))
		.append(" info);")
		.append(KeyWords.NEWLINE);
		
		return builder.toString();
	}
	
	/**
	 * Delete Method
	 * @return
	 * 2016年8月24日上午10:00:59
	 */
	public static String mapperDelete() {
		StringBuilder builder = new StringBuilder();
		//实体类注释
		builder.append(KeyWords.Tab).append("/**")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* 删除对象")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ").append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("*/")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("int delete(int id);")
		.append(KeyWords.NEWLINE);
		
		return builder.toString();
	}
	
	/**
	 * Delete Method
	 * @return
	 * 2016年8月24日上午10:00:59
	 */
	public static String mapperFindOne(TableInfo info) {
		StringBuilder builder = new StringBuilder();
		//实体类注释
		builder.append(KeyWords.Tab).append("/**")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* 根据ID查询对象")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ").append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("*/")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append(JavaBeanHandler.domainClassName(info.getTableName())).append(" findOne(int id);")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE);
		return builder.toString();
	}
	
	
	
	/**
	 * 获取分页列表
	 * @param info
	 * @return
	 */
	public static String mapperGetListPage(TableInfo info){
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append("/**")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* 分页查询")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ")
		.append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* @since ")
		.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("* @param param 条件")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("* @return 返回结果")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("*/")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("List<")
		.append(JavaBeanHandler.domainClassName(info.getTableName()))
		.append("> getListPage(Map<Object, Object> param);")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE);
		return builder.toString();
	}
	
	
	
	/**
	 * 获取分页总数
	 * @param info
	 * @return
	 */
	public static String mapperGetTotalCount(TableInfo info){
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append("/**")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* 获取总数")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ")
		.append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* @since ")
		.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("* @param param 条件")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("* @return 总数")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("*/")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("long getTotalCount(Map<Object, Object> param);")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE);
		return builder.toString();
	}
	
}
