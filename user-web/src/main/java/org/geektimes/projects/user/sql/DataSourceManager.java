package org.geektimes.projects.user.sql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Desc: 数据源管理器
 * User: mercylhrl
 * Date: 2021-03-03 14:49
 */
public class DataSourceManager {

    private static Context context;

    static {
        try {
            context = new InitialContext();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        try {
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/UserPlatformDB");
            return dataSource;
        } catch (Throwable e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
