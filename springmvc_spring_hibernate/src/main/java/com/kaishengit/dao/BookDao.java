package com.kaishengit.dao;

import com.kaishengit.pojo.Book;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class BookDao {

    @Inject
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(Book book) {
        getSession().saveOrUpdate(book);
    }

    public void del(Book book) {
        getSession().delete(book);
    }

    public Book findById(Integer id) {
        return (Book) getSession().get(Book.class,id);
    }

    public void del(Integer id) {
        getSession().delete(findById(id));
    }

    public List<Book> findAll() {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.addOrder(Order.desc("id"));
        return criteria.list();
    }




}
