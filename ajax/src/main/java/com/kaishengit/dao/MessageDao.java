package com.kaishengit.dao;

import com.kaishengit.entity.Message;
import com.kaishengit.util.DbHelp;
import com.kaishengit.util.cache.EhCacheUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MessageDao {

    private Logger logger = LoggerFactory.getLogger(MessageDao.class);

    public Message findById(Integer id) {
        Message message = (Message) EhCacheUtil.get("message:" + id);

        if(message == null) {
            String sql = "select * from t_message where id = ?";
            message = DbHelp.query(sql, new BeanHandler<>(Message.class), id);
            EhCacheUtil.set("message:"+id,message);
        } else {
            logger.debug("load message from cache");
        }

        return message;
    }



    public List<Message> findAll() {

        List<Message> messageList = (List<Message>) EhCacheUtil.get("messageList");
        if(messageList == null) {

            String sql = "select * from t_message order by id desc"; // 10
            messageList = DbHelp.query(sql, new BeanListHandler<>(Message.class));
            EhCacheUtil.set("messageList",messageList);
        }
        return messageList;
    }

    public void save(Message message) {
        String sql = "insert into t_message(message,author) values(?,?)";
        DbHelp.update(sql,message.getMessage(),message.getAuthor());
        EhCacheUtil.remove("messageList");
    }





    public List<Message> findGtMaxId(String maxId) {
        String sql = "select * from t_message where id > ? order by id desc";
        return DbHelp.query(sql,new BeanListHandler<>(Message.class),maxId);
    }
}
