package org.geektimes.projects.user.web.listener;

import org.geektimes.projects.user.jmx.Student;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;

/**
 * Desc: JMX监听器
 * User: 刘浪
 * Date: 2021-03-24 19:08
 */
public class JMXListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //注册JMX能够管理的MBean
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName objectName = new ObjectName("com.lhrl.jmx:type=Student");
            Student student = new Student();
            mBeanServer.registerMBean(student, objectName);
        } catch (Throwable e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
