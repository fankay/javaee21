package com.kaishengit.test;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import org.junit.Assert;
import org.junit.Test;

public class UserServiceTestCase {

    private UserService userService = new UserService();

    @Test
    public void testLogin() {
        User user = userService.login("Jack","111111");
        Assert.assertNotNull(user);
    }
}
