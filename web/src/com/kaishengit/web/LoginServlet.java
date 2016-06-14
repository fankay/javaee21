package com.kaishengit.web;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.判断验证码是否正确
        //1.1 获取表单中的值
        String captcha = req.getParameter("captcha");
        //1.2 获取session中的值
        String sessionCaptcha = (String) req.getSession().getAttribute("captcha");

        if(captcha != null && captcha.equalsIgnoreCase(sessionCaptcha)) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            //根据账号和密码去验证是否成功
            UserService userService = new UserService();
            User user = userService.login(username,password);

            if(user == null) {
                resp.sendRedirect("/login?code=1002");
            } else {
                logger.debug("显示home页面");
            }





        } else {
            //验证输入错误
            logger.warn("验证码错误");
            resp.sendRedirect("/login?code=1001");
        }




    }
}
