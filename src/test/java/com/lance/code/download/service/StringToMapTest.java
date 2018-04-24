package com.lance.code.download.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
*
* @author zhangchangyuan
* @version 创建时间：2018年4月24日 下午2:36:15
*/
public class StringToMapTest {
	public static void main(String[] args) {
		String str = AISIDownLoadTest.readTxt(AISIDownLoadTest.txtPath);
		Map<String, Object> map = (Map<String, Object>)JSONObject.parse(str);
		System.out.println(map.get("aiss"));
		System.out.println(map.toString());
	}
}
