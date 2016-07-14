package com.kaishengit;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Test {

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {

        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE); //让拼音为小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE); //不要音调
        format.setVCharType(HanyuPinyinVCharType.WITH_V); //让u使用v来表示
        String result = PinyinHelper.toHanYuPinyinString("驴",format,"",true);

        System.out.println(result);


    }
}
