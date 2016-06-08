package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserDaoTestCase {

    private UserDao userDao = new UserDao();

    @Test
    public void testSave() {
        User user = new User("刘丽丽","000","日本");
        userDao.save(user);
    }

    @Test
    public void testDel() {
        userDao.del(5);
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(2);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testFindAll() {
        List<User> userList = userDao.findAll();
        Assert.assertEquals(userList.size(),3);
    }

}
