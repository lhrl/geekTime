package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * User controller
 */
@Path("/user")
public class UserController implements PageController {


    private UserService userService;

    public UserController() {
        this.userService = ComponentContext.getInstance().getComponent("bean/userService");
    }


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
     * @param request
     * @param response
     * @return
     */
    @POST
    @Path("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        userService.register(user);
        return "register-success.jsp";
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

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }
}
