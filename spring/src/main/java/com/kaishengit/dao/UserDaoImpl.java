package com.kaishengit.dao;

import com.kaishengit.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Named
public class UserDaoImpl implements UserDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        String sql = "insert into t_user(username,password,address) values(?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getAddress());
    }

    @Override
    public void del(Integer id) {
        String sql = "delete from t_user where id = ?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void update(User user) {
        String sql = "update t_user set username = ?,password = ?,address = ? where id = ?";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getAddress(),user.getId());
    }

    @Override
    public User findById(Integer id) {
        String sql = "select * from t_user where id = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setAddress(resultSet.getString("address"));
                return user;
            }
        },id);
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from t_user";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User findByUserName(String username) {
        String sql = "select * from t_user where username = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username);
    }

    @Override
    public Long count() {
        String sql = "select count(*) from t_user";
        return jdbcTemplate.queryForObject(sql,new SingleColumnRowMapper<Long>());
    }
}
