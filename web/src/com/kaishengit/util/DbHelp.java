package com.kaishengit.util;


import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class DbHelp {

    private static Logger logger = LoggerFactory.getLogger(DbHelp.class);

    public static void update(String sql,Object... params) {
        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
        try {
            queryRunner.update(sql,params);
            logger.debug("SQL: {}",sql);
        } catch (SQLException e) {
            logger.error("执行{}异常",sql);
            throw new DataAccessException("执行:" + sql + "异常",e);
        }
    }

    public static <T> T query(String sql, ResultSetHandler<T> handler,Object... params) {
        QueryRunner queryRunner = new QueryRunner(ConnectionManager.getDataSource());
        try {
            T result = queryRunner.query(sql,handler,params);
            logger.debug("SQL: {}",sql);
            return result;
        } catch (SQLException e) {
            logger.error("执行{}异常",sql);
            throw new DataAccessException("执行:" + sql + "异常",e);
        }
    }

}
