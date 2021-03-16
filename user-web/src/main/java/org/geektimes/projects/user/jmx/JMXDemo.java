package org.geektimes.projects.user.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

/**
 * Desc: JMX测试，用于管理及监测java对象
 * User: 刘浪
 * Date: 2021-03-16 22:17
 */
public class JMXDemo {

    public static void main(String[] args) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.lhrl.jmx:type=Student");
        Student student = new Student();
        mBeanServer.registerMBean(student, objectName);
        while (true) {
            TimeUnit.SECONDS.sleep(2);
            System.out.println(student);
        }
    }

}
