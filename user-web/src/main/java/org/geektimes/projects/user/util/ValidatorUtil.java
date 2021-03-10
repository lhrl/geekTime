package org.geektimes.projects.user.util;

import org.geektimes.projects.user.domain.UserReqDTO;
import org.geektimes.projects.user.enums.ErrorCodeEnum;
import org.geektimes.projects.user.exception.UserBizException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

/**
 * Desc: 参数校验工具
 * User: 刘浪
 * Date: 2021-03-09 16:17
 */
public class ValidatorUtil {

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        ValidatorUtil.validator = factory.getValidator();
    }

    /**
     * 参数校验
     * @param object
     */
    public static void validateObject(Object object) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, Default.class);
        if (constraintViolations.size() > 0) {
            String message = constraintViolations.iterator().next().getMessage();
            throw new UserBizException(ErrorCodeEnum.PARAMS_ILLEGAL.getCode(), message);
        }
    }

    public static void main(String[] args) {
        UserReqDTO userReqDTO = new UserReqDTO();
        userReqDTO.setEmail("mercylhrl@14.com");
        userReqDTO.setName("44");
        userReqDTO.setPassword("42353456");
        userReqDTO.setPhoneNumber("142214825951");
        validateObject(userReqDTO);
    }
}
