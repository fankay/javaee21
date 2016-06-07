package com.kaishengit.test;

import com.kaishengit.dao.UserDao;
import static org.junit.Assert.*;  // 静态导入
import org.junit.Test;

public class MyTestCase {

    private UserDao userDao = new UserDao();

    @Test
    public void testCount() {
        //断言没通过的可能性
        //1. 结果不一致
        //2. 调用方法出现异常
        assertEquals(new Long(11),userDao.count());
    }

    @Test
    public void testFindById() {
        Object obj = userDao.findById(1);
        assertNotNull(obj);

    }

    @Test
    public void testSave() {
        userDao.save(2);
    }

}
