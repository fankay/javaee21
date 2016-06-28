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
    public void testFindById() {
        //一级缓存：在同一个SqlSession中多次查询同一个对象，会触发一级缓存（一级缓存无须配置）
        //二级缓存：在同一个SqlSessionFactory产生的SqlSession对象中多次查询同一个对象，会触发二级缓存
        //二级缓存需要配置后才生效 ：1. 放入缓存中的对象需要是可序列化对象 2.mapper.xml中添加<cache/>

        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        NodeMapper nodeMapper = sqlSession.getMapper(NodeMapper.class);

        Node node = nodeMapper.findById(1);

        logger.debug("{}",node);

        sqlSession.close();

        //------------------------------------------------------------

        SqlSession sqlSession2 = MyBatisUtil.getSqlSession();

        NodeMapper nodeMapper2 = sqlSession2.getMapper(NodeMapper.class);

        Node node2 = nodeMapper2.findById(1);

        logger.debug("{}",node2);

        sqlSession2.close();

    }



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
