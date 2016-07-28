package com.kaishengit.controller;

import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import com.kaishengit.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Inject
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model) {
        List<Book> bookList = bookService.findAllBook();
        model.addAttribute("bookList",bookList);
        return "book/list";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newBook(Model model) {
        List<BookType> bookTypeList = bookService.findAllBookType();
        List<Publisher> publisherList = bookService.findAllPublisher();

        model.addAttribute("bookTypeList",bookTypeList);
        model.addAttribute("publisherList",publisherList);
        return "book/new";
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String saveBook(Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/book";
    }

    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String delBook(@PathVariable Integer id) {
        bookService.delBook(id);
        return "redirect:/book";
    }

    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.GET)
    public String editBook(@PathVariable Integer id,Model model) {
        Book book = bookService.findBookById(id);
        List<BookType> bookTypeList = bookService.findAllBookType();
        List<Publisher> publisherList = bookService.findAllPublisher();

        model.addAttribute("bookTypeList",bookTypeList);
        model.addAttribute("publisherList",publisherList);
        model.addAttribute("book",book);
        return "book/edit";
    }

    @RequestMapping(value = "/{id:\\d+}/edit",method = RequestMethod.POST)
    public String editBook(Book book) {
        bookService.saveBook(book);
        return "redirect:/book";
    }

}
