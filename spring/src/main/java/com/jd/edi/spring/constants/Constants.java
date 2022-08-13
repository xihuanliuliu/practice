package com.jd.edi.spring.constants;

/**
 * description:常量类
 * createAuthor:songxl3
 * createDate:2017/5/4
 */
public class Constants {
    public static final int VALID 	= 1; // 有效
    public static final int INVALID = 0; // 无效
    public static final String VALID_CHAR 	= "1"; // 有效
    public static final String UNVALID_CHAR 	= "0"; // 无效

    public static final String MULTIPLEX_NO = "0";//任务不可复用
    public static final String MULTIPLEX_YES = "1";//任务可复用

    public static final String SQL_SOURCE_GITLAB = "0";//gitlab
    public static final String SQL_SOURCE_LOCAL = "1";//本地
    /**
     * 阅读状态
     */
    public static final String READ_STATUS_UNREAD = "0";
    public static final String READ_STATUS_READ = "1";
    /**
     * 发布状态
     */
    public static final String PUBLISH_STATUS_UNPUBLISH = "0";
    public static final String PUBLISH_STATUS_PUBLISHING = "1";
    public static final String PUBLISH_STATUS_EXPIRE = "2";

    public static final String HYPHEN = "-";

    /**
     * 审批单类型
     */
    public static final String APPLY_TYPE_COMPONENT_UPLOAD = "1";

    /**
     * 时间换算毫秒数
     */
    public static final int SECOND_VALUE = 1000;
    public static final int MINUT_VALUE = 60 * 1000;
    public static final int HOUR_VALUE = 60 * 60 * 1000;

    /**
     * 申请类型
     */
    public static final String PROJECT_APPLY = "0";
    public static final String RESOURCES_APPLY = "1";

    /**
     * 申请状态
     */
    public static final String APPROVAL_STATUS_ING = "0";
    public static final String APPROVAL_STATUS_YES = "1";
    public static final String APPROVAL_STATUS_NO = "2";

    /**
     * 用户类型
     */
    public static final String IDENTITY_NT = "0";
    public static final String IDENTITY_OTHER = "1";

    /**
     * 镜像构建方式
     */
    public static final String IMAGE_BUILD_TYPE_LOCAL = "0";
    public static final String IMAGE_BUILD_TYPE_HPS = "1";

    /**
     * 用户登录认证方式
     */
    public static final String AUTHC_MODE_PW = "1";
    public static final String AUTHC_MODE_LDAP = "2";

    /**
     * 项目状态
     */
    public static final String PROJECT_STATUS_OK = "1"; //在用
    public static final String PROJECT_STATUS_NO = "2"; //注销

    /**
     * 项目用户状态
     */
    public static final String PROJECT_USER_STATUS_OK = "1"; //在用
    public static final String PROJECT_USER_STATUS_NO = "2"; //注销
    /**
     * 配置表名
     */
    public static final String TABLE_SERVICEINFO = "t_service_info";
    public static final String TABLE_SYSTEMINFO = "t_system_info";
    public static final String TABLE_SYSTEMCONFIG = "t_system_config";

    /**
     * t_system_config表key至
     */
    public static final String ARTIFACTORY_URL = "artifactory.url";
    public static final String ARTIFACTORY_USERNAME_KEY = "artifactory.username";
    public static final String ARTIFACTORY_ADMIN_PASS_KEY = "artifactory.password";
    public static final String ARTIFACTORY_API_KEY = "artifactory.api.key";
    public static final String QUARTZ_CRON_EXPRESSION = "quartz.cron.expression";
    public static final String QUARTZ_GITLAB_PROJECT_AND_USER_AND_BRUNCH_SYNC_CRON_EXPRESSION = "quartz.gitlab.project.user.brunch.cron.expression";
    public static final String QUARTZ_GITLAB_PROJECT_CRON_EXPRESSION_DEFAULT = "59 59 03 * * ?";

    public static final String SONAR_URL_CONFIG_KEY = "sonar.url";

    /**
     * byte大小
     */
    public static final int BYTE_1024 = 1024;

    public static final String JOB_PACKAGE_DIR_NAME = "job_package";

    /**
     * 用来判断返回是否正确
     */
    public static final int RETURN_CODE_300 = 300;

    /**
     * 解决sonar魔法数字
     */
    public static final int NUMBER_0 = 0;

    public static final int NUMBER_3 = 3;

    public static final int NUMBER_TWO = 2;

    public static final int NUMBER_FIVE = 5;

    public static final int NUMBER_FOUR = 4;

    public static final int NUMBER_100 = 100;

    public static final int NUMBER_12 = 12;

    public static final int NUMBER_ONE = 1;

    public static final int NUMBER_THREE = 3;

    public static final int NUMBER_23 = 23;

    public static final int NUMBER_59 = 59;

    public static final int NUMBER_7 = 7;

    public static final int NUMBER_60 = 60;

    public static final int NUMBER_1000 = 1000;

    public static final int NUMBER_24 = 24;

    public static final int NUMBER_512 = 512;

    public static final int NUMBER_500 = 500;
    
    public static final int NUMBER_502 = 502;

    public static final String GITLAB_HOST_KEY_NAME = "gitlab.server.addr";

    public static final String GITLAB_TOKEN_KEY_NAME = "gitlab.root.token";

    public static final String GITLAB_HOST_NEW_KEY_NAME = "gitlab.server.new.addr";

    public static final String NOT_LEADER = "当前登录用户不是项目领导";

    public static final String PROPERTY_FILE_PATH_KEY = "properties.path";

    public static final String SQL_FILE_PATH_KEY = "sqlConfig.path";

    /**
     * aritifactory默认rclass类型
     */
    public static final String ARTIFACTORY_RCLASS_LOCAL = "local";

    /**
     * 用户默认密码
     */
    public static final String DEFAULT_PW = "88888888";

    /**
     * 用户状态：新用户
     */
    public static final String USER_STATUS_NEW = "0";
    /**
     * 用户状态：在用（关联项目）
     */
    public static final String USER_STATUS_IN = "1";
    /**
     * 用户状态：注销
     */
    public static final String USER_STATUS_OUT = "2";
    /**
     * 附件存储方式
     */
    public static final String ATTR_SAVE_DISK 	= "1";//磁盘
   	public static final String ATTR_SAVE_DB 	= "2";//数据库
   	public static final String ATTR_SAVE_SFTP 	= "3";//sftp
    /**
     * 项目用户用户类型
     */
    public static final String PROJECT_USER_USER_TYPE_COMMON 	= "1"; //用户参与
    public static final String PROJECT_USER_USER_TYPE_LEAD 		= "2"; //领导关注
    /**
     * 项目用户关注
     */
    public static final String PROJECT_USER_IS_TOP_NO 	= "0"; //不关注
    public static final String PROJECT_USER_IS_TOP_YES 	= "1"; //关注
   	/**
   	 * 角色编码
   	 */
    public static final String ROLE_CODE_LEADER 	= "leader"; //领导

    /**
     * 项目管理角色ID
     */
    public static final int ROLE_ID_MASTER = 2;

    /**
     * 项目开发角色ID
     */
    public static final int ROLE_ID_DEV = 1;

    /**
     * 项目测试角色ID
     */
    public static final int ROLE_ID_TEST = 3;

    /**
     * 系统管理员角色ID
     */
    public static final int ROLE_ID_ADMIN = 4;

    /**
     * 领导角色ID
     */
    public static final int ROLE_ID_LEADER = 5;

    /**
     * status在用/注销状态(统一)
     */
    public static final String STATUS_OK = "1"; //在用
    public static final String STATUS_NO = "2"; //注销

    public static final int AVAILABLE = 1;
    public static final int UNAVAILABLE = 2;

    /**
     * 特殊符号
     */
    public static final String SYMBOL_COMMA = ",";

    /**
     * 版本库类型
     */
    public static final String REPOSITOCY_TYPE_GIT = "1";
    public static final String REPOSITOCY_TYPE_SVN = "2";

    /**
     * 参数名
     */
    public static final String REQ_PARAM_NAME_JOB_NAME = "jobName";
    public static final String REQ_PARAM_NAME_JENKINS_CONFIG_ID = "jenkinsConfigId";
    public static final String REQ_PARAM_NAME_PROJECT_ID = "projectId";
    public static final String REQ_PARAM_NAME_BUILD_NO = "buildNo";
    public static final String REQ_PARAM_URL = "url";
    public static final String REQ_PARAM_CREDENTIALS_ID = "credentialsId";
    public static final String REQ_PARAM_CRON_EXPRESS = "cronExpress";
    public static final String REQ_PARAM_REPOSITORY_TYPE = "repositoryType";
    public static final String REQ_PARAM_GOALS = "goals";
    public static final String REQ_PARAM_RECIPIENTS = "recipients";
    public static final String RSP_PARAM_NAME_ROLE_ID = "roleId";
    public static final String SESSION_PARAM_USER_ID = "userId";
    /**
     * SQL占位符
     */
    public static final String START_STR = "/*{*/";
    public static final String END_STR = "/*}*/";


    public static final int PERCENTAGE = 100;//百分比

    /**
     * 项目申请接口类型
     */
    public static final String PROKECT_APPLICATION_TYPE_NEW = "1";
    public static final String PROKECT_APPLICATION_TYPE_UPDATE = "2";

    /**
     * job类型
     */
    public static final String JOB_TYPE_UNITTEST 	= "3";
    public static final String JOB_TYPE_SQL 		= "5";

    /**
     * 单元测试类型
     */
    public static final String UNITTEST_TYPE_MAVEN 	= "1";


    public static final String EXEC_PATH = "/veris/exec";

    public static final String EXEC_CONFIG = EXEC_PATH + "/config/";

    public static final String PROPERTY_FILE_PATH = "src/main/resources/application.properties";

    public static final String DDL_SQL_FILE_PATH = "src/main/resources/sql/ddl";

    public static final String DML_SQL_FILE_PATH = "src/main/resources/sql/dml";

    public static final String AICP_HOST_KEY = "aicp.host";

    public static final String FILE_CONFIG_JSON_TEMPLATE = "fileConfigJson.template";

    public static final String SQL_CONFIG_JSON_TEMPLATE = "sqlConfigJson.template";

    public static final String DEFAULT_EXPRESSION_KEY = "expression.default";

    public static final String LDAP_KEY_NAME = "ldap.userName";

    public static final String LDAP_KEY_PASS = "ldap.password";

    public static final String DEF_ROLE_ID = "defRoleId";

    public static final String TEST = "test";

    public static final int PIPELINE_TYPE_ALL= 0;
    public static final int PIPELINE_TYPE_CI = 1;
    public static final int PIPELINE_TYPE_CD = 2;

    public static final int HTTP_RESPONSE_CODE_SUCCESS = 200;

    public static final int MIDDLEWARE_TYPE_MIDD = 0;	//中间件
    public static final int MIDDLEWARE_TYPE_DB = 1;		//数据库
    public static final int ACCOUNT_USE_FOR_ROOT = 0;      //root
    public static final int ACCOUNT_USE_FOR_MIDD = 1;       //中间件安装
    public static final int ACCOUNT_USE_FOR_DB = 2;         //数据库安装
    public static final int ACCOUNT_USE_FOR_DEPLOY = 3;      //应用部署
    public static final int ACCOUNT_USE_FOR_OTHER = 4;      //其它

    public static final int ENV_STATUS_DEPLOY = 3;    //环境搭建中
    public static final int ENV_STATUS_FAILED = 4;    //环境搭建失败
    public static final int ENV_STATUS_SUCCESS = 5;    //环境搭建成功

    /**
     * 中间件搭建状态
     */
    public static final int ENV_MIDDLEWARE_STATUS_MIDD_BUILDING 	= 1;    //中间件搭建中
    public static final int ENV_MIDDLEWARE_STATUS_MIDD_FAILD 		= 2;    //中间件搭建失败
    public static final int ENV_MIDDLEWARE_STATUS_SCRIPT_BUILDING 	= 3;    //脚本执行中
    public static final int ENV_MIDDLEWARE_STATUS_SCRIPT_FAILD 		= 4;    //脚本执行失败
    public static final int ENV_MIDDLEWARE_STATUS_SUCCESS 			= 5;    //搭建成功

    public static final int TEMPLATE_TYPE_ENV_BUILD 	= 1;  //模板类型-环境搭建
    public static final int TEMPLATE_TYPE_APP_DEPLOY 	= 2;  //模板类型-应用部署

    public static final int TEMPLATE_MODULE_TYPE_MIDD 	= 0;  //模板模块类型-中间件
    public static final int TEMPLATE_MODULE_TYPE_DB 	= 1;  	//模板模块类型-数据库

    public static final String SSO_URL = "sso.url";
    public static final String JDBC_URl = "jdbc.url";
    public static final String JDBC_USERNAME = "jdbc.username";
    public static final String JDBC_PAWD = "jdbc.password";

    /**
     * 项目jenkins配置状态
     */
    public static final String PROJECT_JENKINS_STATUS_ING   = "0"; //在用
    public static final String PROJECT_JENKINS_STATUS_NO  = "1"; //注销

    public static final String DEFAULT_GIT_TOKEN_ID = "git_root_token";

    /** 应用部署：包来源类型 **/
    public static final int APP_DEPLOY_PACKAGE_SOURCE_TYPE_LOCAL 	= 1;  	//包来源类型：本地上传
    public static final int APP_DEPLOY_PACKAGE_SOURCE_TYPE_JENKINS 	= 2;  	//包来源类型：JENKINS环境
    /** 应用部署配置：类型 **/
    public static final int APP_DEPLOY_CFG_TYPE_SFTP 			= 1;	//上传应用包
    public static final int APP_DEPLOY_CFG_TYPE_STOP 			= 2;	//停止服务
    public static final int APP_DEPLOY_CFG_TYPE_UPGRADE 		= 3;	//更新应用包
    public static final int APP_DEPLOY_CFG_TYPE_START 			= 4;	//启动服务
    public static final int APP_DEPLOY_CFG_TYPE_MOVE_PACKAGE 	= 5;	//移动应用包

    public static final int DEF_PROJECT_ID 	= 0;  	//默认系统项目ID，用于记录的系统默认数据

    /**
     * host_pool used状态
     */
    public static final int HOST_POOL_UNUSED  	= 0; //未使用
    public static final int HOST_POOL_USED  	= 1; //已使用

    public static final int SSH_DEF_PORT  		= 22; //默认SSH端口号

    public static final String NEWLINE_RN = "\r\n";	//换行符
    /**
     * sftp配置
     */
    public static final int SFTP_CFG_TYPE_DEF  	= 1; //默认

    /**
     * 文件上传模式 1-本地；2-sftp
     */
    public static final int UPLOAD_MODE_LOCAL  	= 1; //本地
    public static final int UPLOAD_MODE_SFTP  	= 2; //sftp

    public static final String UPLOAD_SFTP_STEP_NAME_PREFIX = "sftp";//文件上传sftp的步骤名前缀

    /**
     * 服务升级状态
     */
    public static final int HPS_CLUSTER_UPGRADE_STATUS_UPGRADING = 1;//升级中
    public static final int HPS_CLUSTER_UPGRADE_STATUS_UPGRADE_FAILED = 2;//升级失败
    public static final int HPS_CLUSTER_UPGRADE_STATUS_ROLLBACKED = 3;//已恢复
    public static final int HPS_CLUSTER_UPGRADE_STATUS_UPGRADED = 4;//升级完成
    public static final int HPS_CLUSTER_UPGRADE_STATUS_ROLLLBACK_FAILED = 5;//恢复失败
    public static final int HPS_CLUSTER_UPGRADE_STATUS_CONFIRM_FAILED = 6;//发布失败
    public static final int HPS_CLUSTER_UPGRADE_STATUS_ROLLBACKING = 7;//恢复中
    public static final int HPS_CLUSTER_UPGRADE_STATUS_CONFIRMING = 8;//发布中
    public static final int HPS_CLUSTER_UPGRADE_STATUS_CHECKING = 9;//健康检查中

    /**
     * 主机组件类型
     */
    public static final int COMPONENT_TYPE_ETCD 			= 1; //etcd
    public static final int COMPONENT_TYPE_KUBE_MAMSTER 	= 2; //kube-mamster
    public static final int COMPONENT_TYPE_KUBE_NODE 		= 3; //kube-node
    public static final int COMPONENT_TYPE_ELASTIC_SEARCH 	= 4; //ElasticSearch
    /**
     * 组件状态
     */
    public static final int COMPONENT_STATUS_UNINSTALL 			= 0; //未安装
    public static final int COMPONENT_STATUS_INSTALL_CUCCESS 	= 1; //安装成功
    public static final int COMPONENT_STATUS_INSTALL_FAILD 		= 2; //安装失败
    public static final int COMPONENT_STATUS_START_SUCCESS 		= 3; //启动成功
    public static final int COMPONENT_STATUS_START_FAILD 		= 4; //启动失败
    public static final int COMPONENT_STATUS_STOP_SUCCESS 		= 5; //停止成功
    public static final int COMPONENT_STATUS_STOP_FAILD 		= 6; //停止失败
    /**
     * 组件动作
     */
    public static final int COMPONENT_OPER_TYPE_INSTALL 	= 1; //安装
    public static final int COMPONENT_OPER_TYPE_START 		= 2; //启动
    public static final int COMPONENT_OPER_TYPE_STOP 		= 3; //停止
    /**
     * 主机初始化状态
     */
    public static final int HOST_INIT_STATUS_NEW 		= 1; //新建
    public static final int HOST_INIT_STATUS_INITING 	= 2; //初始化中
    public static final int HOST_INIT_STATUS_SUCCESS 	= 3; //初始化成功
    public static final int HOST_INIT_STATUS_FAILD 		= 4; //初始化失败

    /**
     * job来源类型
     */
    public static final String SOURCE_TYPE_CICD = "1";//流水线创建的job
    public static final String SOURCE_TYPE_RELEASE = "2";//发布管理创建的job

    /**
     * dmp需求状态
     */
    public static final String DEMAND_STATUS_DELETE = "已删除";


    /**
     * 发布版本类型
     */
    public static final int RELEASE_LARGE_VERSION= 1;//大版本
    public static final int RELEASE_ITERATIVE_VERSION = 2; //迭代版本
    public static final int RELEASE_PATCH_VERSION = 3;//补丁版本

    private Constants() {}
}
