package org.geektimes.projects.user.web.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionInitializerListener implements ServletContextListener {

    public static final String databaseURL = "jdbc:derby:db/user-platform;create=true";

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

            System.out.println("数据库链接监听器初始化...");
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/UserPlatformDB");
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//            Connection connection = DriverManager.getConnection(databaseURL);
            dataSource.getConnection().createStatement().execute(CREATE_USERS_TABLE_DDL_SQL);
            System.out.println("数据库链接监听器开始初始化成功...");
        } catch (Throwable e) {
            //do nothing
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
