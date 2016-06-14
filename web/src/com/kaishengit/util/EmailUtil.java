package com.kaishengit.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailUtil {

    private static Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    /**
     * 发送一封HTML格式的邮件
     * @param toAddress 收件人的地址
     * @param subject 邮件主题
     * @param html 邮件内容（HTML格式）
     */
    public static void sendHtmlEmail(String toAddress,String subject,String html) {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(Config.get("mail.hostname"));
        htmlEmail.setSmtpPort(Integer.parseInt(Config.get("mail.port","25")));
        htmlEmail.setAuthentication(Config.get("mail.username"),Config.get("mail.password"));
        htmlEmail.setCharset(Config.get("mail.charset"));

        try {
            htmlEmail.setFrom(Config.get("mail.fromEmail"));

            htmlEmail.setSubject(subject);
            htmlEmail.setHtmlMsg(html);
            htmlEmail.addTo(toAddress);
            htmlEmail.send();

            logger.debug("给{}发邮件成功",toAddress);
        } catch (EmailException ex) {
            logger.error("给{}发邮件失败",toAddress);
            throw new RuntimeException("发送电子邮件异常",ex);
        }


    }

}
