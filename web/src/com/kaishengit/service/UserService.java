package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class UserService {

    private final String SALT = "QQQKJHKUYTUY^%&^%&^%^#%$#)*)*&*%^#%";
    private Logger logger = LoggerFactory.getLogger(UserService.class);


    private UserDao userDao = new UserDao();

    /**
     * 用户登录
     * @param username 账号
     * @param password 密码
     * @return 如果登录成功返回User对象，登录失败返回null
     */
    public User login(final String username,String password) {
        final User user = userDao.findByUsername(username);
        //password = DigestUtils.md5Hex(password + SALT);

        if(user != null && user.getPassword().equals(password)) {
            logger.debug("{}登录系统",username);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    //登录成功后，给用户发邮件
                    EmailUtil.sendHtmlEmail(user.getAddress(),"账号登录提示","你的账号"+username+"在"+ DateTime.now().toString("yyyy-MM-dd HH:mm:ss")+"登录了系统");
                }
            });

            thread.start();

            return user;
        }
        return null;
    }


}
