package com.kaishengit.mapper;

import com.kaishengit.pojo.Node;

import java.util.List;

public interface NodeMapper {

    void batchSave(List<Node> nodeList);

    List<Node> findByIds(List<Integer> idList);

}
