package com.kaishengit.mapper;

import com.kaishengit.pojo.Node;
import com.kaishengit.pojo.Topic;
import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.*;

@CacheNamespace
public interface TopicMapper {

    @Select("select * from t_topic where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "text",property = "text"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "nodeid",property = "nodeid"),
            @Result(property = "user",javaType = User.class,column = "userid",one = @One(select = "com.kaishengit.mapper.UserMapper.findById")),
            @Result(property = "node",javaType = Node.class,column = "nodeid",one = @One(select = "com.kaishengit.mapper.NodeMapper.findById"))
    })
    @Options(useCache = false)
    Topic findById(Integer id);

}
