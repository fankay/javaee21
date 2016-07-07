package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Book;
import com.kaishengit.service.BookService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/datatable")
public class DataTableController {

    @Inject
    private BookService bookService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("types",bookService.findAllBookType());
        model.addAttribute("pubs",bookService.findAllPublisher());
        return "datatables/list";
    }

    @RequestMapping(value = "/data.json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start"); //当前页偏移量
        String length = request.getParameter("length"); //每页显示多少条数据
        String bookname = request.getParameter("bookname");
        String typeid = request.getParameter("typeid");
        String pubid = request.getParameter("pubid");

        //String keyword = request.getParameter("search[value]"); //搜索框中的值
        //keyword = Strings.toUTF8(keyword);
        bookname = Strings.toUTF8(bookname);

        String sortColumnIndex = request.getParameter("order[0][column]"); //获取排序列的索引
        String sortColumnName = request.getParameter("columns["+sortColumnIndex+"][name]"); //根据排序列的索引获取列的名字
        String sortType = request.getParameter("order[0][dir]");//排序方式 asc | desc

        Map<String,Object> param = Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        //param.put("keyword",keyword);
        param.put("bookname",bookname);
        param.put("type",typeid);
        param.put("pub",pubid);
        param.put("sortColumn",sortColumnName);
        param.put("sortType",sortType);


        List<Book> bookList = bookService.findByDataTables(param);

        Map<String,Object> result = Maps.newHashMap();
        result.put("draw",draw);
        result.put("recordsTotal",bookService.count());
        result.put("recordsFiltered",bookService.countByParam(param));
        result.put("data",bookList);
        return result;
    }


    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String saveBook(Book book) {
        bookService.saveBook(book);
        return "success";
    }

    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    @ResponseBody
    public String delBook(@PathVariable Integer id) {
        bookService.delBook(id);
        return "success";
    }

    @RequestMapping(value = "/{id:\\d+}.json",method = RequestMethod.GET)
    @ResponseBody
    public Book showBook(@PathVariable Integer id) {
        return bookService.findBookById(id);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public String editBook(Book book) {
        bookService.updateBook(book);
        return "success";
    }
}
