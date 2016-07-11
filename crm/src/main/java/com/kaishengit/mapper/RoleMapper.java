package com.kaishengit.mapper;

import com.kaishengit.pojo.Role;

import java.util.List;

public interface RoleMapper {

    Role findById(Integer id);

    List<Role> findAll();

}
