package com.kaishengit.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

public class Strings {

    public static String toUTF8(String str) {
        if(StringUtils.isNotEmpty(str)) {
            try {
                return new String(str.getBytes("ISO8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("字符串转码异常");
            }
        }
        return "";
    }

    public static String toPinyiin(String str) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE); //让拼音为小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); //不要音调
        format.setVCharType(HanyuPinyinVCharType.WITH_V); //让u使用v来表示
        try {
            return PinyinHelper.toHanYuPinyinString(str,format,"",true);
        } catch (BadHanyuPinyinOutputFormatCombination ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
