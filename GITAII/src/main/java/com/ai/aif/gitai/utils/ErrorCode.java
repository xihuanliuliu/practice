package com.ai.aif.gitai.utils;

public enum ErrorCode {
    DEF_ERROR("-1", "系统异常"),
    NULL_INPUT("1001", "参数为空"),
    LACK_INPUT("1001", "缺少参数"),
    NOT_LOGIN("1003", "未登录系统"),
    DB_ERROR("1004", "数据库异常"),
    FILE_NOT_FOUND("1005","未找到文件"),
    IO_ERROR("1006","读取文件失败"),
    GITLAB_UPDATE_PASSWORD_ERROR("60001","更新gitlab密码错误"),
    GITLAB_GET_USER_ERROR("60002","获取gitlab用户密码错误"),
    GITLAB_CREATE_USER_ERROR("60009","gitlab用户创建失败"),
    GITLAB_CREATE_BRANCH_FAIL("60003","创建分支失败"),
    GITLAB_BRANCH_NAME_FAIL("60004","分支名称错误"),
    GITLAB_CREATE_MR_FAIL("60005","创建合并分支请求失败"),
    GITLAB_ACCEPT_MR_FAIL("60006","合并分支失败"),
    GITLAB_DELETE_TAG_FAIL("60007","标签删除失败"),
    GITLAB_CREATE_TAG_FAIL("60008","标签创建失败"),
    GITLAB_USER_NAME_FAIL("20101","用户不存在"),
    PROJECT_GITLAB_GROUP_NOT_EXIST("20004","项目gitlab组不存在"),
    GITLAB_CREATE_HOOK_FAIL("2001","新增hook失败"),
    GITLAB_UPDATE_HOOK_FAIL("2002","修改hook失败");

    private String code;
    private String msg;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
