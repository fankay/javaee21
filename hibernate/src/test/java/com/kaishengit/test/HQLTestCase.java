package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class HQLTestCase {

    @Test
    public void testFindAll() {
        // HQL 全部是Java中的对象，根数据库无关

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User";
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        for (User user : userList) {
            System.out.println(user);
        }


        session.getTransaction().commit();
    }

    @Test
    public void testFindByWhere() {
        // HQL 全部是Java中的对象，根数据库无关

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User as u where u.password = :password and u.username = :name"; //引用占位符

        Query query = session.createQuery(hql);
        //query.setString(0,"123123");
        //query.setParameter(0,"123123");
        //query.setString("password","123123");
        query.setParameter("password","123123");
        query.setParameter("name","rose");

        List<User> userList = query.list();

        for (User user : userList) {
            System.out.println(user);
        }


        session.getTransaction().commit();
    }

    @Test
    public void testFindUnique() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User where password = :pwd";
        Query query = session.createQuery(hql);
        query.setParameter("pwd","321321");

        User user = (User) query.uniqueResult(); //确保结果集只有一条记录
        System.out.println(user);

        session.getTransaction().commit();
    }

    @Test
    public void findByCloumn() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select id,username,password from User";
        Query query = session.createQuery(hql);

        List<Object[]> result = query.list();

        for (Object[] objects : result) {
            System.out.println(objects[0] + " -> " + objects[1]);
        }

        /*List<String> result = query.list();

        for (String name : result) {
            System.out.println(name);
        }*/

        session.getTransaction().commit();
    }


    @Test
    public void testCount() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "select count(*),max(id) from User";
        Query query = session.createQuery(hql);
        Object[] objects = (Object[]) query.uniqueResult();
        System.out.println("Count:" + objects[0]);
        System.out.println("Max:" + objects[1]);


        session.getTransaction().commit();
    }

    @Test
    public void testPage() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        String hql = "from User order by id desc";
        Query query = session.createQuery(hql);

        query.setFirstResult(3);
        query.setMaxResults(3); // limit 0,3 = limit 3

        List<User> userList = query.list();
        for(User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }



}
