package com.kaishengit.dao;

//import org.springframework.context.annotation.Lazy;
//import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named
//@Scope("prototype")
//@Lazy(true)
public class UserDaoImpl2 implements UserDao {


    @Override
    public Integer save() {
        System.out.println("user add....");
        return 100;
    }


}
