package org.geektimes.projects.user.web.listener;

import org.geektimes.projects.user.context.ComponentContext;
import org.geektimes.projects.user.sql.DataSourceManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * ${@link ComponentContext}初始化器
 * Spring ContextLoaderListener
 */
public class ComponentContextLoaderInitializerListener implements ServletContextListener {


    public static final String DATASOURCE_NAME = "jdbc/UserPlatformDB";

    public static final String CREATE_USERS_TABLE_DDL_SQL = "CREATE TABLE users(" +
            "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "name VARCHAR(16) NOT NULL, " +
            "password VARCHAR(64) NOT NULL, " +
            "email VARCHAR(64) NOT NULL, " +
            "phoneNumber VARCHAR(64) NOT NULL" +
            ")";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            ServletContext servletContext = sce.getServletContext();
            ComponentContext componentContext = new ComponentContext();
            componentContext.init(servletContext);

            System.out.println("数据库链接监听器初始化...");
            DataSource dataSource = componentContext.getComponent(DATASOURCE_NAME);
            dataSource.getConnection().createStatement().execute(CREATE_USERS_TABLE_DDL_SQL);
            System.out.println("数据库链接监听器开始初始化成功...");
        } catch (Throwable e) {
            //do nothing
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ComponentContext.getInstance().destroy();
    }
}
