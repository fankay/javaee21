package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Inject
    private UserService userService;


    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String userList() {

        return "admin/userlist";
    }

    @RequestMapping(value = "/users/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<User> loadUsers(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");
        keyword = Strings.toUTF8(keyword);

        Map<String,Object> params = Maps.newHashMap();
        params.put("keyword",keyword);
        params.put("start",start);
        params.put("length",length);

        List<User> userList = userService.findUserListByParam(params);
        Long count = userService.findtUserCount();
        Long filterCount = userService.findUserCountByParam(params);

        return new DataTablesResult<>(draw,userList,count,filterCount);
    }


}
