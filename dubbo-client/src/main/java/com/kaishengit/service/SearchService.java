package com.kaishengit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private MovieService movieService;

    public void showAllMovie() {
        List<String> names = movieService.findAll();
        for(String name : names) {
            System.out.println(name);
        }
    }

}
