package com.kaishengit.dao;

import com.kaishengit.pojo.LoginLog;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoginLogImpl implements LoginLogDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(LoginLog loginLog) {
        String sql = "insert into t_login_log(ip,userid) values(?,?)";
        jdbcTemplate.update(sql,loginLog.getIp(),loginLog.getUserid());
    }
}
