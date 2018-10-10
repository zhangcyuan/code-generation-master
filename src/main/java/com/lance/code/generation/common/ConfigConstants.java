package com.lance.code.generation.common;

public interface ConfigConstants {
	/** 定义Schema */
	String SCHEMA = "sysu_disease_system"; // health_test_maiyou_1013

	/** 作者 */
	String AUTHOR = "@author zhangchangyuan";

	/** 定义单个表名  多个同一个模块的表名 用英文逗号分隔  可一次性生成 **/
	String TABLE_NAME = "questionnaire,question,option,question_form,form_option";
	
	/** 生成模块包名称 */
	String MOUDEL_PACKAGE = "questionnaire";
	
	/** 创建各个功能模块开关  false 为关闭   true 为打开 */
	boolean CREATE_MODEL = true;
	boolean CREATE_MAPPER = true;
	boolean CREATE_SERVICE = true;
	boolean CREATE_SERVICEIMPL = true;
	boolean CREATE_MAPPER_XML = true;
	boolean CREATE_CONTROLLER = true;
	
	/** controller更访问路径 如：/api/test/sysUsetTest   更路径为 api  一般为 后台 admin  或前端api */
	String CONTROLLER_ROOT_URL = "admin";

	/** 移除表前缀 如：td_sys_user的前缀为td_*/
	String REMOVE_TABLE_PREFIX = "";

	/** Domain后缀, CustomerInfo  Info为后缀名 */
	String DOMAIN_SUFFIX = "";

	/** service model mapper等java文件生成路径 */ // 项目改变则改变
	String FILE_PATH = "D:\\health\\hm-sysu-parent\\hm-sysu-service\\src\\main\\java";
	
	/** controller生成文件路径 */
	String CONTROLLER_PATH = "D:\\health\\hm-sysu-parent\\hm-sysu-admin\\src\\main\\java";
	
	/** api生成文件路径*/
	String API_PATH = "D:\\health\\hm-sysu-parent\\hm-sysu-api\\src\\main\\java";
	
	/** controller文件z后缀名  后台为Controller   api 为  ApiController*/
	String CONTROLLER_SUFFIX = "Controller";

	/** MyBatis SQL生成文件路径 */ // 项目改变则改变
	String SQL_PATH = "D:\\health\\hm-sysu-parent\\hm-sysu-service\\src\\main\\resources\\mysql-mapper";

	/** 生成Mapper.xml后缀名字, EX: customer.Mapper.xml */
	String SQL_MAPPER_SUFFIX = ".Mapper";

	/** 生成基础包名称 */
	String ROOT_PACKAGE = "com.byhealth.zd.jkgl.service";
	
	/** 生成web admin controller 包名称*/
	String WEB_PACKAGE = "com.byhealth.zd.jkgl.web.admin";
	/** 生成web admin controller 包名称*/
	String API_PACKAGE = "com.byhealth.zd.jkgl.web.api";
	
	/** JavaBean包名称 */
	String DOMAIN_PACKAGE = "model";

	/** service包名称 */
	String SERVICE_PACKAGE = "service";

	/** serviceImpl包名称 */
	String SERVICE_impl_PACKAGE = "impl";
	
	/** controller包名称 */
	String CONTROLLER_PACKAGE = "controller";

	/** serviceImpl后缀名 */
	String SERVICE_SUFFIX = "Impl";

	/** mapper包名称 */
	String MAPPER_PACKAGE = "mapper";
	
	/** 通用Mapper类路径 */
	String COMMON_MAPPER_PACKAGE = "com.byhealth.zd.jkgl.util";

	/** 通用Service接口路径 */
	String COMMON_SERVICE_PACKAGE = "com.byhealth.zd.jkgl.util";
	
	/** StateCode 错误码 包名 */
	String STATECODE_PACKAGE = "com.byhealth.zd.jkgl.common.system";
	
	/** apiReturnObject api返回类 包名 */
	String APIRETURNOBJECT_PACKAGE = "com.byhealth.zd.jkgl.web.utils";
	
	/** 分页工具类 包名 */
	String PAGE_PACKAGE = "com.byhealth.zd.jkgl.common.orm";


}
