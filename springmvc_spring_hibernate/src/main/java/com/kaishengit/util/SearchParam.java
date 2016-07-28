package com.kaishengit.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class SearchParam {

    private String type;
    private String protertyName;
    private Object value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProtertyName() {
        return protertyName;
    }

    public void setProtertyName(String protertyName) {
        this.protertyName = protertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public static List<SearchParam> buiderSearchParam(HttpServletRequest request) {

        List<SearchParam> searchParamList = Lists.newArrayList();

        //1.获取所有的查询字符串
        Enumeration<String> enumeration = request.getParameterNames();

        while(enumeration.hasMoreElements()) {
            String queryString = enumeration.nextElement();
            String value = request.getParameter(queryString);
            if(queryString.startsWith("q_") && StringUtils.isNotEmpty(value)) {
                // q_like_xxx
                String[] array = queryString.split("_");
                if(array.length != 3) {
                    throw new RuntimeException("地址栏查询字符串格式错误:" + queryString);
                }
                String type = array[1];
                String propertyName = array[2];

                SearchParam searchParam = new SearchParam();
                searchParam.setProtertyName(propertyName);
                searchParam.setValue(Strings.toUTF8(value));
                searchParam.setType(type);

                searchParamList.add(searchParam);
            }
        }


        return searchParamList;
    }


}
