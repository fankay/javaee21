package com.kaishengit.util;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {


        dataSource.setDriverClassName(Config.get("jdbc.driver"));
        dataSource.setUrl(Config.get("jdbc.url"));
        dataSource.setUsername(Config.get("jdbc.username"));
        dataSource.setPassword(Config.get("jdbc.password"));

        dataSource.setInitialSize(Integer.parseInt(Config.get("jdbc.initsize","5")));
        dataSource.setMaxTotal(Integer.parseInt(Config.get("jdbc.maxsize","20")));
        dataSource.setMaxWaitMillis(Integer.parseInt(Config.get("jdbc.maxwait","5000")));
        dataSource.setMaxIdle(Integer.parseInt(Config.get("jdbc.maxidle","10")));
        dataSource.setMinIdle(Integer.parseInt(Config.get("jdbc.minidle","5")));
    }

    public static DataSource getDataSource() {
        return dataSource;
    }


    public static Connection getConnection() throws DataAccessException {
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            throw new DataAccessException("获取数据库连接异常",e);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DataAccessException("数据库连接关闭异常",e);
        }
    }


}
