package org.geektimes.projects.user.util;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.domain.UserReqDTO;

/**
 * Desc: 数据转换工具类
 * User: 刘浪
 * Date: 2021-03-09 14:37
 */
public class BeanConvertUtils {

    /**
     * UserDTO->User
     * @param userReqDTO
     * @return
     */
    public static User userDTO2User(UserReqDTO userReqDTO) {
        User user = new User();
        user.setEmail(userReqDTO.getEmail());
        user.setPassword(MD5Util.MD5EncodeUtf8(userReqDTO.getPassword()));
        user.setName(userReqDTO.getName());
        user.setPhoneNumber(userReqDTO.getPhoneNumber());
        return user;
    }
}
