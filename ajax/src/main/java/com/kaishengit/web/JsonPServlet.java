package com.kaishengit.web;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/jsonp")
public class JsonPServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodName = req.getParameter("callback");

        User user = new User(1,"lisi","China",77F);

        // handler({id:1,...})

        PrintWriter out = resp.getWriter();
        out.print(methodName+"("+new Gson().toJson(user)+")");
        out.flush();
        out.close();
    }
}
