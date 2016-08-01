package com.kaishengit.action.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class LoginInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        ActionProxy proxy = invocation.getProxy();
        String namespace = proxy.getNamespace();
        String actionName = proxy.getActionName();

        if("/".equals(namespace) && ("index".equals(actionName) || "login".equals(actionName))) {
            return proxy.execute();
        } else {
            Map<String,Object> session = ActionContext.getContext().getSession();
            String sessionValue = (String) session.get("curr_user");
            if(sessionValue != null) {
                return proxy.execute();
            } else {
                return Action.LOGIN;
            }
        }
    }
}
