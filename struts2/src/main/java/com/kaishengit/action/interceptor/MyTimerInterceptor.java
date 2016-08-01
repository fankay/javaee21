package com.kaishengit.action.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MyTimerInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        ActionProxy actionProxy = invocation.getProxy();
        String namespace = actionProxy.getNamespace(); //获取目标对象的namespace
        String methodName = actionProxy.getMethod(); //目标对象执行的方法名称
        String name = actionProxy.getActionName(); // 目标对象在URL中请求的名称

        long stratTime = System.currentTimeMillis();
        String result = actionProxy.execute(); //目标对象（Action）方法的执行
        long endTime = System.currentTimeMillis();

        System.out.println((namespace + "/" +  name) + " 方法：" + methodName + "耗费:" + (endTime - stratTime) + "ms");

        return result;
    }
}
