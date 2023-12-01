package com.mysql.smart.util;

public enum ErrorCode {

    ACCOUNT_ALREADY_EXIST(101, "用户名已存在"),
    ACCOUNT_PWD_NOT_EXIST(102, "用户名或密码不存在"),
    TOKEN_ERROR(103, "Toekn不合法"),
    NO_PERMISSION(701, "无访问权限"),
    SESSION_TIME_OUT(901, "会话超时"),
    NO_LOGIN(902, "未登录"),
    ;

    private String code;

    private String msg;

    ErrorCode(int code, String msg) {
        this.code = Integer.toString(code);
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
