package com.kaishengit.test;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Tag;
import com.kaishengit.pojo.User;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisInterfaceTestCase {

    private Logger logger = LoggerFactory.getLogger(MyBatisInterfaceTestCase.class);


    @Test
    public void testFindByPage() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.findByPage("0","2");

        for(User user : userList) {
            logger.debug("{}",user);
        }


        sqlSession.close();
    }


    @Test
    public void testFindByQueryParam() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //getMapper()方法使用了【动态代理模式】-> 自动产生一个接口的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Object> queryParam = Maps.newHashMap();
        //queryParam.put("username","James");
        //queryParam.put("email","fankai@kaishengit.com");
        queryParam.put("password","123123");

        userMapper.findByQueryParam(queryParam);

        sqlSession.close();

    }


    @Test
    public void testFindByParams() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //getMapper()方法使用了【动态代理模式】-> 自动产生一个接口的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findByParams("James","123123");
        logger.debug("{}",user);

        Assert.assertNotNull(user);

    }


    @Test
    public void testFindByMap() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //getMapper()方法使用了【动态代理模式】-> 自动产生一个接口的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String,Object> param = Maps.newHashMap();
        param.put("username","James");
        param.put("password","123123");

        User user = userMapper.findByMap(param);
        logger.debug("{}",user);

        Assert.assertNotNull(user);

    }


    @Test
    public void testFindById() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //getMapper()方法使用了【动态代理模式】-> 自动产生一个接口的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findById(10);
        logger.debug("{}",user);

        List<Tag> tagList = user.getTagList();
        for(Tag tag : tagList) {
            logger.debug("Tag: {}",tag);
        }

        sqlSession.close();
        Assert.assertNotNull(user);
    }

    @Test
    public void testSave() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("Rose");
        user.setPassword("123123");
        user.setState("正常");
        user.setEmail("rose@google.com");
        user.setCreatetime("2016-06-27 12:23:34");

        userMapper.save(user);

        logger.debug("UserID:{}",user.getId());

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findById(16);
        user.setPassword("00998877");

        userMapper.update(user);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testDel() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.del(16);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testFindAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();

        for(User user : userList) {
            logger.debug("{}",user);
        }

        sqlSession.close();
    }

}
