package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select * from t_user limit ${start},${size}")
    List<User> findByPage(@Param("start") String start,@Param("size") String pageSize);

    List<User> findByQueryParam(Map<String,Object> queryParam);

    User findByParams(@Param("username") String username,@Param("pwd") String password);

    User findByMap(Map<String,Object> param);

    @Select("select * from t_user where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "createtime",property = "createtime"),
            @Result(column = "loginip",property = "loginip"),
            @Result(column = "logintime",property = "logintime"),
            @Result(column = "state",property = "state"),
            @Result(property = "tagList",javaType = List.class,column = "id",many = @Many(
                    select = "com.kaishengit.mapper.TagMapper.findByUserId"
            ))
    })
    User findById(Integer id);

    @Insert("INSERT INTO t_user(username, password, email, avatar, createtime, loginip, logintime, state) \n" +
            "VALUES (#{username},#{password},#{email},#{avatar},#{createtime},#{loginip},#{logintime},#{state})")
    void save(User user);

    @Update("UPDATE t_user\n" +
            "         set\n" +
            "            password = #{password},\n" +
            "             email = #{email},\n" +
            "             avatar=#{avatar},\n" +
            "             loginip=#{loginip},\n" +
            "             logintime=#{logintime},\n" +
            "             state=#{state}\n" +
            "        where id = #{id}")
    void update(User user);

    @Delete("delete from t_user where id = #{id}")
    void del(Integer id);

    List<User> findAll();




}
