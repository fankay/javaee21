package com.kaishengit.action;

import com.kaishengit.pojo.User;

public class JSONAction extends BaseAction {

    private Integer id;
    private String name;
    private User user;


    @Override
    public String execute() throws Exception {
        id = 101;
        name = "Jack";
        user = new User();
        user.setUsername("李斯");
        return SUCCESS;
    }

    //get set


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
