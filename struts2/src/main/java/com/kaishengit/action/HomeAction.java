package com.kaishengit.action;

import javax.servlet.http.HttpSession;

public class HomeAction extends BaseAction {

    private String username;
    private String password;
    private String code;

    public String toLogin() {
        return SUCCESS;
    }

    public String login() {
        if("tom".equals(username) && "123123".equals(password)) {
            HttpSession session = getHttpSession();
            session.setAttribute("curr_user",username);
            return SUCCESS;
        } else {
            code = "10009";
            return INPUT;
        }
    }


    //get set

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
