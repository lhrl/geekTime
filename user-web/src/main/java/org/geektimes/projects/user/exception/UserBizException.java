package org.geektimes.projects.user.exception;

import org.geektimes.projects.user.enums.ErrorCodeEnum;

/**
 * Desc: 用户系统自定义异常
 * User: 刘浪
 * Date: 2021-03-09 16:27
 */
public class UserBizException extends RuntimeException {

    private String code;

    public UserBizException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getDesc());
        this.code = errorCodeEnum.getCode();
    }

    public UserBizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
