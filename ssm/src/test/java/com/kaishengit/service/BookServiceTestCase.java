package com.kaishengit.service;

import com.kaishengit.pojo.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookServiceTestCase {


    @Inject
    private BookService bookService;

    @Test
    public void testFindAllBook() {
        List<Book> bookList = bookService.findAllBook();
        Assert.assertEquals(bookList.size(),27);
    }

    @Test
    public void testSaveBook() {
        Book book = new Book();
        book.setBookauthor("李维斯");
        book.setBooknum(10);
        book.setBookprice(67.5F);
        book.setPubid(1);
        book.setTypeid(2);
        book.setBookname("SSM项目实战");

        bookService.saveBook(book);
    }

    @Test
    public void testFindBookById() {
        Book book = bookService.findBookById(33);
        Assert.assertNotNull(book);
    }

    @Test
    public void testUpdateBook() {
        Book book = bookService.findBookById(33);
        book.setBookprice(100F);
        bookService.updateBook(book);
    }

    @Test
    public void testDelBook() {
        bookService.delBook(33);
    }


}
