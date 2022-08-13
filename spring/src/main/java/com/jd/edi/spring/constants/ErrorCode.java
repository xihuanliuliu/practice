package com.jd.edi.spring.constants;

/**
 * description:
 * createAuthor:songxl3
 * createDate:2017/5/4
 */
public class ErrorCode {

    /**成功*/
    public static final int SUCCESS = 0;
    /**默认失败代码*/
    public static final int ERROR_DEF = -1;
    public static final String ERROR_DEF_MESSAGE = "系统异常";

    // ----------------------------------------------系统异常------------------------------------------------//

    /** 系统内部错误 */
    public static final int UNKNOWN_INTERNAL_ERROR = 10001;
    /** 数据库操作错误 */
    public static final int DB_ERROR = 10002;
    /** 输入参数为空 */
    public static final int NULL_INPUT = 10003;
    public static final String NULL_INPUT_MESSSGE = "参数校验为空";
    /** 非法请求，参数格式不合法 */
    public static final int ILLEGAL_REQUEST = 10004;
    /** 字符串编码错误 */
    public static final int CHARACTER_ENCODE = 10005;
    /** 字符串解码错误 */
    public static final int CHARACTER_DENCODE = 10006;
    /** JSON错误 */
    public static final int JSON_ERROR = 10007;
    /** 输出参数为空 */
    public static final int NULL_OUTPUT = 10008;
    /** 其他错误 */
    public static final int OTHER_ERROR = 10009;
    /** 验证码错误 */
    public static final int CHECK_CODE_ERROR = 10010;
    /** Base64编码错误 */
    public static final int BASE64_ENCODED_ERROR = 10011;
    /** Base64解码错误 */
    public static final int BASE64_DECODED_ERROR = 10012;
    /** 请求参数格式错误 */
    public static final int VALIDATOR_PARAM_ERROR = 10014;
    /** 找不到文件 */
    public static final int FILE_NOT_FOUND = 10015;
    /** IO错误 */
    public static final int IO_ERROR = 10016;
    /** HTTP错误 */
    public static final int HTTP_ERROR = 10017;
    /** 找不到记录 */
    public static final int NOT_FOUNT_RECORD = 10018;
    /** 外部系统接口出错 */
    public static final int THIRD_PARTY_INTERFACE_ERROR = 10019;

    // -----------------------------------------------业务异常-----------------------------------------------//
    // 项目申请审批相关错误 20000-20099
    /** 项目编码已存在 */
    public static final int PROJECT_NO_REPEAT = 20000;
    /** 项目中文名称已存在 */
    public static final int PROJECT_CN_NAME_REPEAT = 20001;
    /** 项目英文名称已存在 */
    public static final int PROJECT_EN_NAME_REPEAT = 20002;
    /**项目默认仓库不存在*/
    public static final int PROJECT_DEFAULT_REPOSITORY_NOT_EXIST  = 20003;

    public static final int PROJECT_GITLAB_GROUP_NOT_EXIST  = 20004;

    // 用户相关错误20100-20199
    /** 用户错误：注册错误(包括验证用户输入参数错误) */
    public static final int USER_REGISTER_ERROR = 20100;
    /** 用户错误：用户不存在 */
    public static final int USER_INVALID_USER = 20101;
    /** 用户错误：用户已存在 */
    public static final int USER_EXISTED = 20102;
    /** 用户错误：密码错误 */
    public static final int USER_INVALID_PASSWORD = 20103;
    /** 用户错误：验证码错误 */
    public static final int USER_INVALID_VERIFY_CODE = 20104;
    /** 用户错误：问题错误 */
    public static final int USER_INVALID_QUESTION = 20105;
    /** 用户错误：问题的答案错误 */
    public static final int USER_INVALID_ANSWER = 20106;
    /** 用户错误：用户没有登录 */
    public static final int USER_NO_LOGIN = 20107;
    /** 用户错误：用户已被锁定 */
    public static final int USER_LOCKED = 20108;
    /** 用户错误：用户需要实名认证 */
    public static final int USER_NEED_REALAUTH = 20109;
    /** 用户错误：用户需要EMAIL激活 */
    public static final int USER_NEED_EMAILACTIVED = 20110;
    /** 用户错误：用户选择的问题错误 */
    public static final int USER_QUESTION = 20111;
    /** 用户错误：用户用户回答的问题错误 */
    public static final int USER_ANSWER = 20113;
    /** 用户错误：用户需要手机激活 */
    public static final int USER_NEED_MOBILEACTIVED = 20115;
    /** 用户错误：验证码为null */
    public static final int USER_INVALID_CODE_NULL = 20116;
    /** 用户错误：邮箱错误 */
    public static final int USER_INVALID_EMAIL = 20117;
    /** 用户错误：session过期 */
    public static final int USER_SESSION_EXPIRE = 20118;
    /** 权限不够 */
    public static final int USER_PERMISSION_FORBIDDEN = 20119;
    /** 审核状态错误 */
    public static final int AUDIT_TYPE_ERROR = 20120;

    /** 删除失败，申请单已审批或者申请单不存在 */
    public static final int DELETE_APPLICATION_ERROR = 20121;

    /** 申请单已近审批 */
    public static final int APPLICATION_AUDIT = 20122;

    /** 项目成员已经添加 */
    public static final int PROJECT_USER_EXIST = 20123;

    /** 删除项目成员失败 */
    public static final int DELETE_PROJECT_USER_ERROR = 20124;

    // 邮件发送相关错误定义20200-20299
    /** 密码错误 */
    public static final int SEND_MAIL_INVALID_PASS = 20200;
    /** 初始化错误 */
    public static final int SEND_MAIL_INIT_ERROR = 20201;
    /** 邮件发送错误 */
    public static final int SEND_MAIL_SEND_ERROR = 20202;
    /** 服务器错误 */
    public static final int SEND_MAIL_INVALID_HOST = 20201;
    /** 发送者错误 */
    public static final int SEND_MAIL_INVALID_SENDER = 20204;
    /** 接收者错误 */
    public static final int SEND_MAIL_INVALID_RECEIVER = 20205;

    // 短消息发送相关错误定义20300-20399
    /** 发送的短信内容过长 */
    public static final int SEND_CONTENT_TOO_LONG_ERROR = 20300;
    /** 短消息发送错误 */
    public static final int SEND_SMS_IO_ERROR = 20301;
    /** 短消息网关返回错误 */
    public static final int SEND_SMS_SEND_ERROR = 20302;
    /** （单次）发送短信息次数达到上限 */
    public static final int SEND_SMS_TIMES_OUT = 20303;
    /** 发送的手机号码错误 */
    public static final int SEND_MOBILE_ERROR = 20304;

    public static final int JENKINS_VALIDATION_ERROR = 29999;
    public static final int JOB_EXISTS = 30000;
    public static final int JOB_NOT_EXISTS = 30001;

    // 调用外部接口错误定义20400-20499
    /**调用artifactory处理失败*/
    public static final int ARTIFACTORY_HTTP_ERROR = 20400;

    public static final int LDAP_HTTP_ERROR = 20401;

    public static final String USER_AUTHC_FAILED = "用户认证失败！";

    public static final int JENKINS_CONFIG_NAME_EXISTS = 40000;
    public static final int JENKINS_CONFIG_NOT_EXISTS = 40001;
    public static final int JENKINS_SERVER_CREATION_ERROR = 40002;
    public static final int JENKINS_SERVER_START_ERROR = 40003;
    public static final int JENKINS_SERVER_DELETE_ERROR = 40004;
    public static final int JENKINS_SERVER_STOP_ERROR = 40004;
    public static final int JENKINS_BUILD_ERROR = 50000;
    public static final int JENKINS_BUILD_NOT_EXISTS = 50001;
    public static final int JENKINS_BUILD_NOT_BUILDING = 50002;
    public static final int JENKINS_JOB_IS_RELATED = 50003;
    public static final int JENKINS_JOB_POM_FILE_NOT_FOUND = 50004;
    public static final int JENKINS_JOB_GET_PACKAGE_NAME_ERROR = 50005;

    /**
     * gitlab处理异常
     */
    public static final int GITLAB_UPDATE_PASSWORD_ERROR = 60001; // 更新gitlab密码错误
    public static final int GITLAB_GET_USER_ERROR = 60002; // 获取gitlab用户密码错误
    public static final int GITLAB_CREATE_BRANCH_FAIL = 60003; // 创建分支失败
    public static final int GITLAB_BRANCH_NAME_FAIL = 60004; // 分支名称错误

    public static final String ERROR_COLLECTION_MESSAGE = "70001";//仓库正在采集中
    
    private ErrorCode() {
    }
}
