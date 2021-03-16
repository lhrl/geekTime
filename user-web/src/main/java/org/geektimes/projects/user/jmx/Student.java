package org.geektimes.projects.user.jmx;

/**
 * Desc: 学生对象
 * 实现MBean接口
 * User: 刘浪
 * Date: 2021-03-16 22:16
 */
public class Student implements StudentMBean {
    private String name;

    private String password;

    private Long id;
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
