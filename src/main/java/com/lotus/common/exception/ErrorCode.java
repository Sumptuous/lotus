package com.lotus.common.exception;

/**
 * @author wyy
 */
public enum ErrorCode {

    COMMON_MISSING_HTTP_REQ_PARAM(100),
    COMMON_SQL_SYNTAX_ERROR(101),
    COMMON_TRANSCATION_ROLLBACK(102),
    COMMON_UNDEFINED_ERROR(103, "系统未定义异常"),

    PLEASE_INPUT_USERNAME(1000, "请输入用户名!"),
    PLEASE_INPUT_PASSWORD(1001, "请输入密码!"),
    PLEASE_INPUT_VERIFICATION_CODE(1002, "请输入验证码!"),
    INCORRECT_PASSWORD(1003, "{test}密码不正确!"),
    INCORRECT_USERNAME(1004, "用户名不正确!"),
    INCORRECT_VERIFICATION_CODE(1005, "验证码不正确!"),
    PASSWORD_NOT_MATCH(1006, "两次输入的密码不匹配!"),
    USERNAME_ALREADY_EXIST(1007, "用户已存在!"),
    USERNAME_NOT_EXIST(1008, "用户不存在!"),
    USERNAME_NOT_VALID(1009, "用户名不符合要求!"),
    PASSWORD_NOT_VALID(1010, "密码不符合要求!");


    private int code;
    private String msg;

    ErrorCode(int code) {
        this.code = code;
    }

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}