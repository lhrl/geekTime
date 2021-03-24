package org.geektimes.projects.injection.initializer;

import org.geektimes.projects.common.constant.SystemConstant;
import org.geektimes.projects.common.initializer.MyApplicationInitializer;
import org.geektimes.projects.injection.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.util.logging.Logger;

/**
 * Desc: 全局上下文启动器
 * User: 刘浪
 * Date: 2021-03-23 01:14
 */
public class MyComponentContextApplicationInitializer implements MyApplicationInitializer {

    private static Logger logger = Logger.getLogger(MyComponentContextApplicationInitializer.class.getName());

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        logger.info("MyComponentContextApplicationInitializer onStartup......");
        ComponentContext componentContext = new ComponentContext();
        componentContext.init(servletContext);
        try {
            logger.info("数据库链接监听器初始化...");
            DataSource dataSource = componentContext.getComponent(SystemConstant.DATASOURCE_NAME);
            dataSource.getConnection().createStatement().execute(SystemConstant.CREATE_USERS_TABLE_DDL_SQL);
            logger.info("数据库链接监听器开始初始化成功...");
        } catch (Throwable throwable) {
            //do nothing
            logger.info("数据库已完成初始化");
        }
    }
}
