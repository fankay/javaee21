package com.kaishengit.test;

import com.kaishengit.mapper.TopicMapper;
import com.kaishengit.pojo.Topic;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicMapperTestCase {

    private Logger logger = LoggerFactory.getLogger(TopicMapperTestCase.class);

    @Test
    public void testFindById() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        TopicMapper topicMapper = sqlSession.getMapper(TopicMapper.class);
        Topic topic = topicMapper.findById(1);

        logger.debug("{}",topic);
        // ${topic.user.username} ${topic.user.avatar}
        logger.debug("UserName:{} UserAavatr:{}",topic.getUser().getUsername(),topic.getUser().getAvatar());
        logger.debug("NodeName:{}",topic.getNode().getNodename());

        sqlSession.close();
    }
}
