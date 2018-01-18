package com.lance.code.generation.common;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.transaction.annotation.Transactional;

import com.lance.code.generation.domain.ColumnInfo;
import com.lance.code.generation.domain.TableInfo;

public class JavaBeanHandler {

	public static String domainPath(){
		StringBuilder builder = new StringBuilder(ConfigConstants.FILE_PATH);
		return builder.append("\\").append(domainPackagePath()).toString();
	}
	
	public static String domainPackage(){
		StringBuilder builder = new StringBuilder(ConfigConstants.ROOT_PACKAGE);
		return builder.append(KeyWords.DOT).append(ConfigConstants.MOUDEL_PACKAGE).append(KeyWords.DOT).append(ConfigConstants.DOMAIN_PACKAGE).toString();
	}
	
	public static String domainPackagePath(){
		String path = domainPackage();
		return StringUtils.replace(path, ".", "\\");
	}
	
	/**
	 * 生成包文件头 Ex:package com.lance.code.generation.common
	 * 2016年8月16日下午2:18:16
	 */
	public static String domainPackageHeader(){
		StringBuilder builder = new StringBuilder(KeyWords.PACKAGE);
		return builder.append(KeyWords.SPACE).append(domainPackage()).toString();
	}
	
	/**
	 * 创建Domain
	 * @param info
	 * @param columns
	 * 2016年8月16日下午2:46:00
	 */
	public static String createDomain(TableInfo info, List<ColumnInfo> columns) {
		StringBuilder builder = new StringBuilder(domainPackageHeader());
		builder.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		//导入包
		.append(KeyWords.IMPORT).append(KeyWords.SPACE).append("java.io.Serializable").append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		
		//导入资源包
		Set<String> set = importPackage(columns);
		if(!set.isEmpty()) {
			for(String p: set) {
				builder.append(KeyWords.IMPORT).append(KeyWords.SPACE).append(p).append(KeyWords.SEMICOLON)
				.append(KeyWords.NEWLINE);
			}
		}
		builder.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.GENERATED_VALUE)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.GENERATED_TYPE)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.ID)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE);
		
		//实体类注释
		builder.append("/**")
		.append(KeyWords.NEWLINE)
		.append("* ").append(info.getTableComment())
		.append(KeyWords.NEWLINE)
		.append("* ").append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append("*/")
		.append(KeyWords.NEWLINE)
		
		//生成public class A implements b 
		.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append(KeyWords.CLASS)
		.append(KeyWords.SPACE).append(domainClassName(info.getTableName()))
		.append(KeyWords.SPACE).append(KeyWords.IMPLEMENTS).append(KeyWords.SPACE)
		.append(KeyWords.Serial).append(KeyWords.SPACE)
		.append("{").append(KeyWords.NEWLINE)
		
		//serialVersionUID
		.append(KeyWords.Tab)
		.append("private static final long serialVersionUID = ")
		.append(RandomUtils.nextInt(1, Integer.MAX_VALUE)).append("L")
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		
		int i = 0;
		//属性
		for(ColumnInfo c: columns) {
			i++;
			builder.append(KeyWords.Tab)
			.append("/**").append(c.getColumnComment()).append("*/")
			.append(KeyWords.NEWLINE);
			if(i==1){
				builder.append(KeyWords.Tab)
				.append("@Id")
				.append(KeyWords.NEWLINE)
				.append(KeyWords.Tab)
				.append("@GeneratedValue(strategy = GenerationType.IDENTITY)");
			}
			builder.append(KeyWords.NEWLINE).append(KeyWords.Tab)
			.append(KeyWords.PRIVATE).append(KeyWords.SPACE)
			.append(changeType(c.getDataType())).append(KeyWords.SPACE)
			.append(attrName(c.getColumnName(), false)).append(KeyWords.SEMICOLON)
			.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE);
		}
		
		//GET/SET
		for(ColumnInfo c: columns) {
			builder.append(KeyWords.Tab)
			.append(KeyWords.PUBLIC).append(KeyWords.SPACE)
			.append(changeType(c.getDataType())).append(KeyWords.SPACE)
			.append(KeyWords.GET).append(attrName(c.getColumnName(), true)).append("() {")
			.append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab)
			.append(KeyWords.RETURN).append(attrName(c.getColumnName(), false)).append(KeyWords.SEMICOLON)
			.append(KeyWords.NEWLINE)
			.append(KeyWords.Tab).append("}")
			.append(KeyWords.NEWLINE)
			.append(KeyWords.NEWLINE);
			
			builder.append(KeyWords.Tab)
			.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append(KeyWords.VOID)
			.append(KeyWords.SET).append(attrName(c.getColumnName(), true)).append("(")
			.append(changeType(c.getDataType())).append(KeyWords.SPACE).append(attrName(c.getColumnName(), false)).append(") {")
			.append(KeyWords.NEWLINE).append(KeyWords.Tab).append(KeyWords.Tab)
			.append(KeyWords.THIS).append(KeyWords.DOT).append(attrName(c.getColumnName(), false)).append(KeyWords.EQUAL)
			.append(attrName(c.getColumnName(), false)).append(KeyWords.SEMICOLON)
			.append(KeyWords.NEWLINE)
			.append(KeyWords.Tab).append("}")
			.append(KeyWords.NEWLINE)
			.append(KeyWords.NEWLINE);
		}
		
		//toString方法
		builder.append(ServiceMethod.modelToString(info, columns));
		
		builder.append("}");
		return builder.toString();
	}
	
	//------------------------------------生成Mapper-----------------------------------------
	public static String mapperPackage(){
		StringBuilder builder = new StringBuilder(ConfigConstants.ROOT_PACKAGE);
		return builder.append(KeyWords.DOT).append(ConfigConstants.MOUDEL_PACKAGE).append(KeyWords.DOT).append(ConfigConstants.MAPPER_PACKAGE).toString();
	}
	
	public static String mapperPackagePath(){
		String path = mapperPackage();
		return StringUtils.replace(path, ".", "\\");
	}
	
	public static String mapperPath(){
		StringBuilder builder = new StringBuilder(ConfigConstants.FILE_PATH);
		return builder.append("\\").append(mapperPackagePath()).toString();
	}
	
	public static String mapperPackageHeader(){
		StringBuilder builder = new StringBuilder(KeyWords.PACKAGE);
		return builder.append(KeyWords.SPACE).append(mapperPackage()).toString();
	}
	
	/**
	 * 创建Mapper
	 * @param info
	 * 2016年8月16日下午2:46:00
	 */
	public static String createMapper(TableInfo info) {
		StringBuilder builder = new StringBuilder(mapperPackageHeader());
		
		builder.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.LIST)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.MAP)
		
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(domainPackage()).append(KeyWords.DOT).append(domainClassName(info.getTableName()))
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(ConfigConstants.COMMON_MAPPER_PACKAGE).append(KeyWords.DOT).append(KeyWords.COMMON_MAPPER)
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		//实体类注释
		.append("/**")
		.append(KeyWords.NEWLINE)
		.append("* ").append(info.getTableComment())
		.append(KeyWords.NEWLINE)
		.append("* ").append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append("*/")
		.append(KeyWords.NEWLINE)
		
		//生成public interface AMapper 
		.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append(KeyWords.INTERFACE)
		.append(KeyWords.SPACE).append(className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE))
		.append(KeyWords.SPACE).append(KeyWords.EXTENDS).append(KeyWords.SPACE)
		.append(KeyWords.COMMON_MAPPER).append(KeyWords.LT).append(domainClassName(info.getTableName())).append(KeyWords.GT)
		
		.append(KeyWords.SPACE).append("{")
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		/*//保存方法
		.append(InterfaceMethod.mapperSave(info))
		.append(KeyWords.NEWLINE)
		//修改对象
		.append(InterfaceMethod.mapperUpdate(info))
		.append(KeyWords.NEWLINE)
		//删除对象
		.append(InterfaceMethod.mapperDelete())
		.append(KeyWords.NEWLINE)
		//查询对象根据ID
		.append(InterfaceMethod.mapperFindOne(info))*/
		
		.append(InterfaceMethod.mapperGetListPage(info))
		.append(InterfaceMethod.mapperGetTotalCount(info))
		
		.append("}");
		return builder.toString();
	}
	
	//------------------------------------生成XML-----------------------------------------
	public static String xmlPath(){
		return ConfigConstants.SQL_PATH+"\\"+ConfigConstants.MOUDEL_PACKAGE;
	}
	
	public static String createXMLMapper(TableInfo info, List<ColumnInfo> columns) {
		StringBuilder builder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		builder.append(KeyWords.NEWLINE)
		.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >")
		.append(KeyWords.NEWLINE)
		.append("<mapper namespace=\"").append(mapperPackage()).append(KeyWords.DOT)
		.append(className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE)).append("\">")
		.append(KeyWords.NEWLINE)
		//加入EhcacheCache缓存, 可以后期替换掉RedisCache
		//.append(KeyWords.Tab).append("<cache type=\"org.mybatis.caches.ehcache.EhcacheCache\"/>")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("<!-- ").append(info.getTableComment()).append(" -->")
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		
		.append(XMLMethod.xmlPage(info, columns))
		
		
		
		/*//Save对象
		.append(XMLMethod.xmlSave(info, columns))
		.append(KeyWords.NEWLINE)
		
		//Update对象
		.append(XMLMethod.xmlUpdate(info, columns))
		.append(KeyWords.NEWLINE)
		
		//删除对象
		.append(XMLMethod.xmlDelete(info))
		.append(KeyWords.NEWLINE)
		
		//查询对象根据ID
		.append(XMLMethod.xmlFindOne(info))*/
		
		
		.append("</mapper>");
		return builder.toString();
	}
	
	//------------------------------------生成Service-----------------------------------------
	public static String servicePackage(){
		StringBuilder builder = new StringBuilder(ConfigConstants.ROOT_PACKAGE);
		return builder.append(KeyWords.DOT).append(ConfigConstants.MOUDEL_PACKAGE).append(KeyWords.DOT).append(ConfigConstants.SERVICE_PACKAGE).toString();
	}
	
	public static String servicePackagePath(){
		String path = servicePackage();
		return StringUtils.replace(path, ".", "\\");
	}
	
	public static String servicePath(){
		StringBuilder builder = new StringBuilder(ConfigConstants.FILE_PATH);
		return builder.append("\\").append(servicePackagePath()).toString();
	}
	
	public static String servicePackageHeader(){
		StringBuilder builder = new StringBuilder(KeyWords.PACKAGE);
		return builder.append(KeyWords.SPACE).append(servicePackage()).toString();
	}
	
	
	/**
	 * 创建Service
	 * @param info
	 * 2016年8月16日下午2:46:00
	 */
	public static String createService(TableInfo info) {
		StringBuilder builder = new StringBuilder(servicePackageHeader());
		builder.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(ConfigConstants.PAGE_PACKAGE)
		.append(KeyWords.DOT).append(KeyWords.PAGE_NAME)
		.append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(domainPackage()).append(KeyWords.DOT).append(domainClassName(info.getTableName()))
		.append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(ConfigConstants.COMMON_SERVICE_PACKAGE).append(KeyWords.DOT).append(KeyWords.COMMON_SERVICE)
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		//实体类注释
		.append("/**")
		.append(KeyWords.NEWLINE)
		.append("* ").append(info.getTableComment())
		.append(KeyWords.NEWLINE)
		.append("* ").append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append("*/")
		.append(KeyWords.NEWLINE)
		
		//生成public interface AService 
		.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append(KeyWords.INTERFACE)
		.append(KeyWords.SPACE).append(className(info.getTableName(), ConfigConstants.SERVICE_PACKAGE)).append(KeyWords.SPACE)
		.append(KeyWords.EXTENDS).append(KeyWords.SPACE).append(KeyWords.COMMON_SERVICE).append(KeyWords.LT)
		.append(domainClassName(info.getTableName())).append(KeyWords.GT)
		.append(KeyWords.SPACE).append("{")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		
		.append(KeyWords.Tab).append("/**")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* 分页查询列表")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* ")
		.append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab).append("* @since ")
		.append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("* @param page 分页条件")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("* @param ")
		.append(StringUtils.uncapitalize(domainClassName(info.getTableName())))
		.append("查询条件")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("* @return 返回分页结果")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("*/")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append("Page<")
		.append(domainClassName(info.getTableName()))
		.append("> getDataPage(Page<")
		.append(domainClassName(info.getTableName()))
		.append("> page, ")
		.append(domainClassName(info.getTableName()))
		.append(KeyWords.SPACE)
		.append(StringUtils.uncapitalize(domainClassName(info.getTableName())))
		.append(");")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		.append("}");
		return builder.toString();
	}
	
	
	//------------------------------------生成ServiceImpl-----------------------------------------
		public static String serviceImplPackage(){
			StringBuilder builder = new StringBuilder(ConfigConstants.ROOT_PACKAGE);
			return builder.append(KeyWords.DOT).append(ConfigConstants.MOUDEL_PACKAGE).append(KeyWords.DOT)
					.append(ConfigConstants.SERVICE_PACKAGE).append(KeyWords.DOT)
					.append(ConfigConstants.SERVICE_impl_PACKAGE).toString();
		}
		
		public static String serviceImplPackagePath(){
			String path = serviceImplPackage();
			return StringUtils.replace(path, ".", "\\");
		}
		
		public static String serviceImplPath(){
			StringBuilder builder = new StringBuilder(ConfigConstants.FILE_PATH);
			return builder.append("\\").append(serviceImplPackagePath()).toString();
		}
		
		public static String serviceImplPackageHeader(){
			StringBuilder builder = new StringBuilder(KeyWords.PACKAGE);
			return builder.append(KeyWords.SPACE).append(serviceImplPackage()).toString();
		}
	/**
	 * 创建ServiceImpl
	 * @param info
	 * 2016年8月16日下午2:46:00
	 */
	public static String createServiceImpl(TableInfo info) {
		//Service导入的Mapper接口类
		String importMapper = className(info.getTableName(), ConfigConstants.MAPPER_PACKAGE);
		
		String importService = className(info.getTableName(), ConfigConstants.SERVICE_PACKAGE);
		
		StringBuilder builder = new StringBuilder(serviceImplPackageHeader());
		builder.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		//导入包
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(ConfigConstants.PAGE_PACKAGE)
		.append(KeyWords.DOT).append(KeyWords.PAGE_NAME)
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.LIST)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.MAP)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.AUTOWIRED)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.SERVICE)
		.append(KeyWords.NEWLINE)
		.append(ImportClassPackage.TRANSACTIONAL)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE).append(servicePackage())
		.append(KeyWords.DOT).append(importService)
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE).append(mapperPackage())
		.append(KeyWords.DOT).append(importMapper)
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT)
		.append(KeyWords.SPACE)
		.append(ConfigConstants.PAGE_PACKAGE)
		.append(KeyWords.DOT)
		.append(KeyWords.PAGE_NAME)
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(ConfigConstants.PAGE_PACKAGE)
		.append(KeyWords.DOT).append("mybatis.ParameterWrapper;")
		.append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(domainPackage()).append(KeyWords.DOT).append(domainClassName(info.getTableName()))
		.append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		
		//实体类注释
		.append("/**")
		.append(KeyWords.NEWLINE)
		.append("* ").append(info.getTableComment())
		.append(KeyWords.NEWLINE)
		.append("* ").append(ConfigConstants.AUTHOR)
		.append(KeyWords.NEWLINE)
		.append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append("*/")
		.append(KeyWords.NEWLINE)
		.append("@Service")
		.append(KeyWords.NEWLINE)
		.append("@Transactional")
		.append(KeyWords.NEWLINE)
		
		//生成public class AServiceImpl 
		.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append(KeyWords.CLASS)
		.append(KeyWords.SPACE).append(className(info.getTableName(), ConfigConstants.SERVICE_SUFFIX))
		.append(KeyWords.SPACE).append(KeyWords.IMPLEMENTS).append(KeyWords.SPACE)
		.append(className(info.getTableName(), ConfigConstants.SERVICE_PACKAGE)).append("{")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.AUTOWIRED)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.PRIVATE).append(KeyWords.SPACE).append(importMapper)
		.append(KeyWords.SPACE).append(StringUtils.uncapitalize(importMapper)).append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		//基本方法
		.append(ServiceMethod.serviceGetOne(info))
		.append(ServiceMethod.serviceGetOne(info))
		.append(ServiceMethod.serviceList(info))
		.append(ServiceMethod.serviceSave(info))
		.append(ServiceMethod.serviceUpdate(info))
		.append(ServiceMethod.serviceDelete(info))
		.append(ServiceMethod.serviceDeleteByPrimaryKey(info))
		.append(ServiceMethod.serviceGetDataPage(info))
		
		.append("}");
		return builder.toString();
	}
	
	//------------------------------------生成contorller-----------------------------------------
			public static String contorllerPackage(){
				StringBuilder builder = new StringBuilder(ConfigConstants.WEB_PACKAGE);
				return builder.append(KeyWords.DOT).append(ConfigConstants.CONTROLLER_PACKAGE).append(KeyWords.DOT)
						.append(ConfigConstants.MOUDEL_PACKAGE).toString();
			}
			
			public static String contorllerPackagePath(){
				String path = contorllerPackage();
				return StringUtils.replace(path, ".", "\\");
			}
			
			public static String contorllerPath(){
				StringBuilder builder = new StringBuilder(ConfigConstants.CONTROLLER_PATH);
				return builder.append("\\").append(contorllerPackagePath()).toString();
			}
			
			public static String contorllerPackageHeader(){
				StringBuilder builder = new StringBuilder(KeyWords.PACKAGE);
				return builder.append(KeyWords.SPACE).append(contorllerPackage()).toString();
			}
	
	/**
	 * 创建Controller
	 * @param info
	 * 2018年1月11日下午2:46:00
	 */
	public static String createController(TableInfo info) {
		
		String importService = className(info.getTableName(), ConfigConstants.SERVICE_PACKAGE);
		
		StringBuilder builder = new StringBuilder(contorllerPackageHeader());
		builder.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		//导入包
		.append(ImportClassPackage.HTTPSERVLETRESPONSE)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		.append(ImportClassPackage.LIST)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		.append(ImportClassPackage.LOGGER).append(KeyWords.NEWLINE)
		.append(ImportClassPackage.LOGGERFACTORY).append(KeyWords.NEWLINE)
		.append(ImportClassPackage.AUTOWIRED).append(KeyWords.NEWLINE)
		.append(ImportClassPackage.CONTROLLER).append(KeyWords.NEWLINE)
		.append(ImportClassPackage.REQUESTMAPPING).append(KeyWords.NEWLINE)
		.append(ImportClassPackage.REQUESTMETHOD).append(KeyWords.NEWLINE)
		.append(ImportClassPackage.RESPONSEBODY).append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT)
		.append(KeyWords.SPACE)
		.append(ConfigConstants.PAGE_PACKAGE)
		.append(KeyWords.DOT)
		.append(KeyWords.PAGE_NAME)
		.append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE).append(ConfigConstants.STATECODE_PACKAGE)
		.append(KeyWords.DOT).append(KeyWords.STATECODE).append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE)
		.append(domainPackage()).append(KeyWords.DOT).append(domainClassName(info.getTableName()))
		.append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE).append(servicePackage())
		.append(KeyWords.DOT).append(importService)
		.append(KeyWords.SEMICOLON).append(KeyWords.NEWLINE)
		
		.append(KeyWords.IMPORT).append(KeyWords.SPACE).append(ConfigConstants.APIRETURNOBJECT_PACKAGE)
		.append(KeyWords.DOT).append(KeyWords.APIRETURNOBJECT).append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		
		.append("/**").append(KeyWords.NEWLINE)
		.append("* ").append(ConfigConstants.AUTHOR).append(KeyWords.NEWLINE)
		.append("* ").append("@since ").append(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))
		.append(KeyWords.NEWLINE)
		.append("*/")
		.append(KeyWords.NEWLINE)
		.append(KeyWords.CONTROLLER).append(KeyWords.NEWLINE)
		.append(KeyWords.REQUESTMAPPING).append("(\"/")
		.append(ConfigConstants.CONTROLLER_ROOT_URL).append("/").append(ConfigConstants.MOUDEL_PACKAGE)
		.append("/").append(StringUtils.uncapitalize(JavaBeanHandler.domainClassName(info.getTableName()))).append("\")")
		.append(KeyWords.NEWLINE)
		
		.append(KeyWords.PUBLIC).append(KeyWords.SPACE).append(KeyWords.CLASS).append(KeyWords.SPACE)
		.append(JavaBeanHandler.domainClassName(info.getTableName())).append(ConfigConstants.CONTROLLER_SUFFIX)
		.append(KeyWords.SPACE).append("{").append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.PRIVATE).append(KeyWords.SPACE)
		.append("Logger logger = LoggerFactory.getLogger(")
		.append(JavaBeanHandler.domainClassName(info.getTableName())).append(ConfigConstants.CONTROLLER_SUFFIX)
		.append(KeyWords.DOT).append(KeyWords.CLASS).append(")").append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE).append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.AUTOWIRED)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.Tab)
		.append(KeyWords.PRIVATE).append(KeyWords.SPACE)
		.append(importService).append(KeyWords.SPACE)
		.append(StringUtils.uncapitalize(importService)).append(KeyWords.SEMICOLON)
		.append(KeyWords.NEWLINE)
		.append(KeyWords.NEWLINE)
		
		//基本方法
		.append(ControllerMethod.controllerGetOne(info))
		.append(ControllerMethod.controllerList(info))
		.append(ControllerMethod.controllerPage(info))
		.append(ControllerMethod.controllerSave(info))
		.append(ControllerMethod.controllerUpdate(info))
		.append(ControllerMethod.controllerDelete(info))
		.append(ControllerMethod.controllerDeleteByPrimaryKey(info))
		
		.append("}");
		return builder.toString();
		
	}
	
	
	/**------------------------------------------------------------------------------
	 * 处理类名称
	 * @param className
	 * @return
	 * 2016年8月16日下午2:42:20
	 */
	public static String domainClassName(String className){
		className = StringUtils.substringAfter(className, ConfigConstants.REMOVE_TABLE_PREFIX);
		String[] _names = StringUtils.split(className, "_");
		
		StringBuilder builder = new StringBuilder();
		for(String name: _names){
			builder.append(StringUtils.capitalize(name));
		}
		return builder.append(StringUtils.capitalize(ConfigConstants.DOMAIN_SUFFIX)).toString();
	}
	
	/**
	 * 处理类名称
	 * @param className
	 * @param suffix
	 * 2016年8月16日下午5:44:13
	 */
	public static String className(String className, String suffix){
		className = StringUtils.substringAfter(className, ConfigConstants.REMOVE_TABLE_PREFIX);
		String[] _names = StringUtils.split(className, "_");
		
		StringBuilder builder = new StringBuilder();
		for(String name: _names){
			builder.append(StringUtils.capitalize(name));
		}
		return builder.append(StringUtils.capitalize(suffix)).toString();
	}
	
	/**
	 * 处理属性名字
	 * @param attr
	 * 2016年8月16日下午4:15:12
	 */
	public static String attrName(String attr, boolean isCapitalize){
		String[] _attrs = StringUtils.split(attr, "_");
		
		StringBuilder builder = new StringBuilder();
		for(int i=0; i<_attrs.length; i++){
			if(i == 0 && !isCapitalize) {
				builder.append(_attrs[0]);
			}else {
				builder.append(StringUtils.capitalize(_attrs[i]));
			}
		}
		return builder.toString();
	}
	
	/**
	 * 转化类型
	 * @param type
	 * @return
	 * 2016年8月16日下午4:29:29
	 */
	public static String changeType(String type){
		switch (type.toLowerCase()) {
			case "date": ;
			case "timestamp": ;
			case "datetime": return "Date";
			case "decimal": return "BigDecimal";
			case "text": ;
			case "varchar": return "String";
			case "tinyint": return "Integer";
			case "int": return "Integer";
		}
		return type;
	}
	
	public static Set<String> importPackage(List<ColumnInfo> columns){
		Set<String> set = new HashSet<>(); 
		for(ColumnInfo c: columns) {
			switch (c.getDataType().toLowerCase()) {
				case "datetime": set.add("java.util.Date"); break;
				case "decimal": set.add("java.math.BigDecimal"); break;
			}
		}
		return set;
	}
}
