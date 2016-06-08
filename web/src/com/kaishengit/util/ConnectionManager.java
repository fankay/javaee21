package com.kaishengit.util;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        Properties prop = new Properties();
        //读取propreties文件

        try {
            prop.load(ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            throw new RuntimeException("读取config.properties文件异常",e);
        }


        dataSource.setDriverClassName(prop.getProperty("jdbc.driver"));
        dataSource.setUrl(prop.getProperty("jdbc.url"));
        dataSource.setUsername(prop.getProperty("jdbc.username"));
        dataSource.setPassword(prop.getProperty("jdbc.password"));

        dataSource.setInitialSize(Integer.parseInt(prop.getProperty("jdbc.initsize","5")));
        dataSource.setMaxTotal(Integer.parseInt(prop.getProperty("jdbc.maxsize","20")));
        dataSource.setMaxWaitMillis(Integer.parseInt(prop.getProperty("jdbc.maxwait","5000")));
        dataSource.setMaxIdle(Integer.parseInt(prop.getProperty("jdbc.maxidle","10")));
        dataSource.setMinIdle(Integer.parseInt(prop.getProperty("jdbc.minidle","5")));
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
