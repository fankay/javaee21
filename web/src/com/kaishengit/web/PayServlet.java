package com.kaishengit.web;

import com.sun.deploy.util.SessionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/pay")
public class PayServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(PayServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1. 产生Token
        String token = UUID.randomUUID().toString();
        //2. 放入session
        HttpSession session = req.getSession();
        session.setAttribute("token",token);
        //3. 放入表单
        req.setAttribute("token",token);

        req.getRequestDispatcher("/WEB-INF/views/pay.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 从表单中获取Token
        String token = req.getParameter("token");
        //2. 从session中获取token
        HttpSession session = req.getSession();
        String sessionToken = (String) session.getAttribute("token");
        //3. 判断表单中的token和session中的token是否一致
        if(token != null && token.equals(sessionToken)) {

            //4. 将session中的token删除
            session.removeAttribute("token");

            String money = req.getParameter("money");
            logger.info("成功扣款{}元",money);

            //请求转发的形式去了结果页面
            req.getRequestDispatcher("/WEB-INF/views/paysuc.jsp").forward(req,resp);
        } else {
            logger.warn("表单重复提交");
            req.getRequestDispatcher("/WEB-INF/views/payerror.jsp").forward(req,resp);
        }





        // 解决表单重复提交方案1：PRG模式(POST-REDIRECT-GET) （重定向到结果页面）
        // https://en.wikipedia.org/wiki/Post/Redirect/Get
        //resp.sendRedirect("/pay/suc");

    }
}
