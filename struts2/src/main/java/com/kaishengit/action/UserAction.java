package com.kaishengit.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import java.util.ArrayList;
import java.util.List;

@Namespace("/user")
public class UserAction extends BaseAction {

    private String code;
    private String name;
    private List<String> nameList;


    @Action("home")
    public String list() {
        name = "Jack";
        System.out.println("User Home....");

        nameList = new ArrayList<>();
        nameList.add("A1");
        nameList.add("A2");
        nameList.add("A3");

        return SUCCESS;
    }

    @Action(value = "new",results = {
            @Result(name = "success",type = "redirectAction",
                    params = {"actionName","home","namespace","/user","code","${code}"})
    })
    public String toSave() {
        code = "10005";
        return SUCCESS;
    }

    //get set


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }
}
