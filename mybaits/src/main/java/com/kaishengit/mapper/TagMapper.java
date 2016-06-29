package com.kaishengit.mapper;

import com.kaishengit.pojo.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TagMapper {

    @Select("select * from t_tag where userid = #{userId}")
    List<Tag> findByUserId(Integer userId);




}
