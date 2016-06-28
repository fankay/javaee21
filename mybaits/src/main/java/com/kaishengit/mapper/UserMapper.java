package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    List<User> findByPage(@Param("start") String start,@Param("size") String pageSize);

    List<User> findByQueryParam(Map<String,Object> queryParam);

    User findByParams(@Param("username") String username,@Param("pwd") String password);

    User findByMap(Map<String,Object> param);

    User findById(Integer id);

    void save(User user);

    void update(User user);

    void del(Integer id);

    List<User> findAll();




}
