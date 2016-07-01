package com.kaishengit.service;

import com.kaishengit.pojo.LoginLog;
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
public class UserServiceTestCase {

    @Inject
    private UserService userService;


    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Spring+MyBaits");
        user.setPassword("123123");
        user.setAddress("zzz");

        userService.save(user);

    }
    @Test
    public void testFindById() {
        User user = userService.findUserById(2);
        Assert.assertNotNull(user);
    }

    @Test
    public void testFindLoginLogByUserId() {
        List<LoginLog> loginLogList = userService.findLoginLogByUserId(8);
        for(LoginLog loginLog : loginLogList) {
            System.out.println(loginLog + " " + loginLog.getNiceCreateTime());
        }
    }
}
