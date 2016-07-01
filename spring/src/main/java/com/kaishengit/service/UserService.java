package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserService {

    //@Autowired
    //@Inject //JSR 330
    @Resource //JSR 250
    private UserDao userDao;

    public void sayHi() {
        userDao.save();
    }
}
