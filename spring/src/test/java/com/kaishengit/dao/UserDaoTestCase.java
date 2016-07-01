package com.kaishengit.dao;


import com.kaishengit.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserDaoTestCase {

    @Inject
    private UserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Spring");
        user.setPassword("123123");
        user.setAddress("LA");

        userDao.save(user);
    }

    @Test
    public void testFindById() {
        User user = userDao.findById(2);
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void testFindByUserName() {
        User user = userDao.findByUserName("spring");
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindAll() {
        List<User> userList = userDao.findAll();

        Assert.assertEquals(userList.size(),6);

        for(User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testCount() {
        Long count = userDao.count();
        Assert.assertEquals(count.intValue(),6);
    }
}
