package com.kaishengit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String list() {
        return "users/list";
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId) {
        logger.debug("ID: {}",userId);
        return "users/show";
    }

    @RequestMapping(value = "/{userId:\\d+}/photos/catalog/{catalogId:\\d+}",method = RequestMethod.GET)
    public String showUserPhoto(@PathVariable Integer userId,
                                @PathVariable Integer catalogId) {
        logger.debug("UserID: {} CatalogID: {}",userId,catalogId);
        return "users/photos";
    }


}
