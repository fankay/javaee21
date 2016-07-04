package com.kaishengit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestfulController {

    @RequestMapping(value = "/validate/login",method = RequestMethod.GET)
    public String isLogin() {
        return "success";
    }

}
