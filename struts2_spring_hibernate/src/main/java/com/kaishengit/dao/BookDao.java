package com.kaishengit.dao;

import com.kaishengit.pojo.Book;
import com.kaishengit.util.Page;
import com.kaishengit.util.SearchParam;
import org.hibernate.Criteria;

import javax.inject.Named;
import java.util.List;

@Named
public class BookDao extends BaseDao<Book,Integer> {

    @Override
    public Page<Book> findByPageNo(Integer pageNo, Integer pageSize, List<SearchParam> searchParamList) {
        Criteria criteria = getSession().createCriteria(Book.class);
        criteria.createAlias("bookType","bt");
        return super.findByPageNo(criteria,pageNo, pageSize, searchParamList);
    }
}
