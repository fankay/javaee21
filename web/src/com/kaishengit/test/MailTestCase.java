package com.kaishengit.test;

import com.kaishengit.util.EmailUtil;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

public class MailTestCase {

    @Test
    public void sentTextMail() {

        SimpleEmail mail = new SimpleEmail();
        mail.setAuthentication("kaishengit","p@ssw@rd");
        mail.setHostName("smtp.126.com");
        mail.setSmtpPort(25);
        mail.setCharset("UTF-8");

        try {
            mail.setFrom("kaishengit@126.com");

            mail.setSubject("今天又讲了javamail");
            mail.setMsg("<h1 style='color:red'>Hello,JavaMail</h1>");

            mail.addTo("fankai@kaishengit.com");

            mail.send();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void sentHtmlMail() {

        HtmlEmail mail = new HtmlEmail();
        mail.setAuthentication("kaishengit","p@ssw@rd");
        mail.setHostName("smtp.126.com");
        mail.setSmtpPort(25);
        mail.setCharset("UTF-8");

        try {
            mail.setFrom("kaishengit@126.com");

            mail.setSubject("这是一封HTML邮件");
            mail.setHtmlMsg("<h1 style='color:red'>Hello,JavaMail</h1><img src='http://ww1.sinaimg.cn/mw690/824de770jw1epwcnivby6j20go0p00x4.jpg'/>");

            mail.addTo("fankai@kaishengit.com");

            mail.send();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Test
    public void sentAttachMail() {

        MultiPartEmail mail = new MultiPartEmail();
        mail.setAuthentication("kaishengit","p@ssw@rd");
        mail.setHostName("smtp.126.com");
        mail.setSmtpPort(25);
        mail.setCharset("UTF-8");

        try {
            mail.setFrom("kaishengit@126.com");

            mail.setSubject("这是一份有附件的邮件");
            mail.setMsg("内容见附件");

            mail.addTo("fankai@kaishengit.com");

            EmailAttachment emailAttachment = new EmailAttachment();
            emailAttachment.setPath("D:/upload/1.jpg");

            mail.attach(emailAttachment);
            mail.send();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    @Test
    public void testSendHtmlEmail() {
        EmailUtil.sendHtmlEmail("fankai@kaishengit.com","今天下午来我办公室","<h3>Hi!^_^</h3>");
    }

}
