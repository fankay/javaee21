package com.kaishengit.dao;

import javax.inject.Named;

@Named("userDao")
public class UserDaoImpl implements UserDao {

    @Override
    public Integer save() {
        System.out.println("userDao save...");
        if(1==1) {
            //throw new RuntimeException("出大事了....");
        }
        return 10000;
    }
}
