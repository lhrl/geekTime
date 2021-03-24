package org.geektimes.projects.user.web.controller;

import org.eclipse.microprofile.config.Config;
import org.geektimes.configuration.microprofile.config.source.servlet.MyConfigApplicationInitializer;
import org.geektimes.projects.user.domain.UserReqDTO;
import org.geektimes.projects.user.enums.ErrorCodeEnum;
import org.geektimes.projects.user.exception.UserBizException;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.util.ValidatorUtil;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.logging.Logger;

/**
 * User controller
 */
@Path("/user")
public class UserController implements PageController {

    private Logger logger = Logger.getLogger(UserController.class.getName());

    @Resource(name = "bean/userService")
    private UserService userService;


    /**
     * 跳转至注册页面
     * @param request  HTTP 请求
     * @param response HTTP 相应
     * @return
     */
    @GET
    @Path("/preRegister")
    public String preRegister(HttpServletRequest request, HttpServletResponse response) {
        return "register-form.jsp";
    }

    /**
     * 用户注册
     *
     * @param request
     * @param response
     * @return
     */
    @POST
    @Path("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        //1.数据转换
        UserReqDTO userReqDTO = convert(request);
        logger.info("register request params " + userReqDTO);
        try {
            //2.参数校验
            ValidatorUtil.validateObject(userReqDTO);
            //3.注册
            userService.register(userReqDTO);
            return "register-success.jsp";
        } catch (UserBizException e) {
            logger.warning("register meet fail,cause" + e.getMessage());
            setPageValue(request, userReqDTO, e.getMessage());
            //注册失败在当前页面显示错误描述信息
            return "register-form.jsp";
        } catch (Throwable e) {
            logger.severe("register meet error,cause" + e.getMessage());
            setPageValue(request, userReqDTO, ErrorCodeEnum.ERROR_99999.getDesc());
            //注册失败在当前页面显示错误描述信息
            return "register-form.jsp";
        }
    }

    /**
     * 跳转至登录页面
     * @param request
     * @param response
     * @return
     */
    @GET
    @Path("/preLogin")
    public String preLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login-form.jsp";
    }


    /**
     * 用户列表
     * @param request
     * @param response
     * @return
     */
    @GET
    @Path("/list")
    public String list(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("userList", userService.getAll());
        return "user-list.jsp";
    }

    /**
     * 获取配置列表
     * @param request
     * @param response
     * @return
     */
    @GET
    @Path("/config")
    public String config(HttpServletRequest request, HttpServletResponse response) {
        ServletContext servletContext = request.getServletContext();
        Config config = (Config) servletContext.getAttribute("config");
        config.getPropertyNames().forEach(logger::info);
        String value = config.getValue("user.name", String.class);
        request.setAttribute("systemUserName", value);
        return "user-config.jsp";
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }

    private UserReqDTO convert(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        UserReqDTO userReqDTO = new UserReqDTO();
        userReqDTO.setName(name);
        userReqDTO.setEmail(email);
        userReqDTO.setPhoneNumber(phoneNumber);
        userReqDTO.setPassword(password);
        return userReqDTO;
    }

    private void setPageValue(HttpServletRequest request, UserReqDTO userReqDTO, String message) {
        request.setAttribute("message", message);
        request.setAttribute("registerName", userReqDTO.getName());
        request.setAttribute("registerEmail", userReqDTO.getEmail());
        request.setAttribute("registerPhoneNumber", userReqDTO.getPhoneNumber());
    }
}
