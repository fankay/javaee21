package com.kaishengit.web;

import com.kaishengit.entity.Document;
import com.kaishengit.service.DocumentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/file/list")
public class FileListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DocumentService documentService = new DocumentService();
        List<Document> documentList = documentService.findAllDocument();

        req.setAttribute("documentList",documentList);
        req.getRequestDispatcher("/WEB-INF/views/filelist.jsp").forward(req,resp);

    }
}
