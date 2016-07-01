package com.kaishengit.service;

import com.kaishengit.mapper.LoginLogMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.LoginLog;
import com.kaishengit.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class UserService {

    @Inject
    private UserMapper userMapper;
    @Inject
    private LoginLogMapper loginLogMapper;

    public void save(User user) {
        userMapper.save(user);
    }

    public User findUserById(Integer id) {
        return userMapper.findById(id);
    }

    public List<LoginLog> findLoginLogByUserId(Integer userId) {
        return loginLogMapper.findByUserId(userId);
    }



}
