package com.kaishengit.dao;

import com.kaishengit.pojo.LoginLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class LoginLogDaoTestCase {

    @Inject
    private LoginLogDao loginLogDao;

    @Test
    public void testSave() {
        LoginLog loginLog = new LoginLog("8.7.6.5",2);
        loginLogDao.save(loginLog);
    }

}
