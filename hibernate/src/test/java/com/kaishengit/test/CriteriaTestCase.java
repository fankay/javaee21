package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.Test;

import java.util.List;

public class CriteriaTestCase {

    @Test
    public void testFindAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        List<User> userList = criteria.list();

        for(User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void testFindByWhere() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        //criteria.add(Restrictions.eq("password","123123"));
        //criteria.add(Restrictions.eq("username","rose"));

        //LIKE !!! %o%
        criteria.add(Restrictions.like("username","o", MatchMode.ANYWHERE));


        //criteria.add(Restrictions.in("username",new Object[]{"rose","Jack"}));

        //!!!!! or
        /*Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("username","rose"));
        disjunction.add(Restrictions.eq("username","Jack"));

        criteria.add(disjunction);*/


        //!!!!!!!
        //criteria.add(Restrictions.or(Restrictions.eq("username","rose"),
        //                             Restrictions.eq("username","Jack")));

        List<User> userList = criteria.list();

        for(User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void testUnique() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);

        criteria.add(Restrictions.eq("password","321321"));
        User user = (User) criteria.uniqueResult();
        System.out.println(user);

        session.getTransaction().commit();
    }

    @Test
    public void testByPage() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.addOrder(Order.asc("id")); //排序

        criteria.setFirstResult(0); //分页
        criteria.setMaxResults(5);

        List<User> userList = criteria.list();

        for(User user : userList) {
            System.out.println(user);
        }

        session.getTransaction().commit();
    }

    @Test
    public void testCount() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        //criteria.setProjection(Projections.rowCount()); //count(*) count(column)
        //criteria.setProjection(Projections.count("id"));

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.rowCount());
        projectionList.add(Projections.max("id"));

        criteria.setProjection(projectionList);


        Object[] objects = (Object[]) criteria.uniqueResult();
        System.out.println("Count: " + objects[0]);
        System.out.println("Max: " + objects[1]);

        session.getTransaction().commit();
    }

}
