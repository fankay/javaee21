package com.kaishengit.mapper;

import com.kaishengit.pojo.Node;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NodeMapper {

    void batchSave(List<Node> nodeList);

    List<Node> findByIds(List<Integer> idList);

    @Select("select * from t_node where id = #{id}")
    Node findById(Integer id);

    void del(Integer id);

    List<Node> findAll();
}
