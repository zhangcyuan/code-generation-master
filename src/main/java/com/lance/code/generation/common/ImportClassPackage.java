package com.lance.code.generation.common;

public interface ImportClassPackage {
	/**集合*/
	String MAP = "import java.util.Map;";
	/**列表*/
	String LIST = "import java.util.List;";
	/**日志*/
	String LOGGER = "import org.slf4j.Logger;";
	/**日志工厂*/
	String LOGGERFACTORY = "import org.slf4j.LoggerFactory;";
	/**引用*/
	String AUTOWIRED = "import org.springframework.beans.factory.annotation.Autowired;";
	/**控制器*/
	String CONTROLLER = "import org.springframework.stereotype.Controller;";
	/**请求映射*/
	String REQUESTMAPPING = "import org.springframework.web.bind.annotation.RequestMapping;";
	/**请求方式*/
	String REQUESTMETHOD = "import org.springframework.web.bind.annotation.RequestMethod;";
	/**响应内容体*/
	String RESPONSEBODY = "import org.springframework.web.bind.annotation.ResponseBody;";
	/**spring service*/
	String SERVICE = "import org.springframework.stereotype.Service;";
	/**事务*/
	String TRANSACTIONAL = "import org.springframework.transaction.annotation.Transactional;";
	/**http响应*/
	String  HTTPSERVLETRESPONSE = "import javax.servlet.http.HttpServletResponse;";
	/**GeneratedValue 自动增长值 */
	String GENERATED_VALUE = "import javax.persistence.GeneratedValue;";
	/**GenerationType 自动增长类型*/
	String GENERATED_TYPE = "import javax.persistence.GenerationType;";
	/**ID 主键 */
	String ID = "import javax.persistence.Id;";
}
