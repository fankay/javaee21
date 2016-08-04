package com.kaishengit.action;

import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import com.kaishengit.service.BookService;
import com.kaishengit.util.Page;
import com.kaishengit.util.SearchParam;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import javax.inject.Inject;
import java.util.List;

@Namespace("/book")
public class BookAction extends BaseAction {

    @Inject
    private BookService bookService;

    private List<Book> bookList;
    private List<BookType> bookTypeList;
    private List<Publisher> publisherList;
    private Book book;
    private Integer id;
    private Integer p;
    private Page<Book> page;



    @Action("list")
    @Override
    public String execute() throws Exception {
        //bookList = bookService.findAllBook();

        if(p == null) {
            p = 1;
        }


        List<SearchParam> searchParams = SearchParam.buiderSearchParam(getHttpServletRequest());

        page = bookService.findByPage(p,searchParams);
        bookTypeList = bookService.findAllBookType();
        publisherList = bookService.findAllPublisher();

        return SUCCESS;
    }

    @Action("new")
    public String toSave() {
        bookTypeList = bookService.findAllBookType();
        publisherList = bookService.findAllPublisher();
        return SUCCESS;
    }

    @Action(value = "save",results = {
            @Result(type = "redirectAction",
                    params = {"actionName","list","namespace","/book"})
    })
    public String save() {
        bookService.saveBook(book);
        return SUCCESS;
    }

    @Action(value = "del",results = {
            @Result(type = "redirectAction",
                    params = {"actionName","list","namespace","/book"})
    })
    public String del() {
        bookService.delBook(id);
        return SUCCESS;
    }

    @Action("edit")
    public String edit() {
        book = bookService.findBookById(id);
        bookTypeList = bookService.findAllBookType();
        publisherList = bookService.findAllPublisher();
        return SUCCESS;
    }

    @Action(value = "update",results = {
            @Result(type = "redirectAction",
                    params = {"actionName","list","namespace","/book"})
    })
    public String update() {
        bookService.saveBook(book);
        return SUCCESS;
    }




    //get set

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<BookType> getBookTypeList() {
        return bookTypeList;
    }

    public void setBookTypeList(List<BookType> bookTypeList) {
        this.bookTypeList = bookTypeList;
    }

    public List<Publisher> getPublisherList() {
        return publisherList;
    }

    public void setPublisherList(List<Publisher> publisherList) {
        this.publisherList = publisherList;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getP() {
        return p;
    }

    public void setP(Integer p) {
        this.p = p;
    }

    public Page<Book> getPage() {
        return page;
    }

    public void setPage(Page<Book> page) {
        this.page = page;
    }
}
