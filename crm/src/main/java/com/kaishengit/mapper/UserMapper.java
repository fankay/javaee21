package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

public interface UserMapper {

    User findByUsername(String username);
}
