package com.kaishengit.test;

import com.kaishengit.pojo.Task;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.Test;

import java.util.UUID;

public class UuidPrimaryKeyTestCase {

    @Test
    public void testSave() {

        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = new Task();
        task.setTitle("x-100");

        session.save(task);

        session.getTransaction().commit();
    }

    @Test
    public void testUpdate() throws InterruptedException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"8a8082e4562b6de001562b6de5260000");
        task.setTitle("x-103");

        Thread.sleep(10000);


        session.getTransaction().commit();
    }


    @Test
    public void testUpdate2() throws InterruptedException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Task task = (Task) session.get(Task.class,"8a8082e4562b6de001562b6de5260000", LockOptions.UPGRADE);
        task.setTitle("x-108");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Session session2 = HibernateUtil.getSession();
                session2.beginTransaction();
                Task task1 = (Task) session2.get(Task.class,"8a8082e4562b6de001562b6de5260000");
                task1.setTitle("VV-100");
                session2.getTransaction().commit();
            }
        });

        thread.start();

        Thread.sleep(5000);

        session.getTransaction().commit();
    }




    @Test
    public void testFindById() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        //一级缓存
        Task task = (Task) session.get(Task.class,"8a8082e4562a08fa01562a0901df0000");
        //System.out.println(session.contains(task));
        //session.clear(); 持久态 -> 游离态  并清空一级缓存
        //session.evict(task); //将指定对象从一级缓存中清除
        session.getTransaction().commit();
        //-------------------------------------------------

        //将对象从二级缓存中清除
        //Cache cache = HibernateUtil.getSessionFactory().getCache();
        //cache.evictEntityRegion(Task.class);
        //cache.evictAllRegions();

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        Task task2 = (Task) session2.get(Task.class,"8a8082e4562a08fa01562a0901df0000");
        System.out.println(task2.getTitle());

        session2.getTransaction().commit();
    }

}
