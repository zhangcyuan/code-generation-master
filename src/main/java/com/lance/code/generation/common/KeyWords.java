package com.lance.code.generation.common;

public interface KeyWords {
	/**package关键字*/
	String PACKAGE = "package";
	
	/**interface关键字*/
	String INTERFACE = "interface";
	
	/** extends关键字 **/
	String EXTENDS = "extends";
	 
	/**class关键字*/
	String CLASS = "class";
	
	/**public关键字*/
	String PUBLIC = "public";
	
	/**private关键字*/
	String PRIVATE = "private";
	
	/**implements关键字*/
	String IMPLEMENTS = "implements";
	
	/**Serializable关键字*/
	String Serial = "Serializable";
	
	/**实体类通用字段*/
	String BaseDomain = "BaseDomain";
	
	/**import关键字*/
	String IMPORT = "import";
	
	/**get关键字*/
	String GET = "get";
	
	/**set关键字*/
	String SET = "set";
	
	/**return关键字*/
	String RETURN = "return ";
	
	/**void关键字*/
	String VOID = "void ";
	
	/**this关键字*/
	String THIS = "this";
	
	/**=关键字*/
	String EQUAL = " = ";
	
	/**空格*/
	String SPACE = " ";
	
	/**点号*/
	String DOT = ".";
	
	/**分号*/
	String SEMICOLON = ";";
	
	/**换行*/
	String NEWLINE = "\r\n";
	
	/**Tab符号*/
	String Tab = "\t";
	
	
	/** 大于号 >**/
	String GT = ">";
	
	/**小于号 < **/
	String LT = "<";
	
	/** @Override 重载 **/
	String OVERRIDE = "@Override";
	
	/** @Controller 控制器  **/
	String CONTROLLER = "@Controller";
	
	/** @RequestMapping 映射  **/
	String REQUESTMAPPING = "@RequestMapping";
	
	/** @ResponseBody 响应体  **/
	String RESPONSEBODY = "@ResponseBody";
	
	/**@Autowired 资源引用**/
	String AUTOWIRED = "@Autowired";
	
	/** 错误码 类名 */
	String STATECODE = "StateCode";
	
	/** 通用SERVICE类名 */
	String COMMON_SERVICE = "MyBaseService";
	
	/** 通用Mapper类名 */
	String COMMON_MAPPER = "MyMapper";
	
	/** API响应结果类名 */
	String APIRETURNOBJECT ="ApiReturnObject";
	
	/** 分页类名类名 */
	String PAGE_NAME = "Page";
	

}
