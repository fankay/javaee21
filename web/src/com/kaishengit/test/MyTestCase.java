package com.kaishengit.test;

import org.junit.Test;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.font.RandomFontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

public class MyTestCase {

    @Test
    public void testUUID() {

        UUID uuid = UUID.randomUUID();
        String key = uuid.toString();

        System.out.println(key);
    }

    @Test
    public void testCaptcha() throws Exception {

        ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
        cs.setColorFactory(new SingleColorFactory(new Color(67, 78, 170)));
        cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));

        RandomWordFactory randomWordFactory = new RandomWordFactory();
        randomWordFactory.setMinLength(4);
        randomWordFactory.setMaxLength(4);
        randomWordFactory.setCharacters("0123456789拉克丝剪短发阿隆索款到即发拉克丝的费卢卡斯大家费卢卡斯剪短发了");
        cs.setWordFactory(randomWordFactory);

        cs.setFontFactory(new FontFactory() {
            @Override
            public Font getFont(int i) {
                return new Font("微软雅黑",Font.ITALIC,24);
            }
        });

        FileOutputStream outputStream = new FileOutputStream("D:/captcha.png");
        EncoderHelper.getChallangeAndWriteImage(cs,"png",outputStream);
        outputStream.flush();
        outputStream.close();

    }

}
