package com.kaishengit.controller;

import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import com.kaishengit.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Inject
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Book> bookList = bookService.findAllBook();

        model.addAttribute("bookList",bookList);
        return "books/list";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String saveBook(Model model) {

        List<Publisher> publisherList = bookService.findAllPublisher();
        List<BookType> bookTypeList = bookService.findAllBookType();

        model.addAttribute("pubs",publisherList);
        model.addAttribute("types",bookTypeList);

        return "books/new";
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String saveBook(Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);

        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/books";
    }
}
