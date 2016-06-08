package com.kaishengit.test;

import com.kaishengit.entity.User;
import com.kaishengit.exception.DataAccessException;
import com.kaishengit.util.ConnectionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DbUtilsTestCase {


    @Test
    public void testConnection() {
        ConnectionManager.getConnection();

    }

    @Test
    public void testSave() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into t_user(username,password,address) values(?,?,?)";
        try {
            queryRunner.update(ConnectionManager.getConnection(),sql,"Jack","123123","USA");
        } catch (SQLException e) {
            throw new DataAccessException();
        }
    }

    @Test
    public void testUpdate() {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "update t_user set username = ? where id = ?";
        Connection connection = ConnectionManager.getConnection();
        try {
            queryRunner.update(connection,sql,"rose",1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Test
    public void testQueryById() {
        String sql = "select * from t_user where id = ?";

        Connection connection = ConnectionManager.getConnection();

        QueryRunner queryRunner = new QueryRunner();
        try {
            User user = queryRunner.query(connection,sql,new BeanHandler<>(User.class),1);
            System.out.println(user);
            Assert.assertNotNull(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }


    }


    @Test
    public void testQueryAll() {
        String sql = "select * from t_user";

        Connection connection = ConnectionManager.getConnection();

        QueryRunner queryRunner = new QueryRunner();
        try {
            List<User> userList = queryRunner.query(connection,sql,new BeanListHandler<>(User.class));

            System.out.println(userList);
            Assert.assertEquals(userList.size(),2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }



    @Test
    public void testQueryByIdToMap() {
        String sql = "select * from t_user where id = ?";

        Connection connection = ConnectionManager.getConnection();

        QueryRunner queryRunner = new QueryRunner();
        try {
            Map<String,Object> result = queryRunner.query(connection,sql,new MapHandler(),1);

            for(Map.Entry<String,Object> entry : result.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }


            Assert.assertNotNull(result);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }


    }


    @Test
    public void testQueryAllToMapList() {
        String sql = "select * from t_user";

        Connection connection = ConnectionManager.getConnection();

        QueryRunner queryRunner = new QueryRunner();
        try {
            List<Map<String,Object>> resultList = queryRunner.query(connection,sql,new MapListHandler());

            for(int i = 0;i < resultList.size();i++) {
                Map<String,Object> result = resultList.get(i);
                for(Map.Entry<String,Object> entry : result.entrySet()) {
                    System.out.println(entry.getKey() + " -> " + entry.getValue());
                }
                System.out.println("---------------------------");
            }

            Assert.assertEquals(resultList.size(),2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }


    @Test
    public void testCount() {
        String sql = "select count(*) from t_user";

        Connection connection = ConnectionManager.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        try {
            Long count = queryRunner.query(connection,sql,new ScalarHandler<Long>());
            Assert.assertEquals(new Long(2),count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }

    }

    @Test
    public void testQueryAllUsername() {
        String sql = "select username from t_user";

        Connection connection = ConnectionManager.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        try {
            List<String> nameList = queryRunner.query(connection,sql,new ColumnListHandler<String>());
            Assert.assertEquals(2,nameList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.closeConnection(connection);
        }

    }












}
