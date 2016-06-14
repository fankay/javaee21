package com.kaishengit.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.FileInputStream;

public class CodecTestCase {


    @Test
    public void testMd5() {
        //1.无论明文长度为多少，密文长度总是32位
        //2.不能通过密文推出明文
        //3.相同明文获取的密文是相同的

        //4297f44b13955235245b2497399d7a93
        String password = "111111";
        String salt = "QQQKJHKUYTUY^%&^%&^%^#%$#)*)*&*%^#%"; //盐
        //password = DigestUtils.md5Hex(password+salt);
        password = DigestUtils.md5Hex(password);

        System.out.println(password);
    }

    @Test
    public void testSha() {
        String password = "111111";
        String salt = "QQQKJHKUYTUY^%&^%&^%^#%$#)*)*&*%^#%"; //盐
        password = DigestUtils.sha1Hex(password + salt);
        System.out.println(password);
    }


    @Test
    public void checkFile() throws Exception {
        //f6c52c5459abde402c915481dd291631
        //f6c52c5459abde402c915481dd291631
        //e2177b81d29f474747fc99efdb2daa9e
        FileInputStream inputStream = new FileInputStream("D:/my2.log");
        String md5 = DigestUtils.md5Hex(inputStream);
        System.out.println(md5);
    }
















}
