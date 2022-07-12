package com.ai.aif.gitai.dao;


/**
 * description:常量类
 * createAuthor:songxl3
 * createDate:2017/5/4
 */
public class Constants {

    public static final int TYPE_REQ = 0;
    public static final int TYPE_RSP = 1;
    public static final int TYPE_CATALOG = 2;


    public static final Integer TENANT_ROLE = 1;//租户角色
    public static final Integer ABILITY_USER_ROLE = 2;//能力使用者角色
    public static final Integer ABILITY_OWNER  = 1;//能力提供者组织
    public static final Integer ABILITY_USER = 2;//能力使用者
    public static final Integer TENANT_LEVEL_ONE= 1;//一级租户
    public static final Integer TENANT_LEVEL_TWO = 2;//二级租户
    public static final Integer ACTION_UPDATE = 0;
    public static final Integer ACTION_ADD = 1;
    public static final Integer ACTION_REMOVE = -1;

    public static final Integer PASS_BUILD_METHOD_AUTO = 0;//自动开通
    public static final Integer PASS_BUILD_METHOD_MANUAL = 1;//人工

    public static final Integer COMPONENT_TYPE_TEXT = 0; //文本框
    public static final Integer COMPONENT_TYPE_SELECTOR = 1; //下拉框
    public static final Integer COMPONENT_TYPE_RADIO = 2; //单选
    public static final Integer COMPONENT_TYPE_CHECKBOX = 3; //多选
    public static final Integer COMPONENT_TYPE_NUMBER = 4; //数值
    public static final Integer COMPONENT_TYPE_PASSWORD = 5; //密码

    public static final Integer SOURCE_IMPORT = 1;//接口导入
    public static final Integer SOURCE_CREATE = 2;//本地创建

    /*-------------------------状态start-------------------------*/

    /**
     * 修改申请
     */
    public static final Integer STATUS_MODIFYED 	= 2;
    /**
     * 在用
     */
    public static final Integer STATUS_ING 	= 1;
    /**
     * 申请中/可用
     */
    public static final Integer STATUS_APPLY = 0;
    /**
     * 注销
     */
    public static final Integer STATUS_NO 	= -1;
    /*-------------------------状态end-------------------------*/

    /**
     * 必须
     */
    public static final String IS_MUST_Y = "Y";
    /**
     * 非必需
     */
    public static final String IS_MUST_N = "N";

    /*-------------------------审批状态start----------------------*/
    /**
     * 同意
     */
    public static final Integer APPROVAL_AGREE = 0;
    /**
     * 拒绝
     */
    public static final Integer APPROVAL_REFUSE = 1;

    /**
     * 未审批
     */
    public static final Integer APPLY_WAIT = 0;
    /**
     * 审批中
     */
    public static final Integer APPLY_ING = 1;
    /**
     * 审批通过
     */
    public static final Integer APPLY_SUCCESS = 2;
    /**
     * 审批拒绝
     */
    public static final Integer APPLY_REFUSED =3 ;
    /**
     * 申请资源注销
     */
    public static final Integer APPLY_CANCEL = 4;

    /**
     * 审批失败
     */
    public static final Integer APPLY_ERROR = -1;

    /**
     * 驳回修改
     */
    public static final Integer APPLY_MODIFY = 5;

    /*-------------------------审批状态end----------------------*/


    /*-------------------------申请类型start----------------------*/
    /**
     *
     */
    public static final String APPLICATION_TYPE_01 = "01";
    /**
     * 添加成员
     */
    public static final String APPLICATION_TYPE_02 = "02";
    /**
     * 接入注册申请
     */
    public static final String APPLICATION_TYPE_03 = "03";
    /**
     * 产品注销
     */
    public static final String APPLICATION_TYPE_04 = "04";
    /**
     * 云服务定义
     */
    public static final String APPLICATION_TYPE_10 = "10";
    /**
     * API订阅
     */
    public static final String APPLICATION_TYPE_12 = "12";
    /**
     * 产品服务规格订阅
     */
    public static final String APPLICATION_TYPE_13 = "13";
    /**
     * 云服务订购
     */
    public static final String APPLICATION_TYPE_14 = "14";
    /**
     * 租户接入注册
     */
    public static final String APPLICATION_TYPE_15 = "15";

    /**
     * 工单创建
     */
    public static final String APPLICATION_TYPE_16 = "16";

    /*--------------------------申请类型end----------------------*/

    /*--------------------------数字常量start---------------------*/
    public static final int NUMBER_23 = 23;

    public static final int NUMBER_59 = 59;

    public static final int NUMBER_100 = 100;
    /*--------------------------数字常量end---------------------*/

    /**
     * 用来判断返回是否正确
     */
    public static final int RETURN_CODE_300 = 300;

    /**
     * byte大小
     */
    public static final int BYTE_1024 = 1024;

    /*--------------------------API审批状态 start---------------------*/
    public static final int API_APPLICATION_STATUS_NEW_APPLY 	= 1;	//新增申请
    public static final int API_APPLICATION_STATUS_UPDATE_APPLY = 2;	//修改申请
    public static final int API_APPLICATION_STATUS_SUCCESS 		= 3;	//申请通过
    public static final int API_APPLICATION_STATUS_FAILD 		= 4;	//申请失败
    /*--------------------------API审批状态 end---------------------*/

    /*--------------------------API参数类型 start---------------------*/
    public static final int API_PARAM_TYPE_REQUEST 	= 1;	//请求参数
    public static final int API_PARAM_TYPE_RESPONSE = 2;	//响应参数
    /*--------------------------API参数类型 end---------------------*/

    /*--------------------------API错误码类型 start---------------------*/
    public static final int API_SYS_TYPE_COMMON 	= 1;	//通用
    public static final int API_SYS_TYPE_BUSINESS 	= 2;	//业务
    /*--------------------------API参数类型 end---------------------*/

    /*--------------------------租户类别 start---------------------*/
    /**
     * 能力使用者
     */
    public static final int TENANT_TYPE_USE= 2;
    /**
     * 能力提供者
     */
    public static final int TENANT_TYP_PROVIDE= 1;
    /*--------------------------租户类别 end---------------------*/

    public interface RedisCacheExtime{
        int REDIS_SESSION_EXTIME = 60 * 30;//30分钟
    }

    public static final String CURRENTUSER = "currentUser";

    /*--------------------------资源组lock常量 start---------------------*/
    public static final int RESOURCE_GROUP_LOCK_NO 	= 0;	//未绑定
    public static final int RESOURCE_GROUP_LOCK_YES = 1;	//绑定
    /*--------------------------资源组lock常量 end---------------------*/

    /*--------------------------资源组resource_type常量 start---------------------*/
    public static final int RESOURCE_GROUP_RESOURCE_TYPE_PRODUCT= 0;	//产品订阅
    public static final int RESOURCE_GROUP_RESOURCE_TYPE_API 	= 1;	//API订阅
    public static final int RESOURCE_GROUP_RESOURCE_TYPE_CLOUD 	= 2;	//云服务资源
    /*--------------------------资源组resource_type常量 end---------------------*/

    /*-------------------------通知start-------------------------*/

    /**
     * 在用
     */
    public static final String NOTICE_STATUS_ING 	= "0";

    /**
     * 注销
     */
    public static final String NOTICE_STATUS_NO 	= "1";

    /**
     * 已读
     */
    public static final String NOTICE_READ_YES 	= "1";

    /**
     * 未读
     */
    public static final String NOTICE_READ_NO 	= "0";
    /*-------------------------通知end-------------------------*/

    /*--------------------------有效性 start---------------------*/
    public static final int INVALID = 0;	//无效
    public static final int VALID 	= 1;	//有效

    public static final int NO 	= 0;	//否
    public static final int YES = 1;	//是

    public static final String NO_CHAR 	= "0";	//否
    public static final String YES_CHAR = "1";	//是
    /*--------------------------有效性 end---------------------*/

    /*--------------------------问答资源类型 start 1-标签;2-主题;3-评论;4-回复---------------------*/
    public static final int ASK_RESOURCE_TYPE_TAG 		= 1;
    public static final int ASK_RESOURCE_TYPE_TOPIC 	= 2;
    public static final int ASK_RESOURCE_TYPE_COMMENT 	= 3;
    public static final int ASK_RESOURCE_TYPE_REPLY 	= 4;
    /*--------------------------问答资源类型 end---------------------*/
    /*--------------------------问答标签类型 start 1-分类；2-标签---------------------*/
    public static final int ASK_TAG_TYPE_CATEGORY 	= 1;
    public static final int ASK_TAG_TYPE_TAG 		= 2;
    /*--------------------------问答标签类型 start 1-分类；2-标签---------------------*/

    /*--------------------------接口异常信息分类常量 start---------------------*/
    public static final String ITFACE_APPROVAL= "1";	//审批
    public static final String ITFACE_INNER	= "2";	//内部接口
    public static final String ITFACE_OUTER 	= "3";	//外部接口
    /*--------------------------接口异常信息分类常量 end---------------------*/

    /**
     * 公告与解决方案分类常量
     */
    public static final int PROCLAMATION= 1;	//公告
    public static final int SOLUTION_COMMON	= 2;	//通用解决方案
    public static final int SOLUTION_AI= 3;	//大数据与AI解决方案
    public static final int SOLUTION_SECURITY= 4;	//安全解决方案

    /**
     * 服务规格同步常量
     */
    public static final String SERVICEINSTANCE = "service_instance";
    public static final String SERVICEBINDING = "service_binding";
    public static final String SERVICEID = "serviceId";
    public static final String PLANID = "planId";

    /**
     * 特殊符号常量
     */
    public static final String SLASH = "/";
    public static final String COMMA = ",";

    /**
     * 登录锁定常量
     */
    public static final String LOCKACCOUNTPREFIX = "fail_times:";
    public static final int LOCKTIME = 1*60;
    public static final int MAXFAILTIMES = 5;

    /*--------------------------API订阅状态 start---------------------*/
    public static final int API_SUBSCRIBE_STATUS_APPLYING 		= 1;	//订阅申请
    public static final int API_SUBSCRIBE_STATUS_APPLY_CANCEL 	= 2;	//取消申请
    public static final int API_SUBSCRIBE_STATUS_AGREE 			= 3;	//订阅通过
    public static final int API_SUBSCRIBE_STATUS_REJECT 		= 4;	//订阅拒绝
    public static final int API_SUBSCRIBE_STATUS_CANCEL 		= 5;	//注销订阅
    /*--------------------------API订阅状态 end---------------------*/

    /**
     * 审批决策
     */
    public static final String APPROVE_AGREE = "0";
    public static final String APPROVE_REFUSE = "1";

    /*-------------------配置表key-------------------------*/
    public static final String GITLAB_HOST_KEY_NAME = "gitlab.server.addr";
    public static final String GITLAB_TOKEN_KEY_NAME = "gitlab.root.token";

    public static final String TOOL_USER_KEY_NAME = "gitlab.tool.user";
    public static final String TOOL_PWD_KEY_NAME = "gitlab.tool.password";
    public static final String TOOL_USERID_KEY_NAME = "gitlab_tool_userid";
    public static final String TOOL_TOKEN_KEY_NAME = "gitlab.tool.token";

    public static final String OUTBOUND_PYTHON_PATH = "outbound.python.path";

    /**
     * 配置表名
     */
    public static final String TABLE_SERVICEINFO = "t_service_info";
    public static final String TABLE_SYSTEMINFO = "t_system_info";
    public static final String TABLE_SYSTEMCONFIG = "t_system_config";

    /**
     * 解决sonar魔法数字
     */
    public static final int NUMBER_0 = 0;

    public static final int NUMBER_3 = 3;

    public static final int NUMBER_TWO = 2;

    public static final int NUMBER_FIVE = 5;

    public static final int NUMBER_FOUR = 4;

    public static final int NUMBER_12 = 12;

    public static final int NUMBER_ONE = 1;

    public static final int NUMBER_THREE = 3;

    public static final int NUMBER_7 = 7;

    public static final int NUMBER_60 = 60;

    public static final int NUMBER_1000 = 1000;

    public static final int NUMBER_24 = 24;

    public static final int NUMBER_512 = 512;

    public static final int NUMBER_500 = 500;

    /**
     * status在用/注销状态(统一)
     */
    public static final String STATUS_OK = "1"; //在用

    /**
     * 项目用户用户类型
     */
    public static final String PROJECT_USER_USER_TYPE_COMMON 	= "1"; //用户参与
    public static final String PROJECT_USER_USER_TYPE_LEAD 		= "2"; //领导关注

    /**
     * 时间换算毫秒数
     */
    public static final int SECOND_VALUE = 1000;
    public static final int MINUT_VALUE = 60 * 1000;
    public static final int HOUR_VALUE = 60 * 60 * 1000;

    /**
     *gitlab新建用户默认密码
     */
    public static final String GITLAB_USER_PWD="88888888";

    /**
     * gitlab请求方法http常量
     */
    public static final String GITLAB_HTTP_HEAD = "http://";

    /**
     * 合并请求状态
     */
    public static final int MERGE_REQUEST=1;
    public static final int MERGE_SUCCESS=2;
    public static final int MERGE_CANCEL=3;

    /***
     *代码画像查询类型
     */
    public static final int CODE_ALL=1;
    public static final int CODE_DETAIL=2;

    /**
     * 工具链工具类型
     */
    public static final int TOOL_GITLAB = 3;

    /**
     * 如果存在某些冲突且无法合并-您将收到405且错误消息 "分支无法合并"
     * 如果合并请求已被合并或关闭-您将收到406和错误消息 "不允许使用方法"
     * 如果sha传递的参数与源头的HEAD不匹配-您将得到一个409错误消息 "SHA与源分支的HEAD不匹配"
     * 如果您没有接受此合并请求的权限-您将获得一个 401
     * gitlab 发起合并请求返回状态码
     */
    public static final String GITLAB_MERGE_FAILED = "405";
    public static final String GITLAB_MERGE_METHOD_NOTALOW = "406";
    public static final String GITLAB_MERGE_HEADSHA_NOTMATCH = "409";
    public static final String GITLAB_MERGE_NOLEVEL = "401";

    /**
     * 评论模块
     */
    public static final int COMMENT_CODE = 1;//代码画像模块
    public static final int COMMENT_MERGE = 2;//合并请求模块

    /**
     * gitlab用户等级
     */
    public static final int GITLAB_MASTER_ACCESS = 40;

    public static final int GITLAB_DEVELOPER_ACCESS = 30;

    public static final int GITLAB_REPORTER_ACCESS = 20;

    /**
     * 我的申请类型
     */
    public static final String APPLICATION_TYPE_CODE_REPO = "1";//仓库申请

    public static final String APPLICATION_TYPE_CODE_MEMBER = "2";//成员申请

    public static final String APPLICATION_TYPE_CODE_BRANCH = "3";//分支申请

    public static final String APPLICATION_TYPE_CODE_OUTBOUND = "5";//代码出库申请

    public static final String APPLICATION_TYPE_CODE_SHAREREPO = "6";//共享仓库申请

    /**
     * 懒加载数据加载完成
     */
    public static final String LAZY_LOAD_COMPLETE = "200";

    /**
     * 是否为脚本类源码
     */
    public static final String  IS_SCRIPT = "1";
    public static final String  ISNOT_SCRIPT = "2";

    /**
     * 交付方类型
     */
    public static final String CUSTOMER_PRIVATE  ="1";
    public static final String CUSTOMER_PUBLIC = "2";
    /**
     * 邮件发送开关
     */
    public static final String MAIL_SEND_SWITCH_OPEN  ="open";//开
    public static final String MAIL_SEND_SWITCH_CLOSE = "close";//关
    /**
     * 资源类型
     * @author louyue
     *
     */
    public enum resourceTyp {
        PRODUCT_SUBSCRIBE(0), API_SUBSCRIBE(1), SERVICE(2);

        private int value;

        resourceTyp(int idx) {
            this.value = idx;
        }

        public int getValue() {
            return value;
        }
    };

    /**
     * 仓库等级
     */
    public enum ReposrityLevel{
        repo_top_secret("绝密",1),
        repo_secret("机密",2),
        repo_privacy("秘密",3),
        repo_internal("内控",4);

        private String desc;//文字描述
        private Integer code; //对应的代码

        /**
         * 私有构造,防止被外部调用
         * @param desc
         * @return
         */
        ReposrityLevel(String desc,Integer code){
            this.desc=desc;
            this.code=code;
        }
        /**
         * 定义方法,返回描述,跟常规类的定义没区别
         * @return
         */
        public String getDesc(){
            return desc;
        }

        public int getCode(){
            return code;
        }
    }

    /**
     * 资源类型
     * @author louyue
     *
     */
    public enum RepositoryPrivilege {
        top_secret(1), secret(2), privacy(3), internal(4);

        private int level;

        public static RepositoryPrivilege fromRepositoryValue(final int level) throws IllegalArgumentException {
            for (final RepositoryPrivilege repositoryPrivilege : RepositoryPrivilege.values()) {
                if (repositoryPrivilege.level == level) {
                    return repositoryPrivilege;
                }
            }
            throw new IllegalArgumentException("RepositoryPrivilege：no level enum constant with repository value: " + level);
        }

        RepositoryPrivilege(int level) {
            this.level = level;
        }

        public int getValue() {
            return level;
        }
    }

    /*------------FTP用户状态----------------------------*/
    //在用
    public static final String FTP_STATUS_ING 	= "1";
    //可用
    public static final String FTP_STATUS_ENABLE = "0";
    //注销
    public static final String FTP_STATUS_NO = "-1";

    //邮件发送成功响应码
    public static final String MAIL_SEND_SUCCESS = "000000";

    private Constants() {}

}

