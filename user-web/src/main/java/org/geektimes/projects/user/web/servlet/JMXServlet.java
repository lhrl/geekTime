package org.geektimes.projects.user.web.servlet;

import org.geektimes.projects.user.jmx.Student;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.lang.management.ManagementFactory;

/**
 * Desc: JMXServlet
 * User: 刘浪
 * Date: 2021-03-17 00:06
 */
public class JMXServlet extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
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
}
