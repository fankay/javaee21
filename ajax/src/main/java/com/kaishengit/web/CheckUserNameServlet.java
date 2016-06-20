package com.kaishengit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkusername")
public class CheckUserNameServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(CheckUserNameServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        logger.debug("username:{}",username);

        PrintWriter out = resp.getWriter();
        if("tom".equals(username)) {
            out.print("no");
        } else {
            out.print("yes");
        }
        out.flush();
        out.close();


    }
}
