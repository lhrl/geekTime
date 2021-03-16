package org.geektimes.projects.user.jmx;

/**
 * Desc: Student定义的MBean对象 必须以MBean结尾
 * User: 刘浪
 * Date: 2021-03-16 22:13
 */
public interface StudentMBean {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String password);

    String toString();
}
