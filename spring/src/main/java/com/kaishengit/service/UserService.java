package com.kaishengit.service;

import com.kaishengit.dao.UserDao;

public class UserService {

    private UserDao userDao ;

    public void setUserDaoX(UserDao userDao) {
        this.userDao = userDao;
    }
    /*public UserService(UserDao userDao) {
        this.userDao = userDao;
    }*/


    public void sayHi() {
        userDao.save();
    }
}
