package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import com.kaishengit.service.BookService;
import com.kaishengit.util.Page;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BookController {

    @Inject
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "1") Integer p,
                       @RequestParam(required = false) String bookname,
                       @RequestParam(required = false) Integer type,
                       @RequestParam(required = false) Integer pub,
                        Model model) {

        bookname = Strings.toUTF8(bookname);

        Map<String,Object> param = Maps.newHashMap();
        param.put("bookname", bookname);
        param.put("type",type);
        param.put("pub",pub);

        Page<Book> page = bookService.findBookPage(p,param);

        model.addAttribute("types",bookService.findAllBookType());
        model.addAttribute("pubs",bookService.findAllPublisher());
        model.addAttribute("page",page);

        model.addAttribute("bookname",bookname);
        model.addAttribute("typeid",type);
        model.addAttribute("pubid",pub);
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

    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String delBook(@PathVariable Integer id,RedirectAttributes redirectAttributes) {
        bookService.delBook(id);

        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String editBook(@PathVariable Integer id,Model model) {
        Book book = bookService.findBookById(id);
        if(book == null) {
            throw new NotFoundException();
        }

        model.addAttribute("types",bookService.findAllBookType());
        model.addAttribute("pubs",bookService.findAllPublisher());
        model.addAttribute("book",book);
        return "books/edit";
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.POST)
    public String editBookd(Book book,RedirectAttributes redirectAttributes) {
        bookService.updateBook(book);

        redirectAttributes.addFlashAttribute("message","操作成功");
        return "redirect:/books";
    }

}
