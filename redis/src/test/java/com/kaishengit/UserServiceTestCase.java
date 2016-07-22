package com.kaishengit;

import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testFind() {
        User user = userService.findUserById(102);
        System.out.println(user);
    }

    @Test
    public void testSave() {
        User user = new User(102,"张三",899.678F);
        userService.saveUser(user);
    }

}
