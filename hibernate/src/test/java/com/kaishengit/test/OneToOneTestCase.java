package com.kaishengit.test;

import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Person;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class OneToOneTestCase {

    /*
        最佳实践：
        1. 先存主，再存从
     */

    @Test
    public void testSave() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = new Person();
        person.setName("刘思言");

        Card card = new Card();
        card.setCardname("VIP-110");
        card.setPerson(person);


        session.save(person);
        session.save(card);

        session.getTransaction().commit();
    }

    @Test
    public void testFindPerson() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = (Person) session.get(Person.class,10);
        System.out.println(person.getName());
        System.out.println(person.getCard().getCardname());

        session.getTransaction().commit();
    }

    @Test
    public void testFindCard() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Card card = (Card) session.get(Card.class,9);
        System.out.println(card.getCardname());
        System.out.println(card.getPerson().getName());

        session.getTransaction().commit();
    }

    @Test
    public void testDeleteCard() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Card card = (Card) session.get(Card.class,9);
        session.delete(card);

        session.getTransaction().commit();
    }


    @Test
    public void testDeletePerson() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Person person = (Person) session.get(Person.class,8);
        session.delete(person);

        session.getTransaction().commit();
    }
}
