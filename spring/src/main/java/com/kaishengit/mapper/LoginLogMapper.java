package com.kaishengit.mapper;

import com.kaishengit.pojo.LoginLog;

import java.util.List;

public interface LoginLogMapper {

    List<LoginLog> findByUserId(Integer userId);

}
