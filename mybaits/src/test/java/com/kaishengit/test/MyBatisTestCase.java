package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MyBatisTestCase {

    private Logger logger = LoggerFactory.getLogger(MyBatisTestCase.class);


    @Test
    public void testLoadXml() {

        try {
            //从classpath中读取mybatis.xml配置文件
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            //根据SqlSessionFactoryBuilder对象构建SqlSessionFactory
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //根据SqlSessionFactory对象创建SqlSession对象
            SqlSession sqlSession = sessionFactory.openSession();

            User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",10);
            logger.debug("{}",user);
            //释放资源
            sqlSession.close();

            Assert.assertNotNull(user);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testSave() {

        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);

            SqlSession sqlSession = sessionFactory.openSession(); //sessionFactory.openSession(true); //默认自动提交事务
            //事务默认【不是】自动提交的，insert update delete

            User user = new User();
            user.setUsername("James");
            user.setPassword("123123");
            user.setState("正常");
            user.setEmail("james@google.com");
            user.setCreatetime("2016-06-27 12:23:34");

            sqlSession.insert("com.kaishengit.mapper.UserMapper.save",user);

            //sqlSession.commit(); //提交事务
            sqlSession.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testUpdate() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findById",13);
        user.setLoginip("8.8.8.8");
        user.setLogintime("2016-06-14 12:45:44");

        sqlSession.update("com.kaishengit.mapper.UserMapper.update",user);

        sqlSession.commit();
        sqlSession.close();


    }

    @Test
    public void testDel() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        sqlSession.delete("com.kaishengit.mapper.UserMapper.del",14);

        sqlSession.commit();
        sqlSession.close();

    }

    @Test
    public void testFindAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        List<User> userList = sqlSession.selectList("com.kaishengit.mapper.UserMapper.findAll");
        for(User user : userList) {
            logger.debug("{}",user);
        }
        sqlSession.close();

        Assert.assertEquals(3,userList.size());
    }
}
