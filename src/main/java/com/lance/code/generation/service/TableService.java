package com.lance.code.generation.service;

import java.util.List;

import com.lance.code.generation.domain.TableInfo;

public interface TableService {

	/**
	 * 根据Schema查询所有表信息
	 * @param schema
	 * @return
	 * 2016年8月16日上午9:44:53
	 */
	List<TableInfo> findAll(String schema);
	
	/**
	 * 根据表信息查询表
	 * @param tableName
	 * @param schemaName
	 * @return
	 */
	List<TableInfo> listByTableInfo(String tableName,String schemaName);

	void run();
}
