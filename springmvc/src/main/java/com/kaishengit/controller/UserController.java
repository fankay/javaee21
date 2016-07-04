package com.kaishengit.controller;

import com.kaishengit.pojo.User;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model,
                       @RequestParam(required = false,defaultValue = "false") String vip,
                       Integer age,
                       @RequestHeader(value = "User-Agent",required = false,defaultValue = "other") String UserAgent,
                       @CookieValue(value = "JSESSIONID",required = false,defaultValue = "") String sessionId) {

        logger.debug("VIP:{}  age:{}",vip,age);
        logger.debug("User-Agent:{}",UserAgent);
        logger.debug("CookieID:{}",sessionId);

        model.addAttribute("userName","Spring 4.x");
        return "users/list";
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public ModelAndView showUser(@PathVariable("id") Integer userId) {
        logger.debug("ID: {}",userId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users/show");
        modelAndView.addObject("userId",userId);

        return modelAndView;
    }

    @RequestMapping(value = "/{userId:\\d+}/photos/catalog/{catalogId:\\d+}",method = RequestMethod.GET)
    public String showUserPhoto(@PathVariable Integer userId,
                                @PathVariable Integer catalogId) {
        logger.debug("UserID: {} CatalogID: {}",userId,catalogId);
        return "users/photos";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newUser() {
        return "users/new";
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String saveUser(User user,String comment) {
        logger.debug("username:{}  address:{} Age:{} comment:{}",
                user.getUsername(),user.getAddress(),user.getAge(),comment);

        return "redirect:/users";
    }

    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String del(@PathVariable Integer id) {
        logger.debug("del object {}",id);
        return "redirect:/users";
    }

    //------------------Ajax

    @RequestMapping(value = "/checkuser.json",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkUser(@PathVariable Integer id) {
        logger.debug("ID:{}",id);
        return "success";
    }


    @RequestMapping(value = "/{id}.json",
                method = RequestMethod.GET,
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User showUserJson() {
        User user = new User();
        user.setAddress("中国河南");
        user.setUsername("张三丰");
        user.setAge(88);
        return user;
    }

    //----------------- Native Servlet

    @RequestMapping(value="/native")
    public String nativeHttpServlet(HttpServletRequest request,
                                    HttpServletResponse response,
                                    HttpSession session) {
        ServletContext context = session.getServletContext();

        session.setAttribute("curr_user","tom");
        return "home";
    }

    //--------------------fileUpload

    @RequestMapping(value = "/avatar/update",method = RequestMethod.GET)
    public String updateAavatr() {

        return "users/upload";
    }

    @RequestMapping(value = "/avatar/update",method = RequestMethod.POST)
    public String updateAavatr(String photoname, MultipartFile file) {

        //判断用户是否选择了文件，需要使用file.isEmpty()来判断，而不能用是否为Null来判断

        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        logger.debug("PhotoName:{}" , photoname);
        logger.debug("File Content Type:{}",file.getContentType());
        logger.debug("File Size:{}",file.getSize());
        logger.debug("File isEmpty:{}",file.isEmpty());
        logger.debug("File Name:{}",file.getOriginalFilename());
        logger.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        try {
            IOUtils.copy(file.getInputStream(),new FileOutputStream("D:/"+file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/users";
    }



}
