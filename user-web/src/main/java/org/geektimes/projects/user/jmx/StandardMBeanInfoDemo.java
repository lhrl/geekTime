package org.geektimes.projects.user.jmx;

import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

/**
 * Desc: StandardMBean标准MBean
 * User: 刘浪
 * Date: 2021-03-16 22:48
 */
public class StandardMBeanInfoDemo {
    public static void main(String[] args) throws NotCompliantMBeanException {
        StandardMBean standardMBean = new StandardMBean(new Student(), StudentMBean.class);

        System.out.println(standardMBean.getMBeanInfo());

    }
}
