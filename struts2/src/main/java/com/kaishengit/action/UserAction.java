package com.kaishengit.action;

import com.kaishengit.pojo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware,ServletContextAware,ApplicationAware {

    private User user;
    private List<String> names;

    private Map<String,Object> session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;
    private Map<String,Object> application;

    /*public UserAction() {
        System.out.println("Create UserAction....");
    }*/

    public String toSave() {
        //1. 获取Session的第一种方式
        /*ActionContext actionContext = ActionContext.getContext();
        Map<String,Object> session = actionContext.getSession();
        session.put("hello","world");*/

        session.put("hi","Hehe");

        //获取HttpServletRequest和HttpServletResponse
        /*HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();*/

        /*Map<String,Object> application = ActionContext.getContext().getApplication();
        application.put("hello","world");*/


        System.out.println("UserAction save...");
        return SUCCESS;
    }

    public String save() {
        System.out.println("username:" + user.getUsername() + " address:" + user.getAddress());
        return SUCCESS;
    }

    public String list() {
        System.out.println("UserAction list...");

        names = new ArrayList<>();
        names.add("aaa");
        names.add("bbb");
        names.add("ccc");

        return SUCCESS;
    }

    //get set
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void setApplication(Map<String, Object> map) {
        this.application = map;
    }
}
