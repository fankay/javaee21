package com.kaishengit.service;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.entity.Movie;
import com.kaishengit.util.Page;

import java.util.List;

public class MovieService {

    private MovieDao movieDao = new MovieDao();


    public List<Movie> findAllMovie() {
        return movieDao.findAll();
    }

    public Page<Movie> findMovieByPageNo(int pageNo) {
        int totalSize = movieDao.count().intValue(); //获取数据库中的总记录数量

        Page<Movie> page = new Page<>(pageNo,10,totalSize);

        List<Movie> movieList = movieDao.findByPage(page.getStart(),10);
        page.setItems(movieList);
        return page;
        /*int totalSize = movieDao.count().intValue(); //获取数据库中的总记录数量
        int size = 10; //每页显示数据的数量
        int totalPageSize = totalSize / size; //总页数
        if(totalSize % size != 0) {
            totalPageSize++;
        }

        if(pageNo > totalPageSize) {
            pageNo = totalPageSize;
        }

        int start = (pageNo - 1) * size; //当前页显示数据的起始行数

        return movieDao.findByPage(start,size);*/
    }


}
