package com.kaishengit.test;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Message;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MessageDaoTestCase {

    private MessageDao messageDao = new MessageDao();


    @Test
    public void testFindById() {
        Message message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);
        message = messageDao.findById(1);
        Assert.assertNotNull(message);
    }

    @Test
    public void testFindAll() {
        List<Message> messageList = messageDao.findAll(); // from DB
        messageList = messageDao.findAll(); // from Cache

        Message message = new Message();
        message.setAuthor("James");
        message.setMessage("Hello");

        messageDao.save(message); //insert removeCache

        messageList = messageDao.findAll(); // from DB
        messageList = messageDao.findAll(); // from Cache

        Assert.assertEquals(26,messageList.size());
    }





    @Test
    public void testOne() {
        System.out.println(System.getProperty("java.io.tmpdir")); //当前用户的临时文件夹
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.vendor"));
        System.out.println(System.getProperty("os.name"));
    }


    @Test
    public void testEhCache() {
        CacheManager cacheManager = new CacheManager();
        Ehcache ehcache = cacheManager.getEhcache("myCache");

        //向缓存中存值
        Element element = new Element("user:1","java");
        ehcache.put(element);

        //将cache中的值删除
        ehcache.remove("user:1");


        //从缓存中取值
        Element outElement = ehcache.get("user:1");
        String value = null;
        if(outElement != null) {
            value = (String) outElement.getObjectValue();
            System.out.println(value);
        }
        Assert.assertNotNull(value);


    }

}
