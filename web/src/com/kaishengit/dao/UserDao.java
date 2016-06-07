package com.kaishengit.dao;

public class UserDao {

    public Long count() {
        //
        return 11L;
    }

    public void save(int i) {
        if(i == 1) {
            throw new RuntimeException();
        }
    }

    public Object findById(Integer id) {
        if(id.equals(1)) {
            return new Object();
        }
        return null;
    }
}
