package org.geektimes.projects.common.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Desc: 自定义加载器接口
 * User: 刘浪
 * Date: 2021-03-23 01:15
 */
public interface MyApplicationInitializer {


    void onStartup(ServletContext servletContext) throws ServletException;

}
