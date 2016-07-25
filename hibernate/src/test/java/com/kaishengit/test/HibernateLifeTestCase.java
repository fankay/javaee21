package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class HibernateLifeTestCase {

    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("rose");
        user.setPassword("123123");

        session.persist(user); //save JPA
        System.out.println(user.getId());

        /*session.save(user);
        System.out.println(user.getId());*/
//        Integer id = (Integer) session.save(user);
//        System.out.println(id);

        session.getTransaction().commit();
    }

    @Test
    public void testFindByGet() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,39);

        session.getTransaction().commit();

        Assert.assertNull(user);
    }

    @Test
    public void testFindByLoad() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.load(User.class,39);
        //System.out.println(user.getUsername());

        //System.out.println(user == null);
        session.getTransaction().commit();
    }

    @Test
    public void testUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,41);


        session.getTransaction().commit();

        user.setUsername("James");

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        session2.update(user);

        session2.getTransaction().commit();
    }

    @Test
    public void testSaveOrUpdate() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = new User();
        user.setUsername("李斯");
        user.setPassword("334455");

        session.saveOrUpdate(user);

        session.getTransaction().commit();

        user.setPassword("0987654321");

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        session2.saveOrUpdate(user);

        session2.getTransaction().commit();

    }

    @Test
    public void testMerge() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        /*User user = new User();
        user.setUsername("李斯");
        user.setPassword("334455");

        session.merge(user);*/
        User user = (User) session.get(User.class,45);


        session.getTransaction().commit();

        //-----------------------------------------

        user.setPassword("0987654321");

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        session2.merge(user);

        session2.getTransaction().commit();

    }

    @Test
    public void testDelete() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,47);
        //session.delete(user);

        session.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        session2.delete(user);

        session2.getTransaction().commit();
    }

    @Test
    public void testClear() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        User user = (User) session.get(User.class,48);
        //session.clear();
        user.setPassword("321321");
        session.flush(); //将对象的变化立即同步到数据库中
        //。。。。

        session.getTransaction().commit();

    }



}
