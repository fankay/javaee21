package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User findByUsername(String username);

    void updateUser(User user);

    List<User> findByParam(Map<String, Object> params);

    Long count();

    Long countByParam(Map<String, Object> params);

    void save(User user);

    User findById(Integer id);

    List<User> findAll();

}
