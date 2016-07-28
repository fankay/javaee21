package com.kaishengit.service;

import com.kaishengit.dao.BookDao;
import com.kaishengit.dao.BookTypeDao;
import com.kaishengit.dao.PublisherDao;
import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Transactional
public class BookService {

    @Inject
    private BookDao bookDao;
    @Inject
    private BookTypeDao bookTypeDao;
    @Inject
    private PublisherDao publisherDao;

    public void saveBook(Book book) {
        bookDao.save(book);
    }

    public List<Book> findAllBook() {
        return bookDao.findAll();
    }

    public List<BookType> findAllBookType() {
        return bookTypeDao.findAll();
    }

    public List<Publisher> findAllPublisher() {
        return publisherDao.findAll();
    }

    public void delBook(Integer id) {
        bookDao.del(id);
    }

    public Book findBookById(Integer id) {
        return bookDao.findById(id);
    }
}
