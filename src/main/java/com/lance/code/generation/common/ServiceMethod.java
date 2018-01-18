package com.lance.code.generation.common;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.lance.code.generation.domain.ColumnInfo;
import com.lance.code.generation.domain.TableInfo;

public final class ServiceMethod {

	public static String serviceGetOne(TableInfo info) {
		String importMapper = JavaBeanHandler.className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append(KeyWords.OVERRIDE).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.PUBLIC).append(KeyWords.SPACE)
				.append(JavaBeanHandler.domainClassName(info.getTableName())).append(KeyWords.SPACE).append("getOne(")
				.append(JavaBeanHandler.domainClassName(info.getTableName())).append(KeyWords.SPACE).append("record) {")
				.append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab).append(KeyWords.RETURN)
				.append(StringUtils.uncapitalize(importMapper)).append(KeyWords.DOT).append("selectOne(record)")
				.append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE).append(KeyWords.Tab).append("}")
				.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		return builder.toString();
	}
	
	public static String serviceGetByPrimaryKey(TableInfo info) {
		String importMapper = JavaBeanHandler.className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append(KeyWords.OVERRIDE).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.PUBLIC).append(KeyWords.SPACE)
				.append(JavaBeanHandler.domainClassName(info.getTableName())).append(KeyWords.SPACE).append("getByPrimaryKey(Object value) {")
				.append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab).append(KeyWords.RETURN)
				.append(StringUtils.uncapitalize(importMapper)).append(KeyWords.DOT).append("selectByPrimaryKey(value)")
				.append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE).append(KeyWords.Tab).append("}")
				.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		return builder.toString();
	}
	
	public static String serviceList(TableInfo info) {
		String importMapper = JavaBeanHandler.className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append(KeyWords.OVERRIDE).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append("List").append(KeyWords.LT)
				.append(JavaBeanHandler.domainClassName(info.getTableName())).append(KeyWords.GT).append(KeyWords.SPACE)
				.append("list").append("(").append(JavaBeanHandler.domainClassName(info.getTableName()))
				.append(KeyWords.SPACE).append(" record) {").append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.Tab).append(KeyWords.RETURN).append(StringUtils.uncapitalize(importMapper))
				.append(KeyWords.DOT).append("select(record)").append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
				.append(KeyWords.Tab).append("}").append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		return builder.toString();
	}

	public static String serviceSave(TableInfo info) {
		String importMapper = JavaBeanHandler.className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append(KeyWords.OVERRIDE).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append("int save").append("(")
				.append(JavaBeanHandler.domainClassName(info.getTableName())).append(KeyWords.SPACE)
				.append(" record) {").append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab)
				.append(KeyWords.RETURN)
				.append(StringUtils.uncapitalize(importMapper)).append(KeyWords.DOT)
				.append("insertUseGeneratedKeys(record)").append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
				.append(KeyWords.Tab).append("}").append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		return builder.toString();
	}

	public static String serviceUpdate(TableInfo info) {
		String importMapper = JavaBeanHandler.className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append(KeyWords.OVERRIDE).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append("int update").append("(")
				.append(JavaBeanHandler.domainClassName(info.getTableName())).append(KeyWords.SPACE)
				.append(" record) {").append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab)
				.append(KeyWords.RETURN).append(StringUtils.uncapitalize(importMapper)).append(KeyWords.DOT)
				.append("updateByPrimaryKeySelective(record)").append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
				.append(KeyWords.Tab).append("}").append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		return builder.toString();
	}

	public static String serviceDelete(TableInfo info) {
		String importMapper = JavaBeanHandler.className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append(KeyWords.OVERRIDE).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append("int delete").append("(")
				.append(JavaBeanHandler.domainClassName(info.getTableName())).append(KeyWords.SPACE)
				.append(" record) {").append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab)
				.append(KeyWords.RETURN).append(StringUtils.uncapitalize(importMapper)).append(KeyWords.DOT)
				.append("delete(record)").append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append("}").append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		return builder.toString();
	}

	public static String serviceDeleteByPrimaryKey(TableInfo info) {
		String importMapper = JavaBeanHandler.className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append(KeyWords.OVERRIDE).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append("int deleteByPrimaryKey(Integer id) {")
				.append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab).append(KeyWords.RETURN)
				.append(KeyWords.SPACE).append(StringUtils.uncapitalize(importMapper)).append(KeyWords.DOT)
				.append("deleteByPrimaryKey(id)").append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
				.append(KeyWords.Tab).append("}").append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		return builder.toString();
	}
	
	
	public static String serviceGetDataPage(TableInfo info) {
		String importMapper = JavaBeanHandler.className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		String modelName = JavaBeanHandler.domainClassName(info.getTableName());
		StringBuilder builder = new StringBuilder();
		builder
		.append(KeyWords.Tab)
		.append(KeyWords.OVERRIDE)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.PUBLIC)
		.append(KeyWords.SPACE)
		.append(KeyWords.PAGE_NAME)
		.append(KeyWords.LT)
		.append(modelName)
		.append(KeyWords.GT)
		.append(" getDataPage(Page<")
		.append(modelName)
		.append("> page, ")
		.append(modelName)
		.append(KeyWords.SPACE)
		.append(StringUtils.uncapitalize(modelName))
		.append(") {")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.Tab)
		.append("Map<Object, Object> param = ParameterWrapper.buildPageParameter(page, ")
		.append(StringUtils.uncapitalize(modelName))
		.append(");")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.Tab)
		.append("page.setResult(")
		.append(StringUtils.uncapitalize(importMapper))
		.append(".getListPage(param));")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.Tab)
		.append("page.setTotalCount(")
		.append(StringUtils.uncapitalize(importMapper))
		.append(".getTotalCount(param));")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.Tab)
		.append("return page;")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("}")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE);
		return builder.toString();
	}
	
	
	

	public static String modelToString(TableInfo info, List<ColumnInfo> columns) {
		StringBuilder builder = new StringBuilder();
		builder.append(KeyWords.Tab).append(KeyWords.OVERRIDE).append(KeyWords.NEWLINE).append(KeyWords.Tab)
				.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append("String toString() {").append(KeyWords.NEWLINE)
				.append(KeyWords.Tab).append(KeyWords.Tab).append(KeyWords.RETURN).append(KeyWords.SPACE).append("\"")
				.append(JavaBeanHandler.domainClassName(info.getTableName())).append(KeyWords.SPACE).append("[");
		int i = 0;
		for (ColumnInfo c : columns) {
			i++;
			builder.append(JavaBeanHandler.attrName(c.getColumnName(), false)).append(KeyWords.EQUAL).append("\"+")
					.append(JavaBeanHandler.attrName(c.getColumnName(), false));
			if (i != columns.size()) {
				builder.append("+\",");
			} else {
				builder.append("+\"");
			}
			if (i % 4 == 0 && i != columns.size()) {
				builder.append("\"").append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab)
						.append(KeyWords.Tab).append("+ \"");
			}
		}
		builder.append(KeyWords.SPACE).append("]\";").append(KeyWords.NEWLINE).append(KeyWords.Tab).append("}")
				.append(KeyWords.NEWLINE);
		return builder.toString();
	}
}
