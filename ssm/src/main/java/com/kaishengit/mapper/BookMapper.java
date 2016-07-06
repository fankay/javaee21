package com.kaishengit.mapper;

import com.kaishengit.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookMapper {


    void save(Book book);

    List<Book> findAll();

    void del(Integer id);

    void update(Book book);

    Book findById(Integer id);


    Long count();
    Long countByParam(Map<String,Object> param);

    List<Book> findByPage(@Param("start") Integer start,@Param("size") Integer size);
    List<Book> findByParam(Map<String,Object> param);

    List<Book> findByDataTable(Map<String,Object> param);

    Long countByKeyWord(@Param("keyword") String keyword);

}
