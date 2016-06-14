package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class UserDao {

    public void save(User user) {
        String sql = "insert into t_user(username,password,address) values(?,?,?)";
        DbHelp.update(sql,user.getUsername(),user.getPassword(),user.getAddress());
    }

    public void del(Integer id) {
        String sql = "delete from t_user where id = ?";
        DbHelp.update(sql,id);
    }

    public User findById(Integer id) {
        String sql = "select * from t_user where id = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),id);
    }

    public User findByUsername(String username) {
        String sql = "select * from t_user where username = ?";
        return DbHelp.query(sql,new BeanHandler<>(User.class),username);
    }

    public List<User> findAll() {
        String sql = "select * from t_user";
        return DbHelp.query(sql,new BeanListHandler<>(User.class));
    }


}



