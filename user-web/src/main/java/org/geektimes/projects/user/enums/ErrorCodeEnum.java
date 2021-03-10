package org.geektimes.projects.user.enums;

/**
 * Desc: 错误码枚举
 * User: 刘浪
 * Date: 2021-03-09 16:29
 */
public enum  ErrorCodeEnum {

    ERROR_99999("API99999", "系统繁忙,请稍后再试"),

    PARAMS_ILLEGAL("PARAMS_ILLEGAL","参数异常"),

    USER_REGISTERED("USER_REGISTERED", "该用户名已被注册,换一个试试"),

    ;

    ErrorCodeEnum(final String code, final String desc) {
        this.code = code;
        this.desc = desc;
    }
    private String code;

    private String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }}
