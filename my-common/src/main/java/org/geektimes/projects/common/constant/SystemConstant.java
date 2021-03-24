package org.geektimes.projects.common.constant;

/**
 * Desc: 系统常量
 * User: 刘浪
 * Date: 2021-03-24 17:58
 */
public class SystemConstant {

    public static final String DATASOURCE_NAME = "jdbc/UserPlatformDB";

    public static final String CREATE_USERS_TABLE_DDL_SQL = "CREATE TABLE users(" +
            "id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
            "name VARCHAR(16) NOT NULL, " +
            "password VARCHAR(64) NOT NULL, " +
            "email VARCHAR(64) NOT NULL, " +
            "phoneNumber VARCHAR(64) NOT NULL" +
            ")";
}
