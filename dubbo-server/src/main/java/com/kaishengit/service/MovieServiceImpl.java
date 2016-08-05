package com.kaishengit.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {


    public List<String> findAll() {

        List<String> names = new ArrayList<String>();
        names.add("大话西游");
        names.add("美国队长1");
        names.add("美国队长2");
        names.add("美国队长3");
        return names;
    }
}
