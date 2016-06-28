package com.kaishengit.test;

import com.google.common.collect.Lists;
import com.kaishengit.mapper.NodeMapper;
import com.kaishengit.pojo.Node;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class NodeMapperTestCase {

    private Logger logger = LoggerFactory.getLogger(NodeMapperTestCase.class);

    @Test
    public void testBatchSave() {

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);

        List<Node> nodeList = Lists.newArrayList();
        nodeList.add(new Node("Node1"));
        nodeList.add(new Node("Node2"));
        nodeList.add(new Node("Node3"));
        nodeList.add(new Node("Node4"));

        nodeMapper.batchSave(nodeList);

        sqlSession.commit();
        sqlSession.close();


    }

    @Test
    public void testFindByIds() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);

        List<Integer> idList = Lists.newArrayList(1,2,3);
        List<Node> nodeList  = nodeMapper.findByIds(idList);

        for(Node node : nodeList) {
            logger.debug("{}",node);
        }

        sqlSession.close();


    }



}
