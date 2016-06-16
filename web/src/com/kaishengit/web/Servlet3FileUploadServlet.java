package com.kaishengit.web;

import com.kaishengit.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

@WebServlet("/servlet/upload")
@MultipartConfig //servlet3.x 用于处理文件提交的注解
public class Servlet3FileUploadServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(Servlet3FileUploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/upload3.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        //1. 文件上传表单必须是post提交
        //2. 将form属性值修改为 enctype="multipart/form-data"(修改该属性后，
        //   会导致request的getParameter()方法无法获取表单中的值)
        //3. 给处理文件上传的servlet添加 @MultipartConfig 注解

        //获取普通表单元素的值
        String fileDesc = req.getParameter("fileDesc");
        logger.debug("文件的描述为:{}",fileDesc);

        //获取文件元素的值
        Part part = req.getPart("doc");
        logger.debug("size:{}",part.getSize());
        InputStream input = part.getInputStream();

        DocumentService documentService = new DocumentService();
        documentService.updateFile(getFileName(part),part.getSize(),input);












        /*String contentType = part.getContentType(); //获取上传文件的MIME头
        logger.debug("文件的ContentType:{}",contentType);

        long size = part.getSize(); //获取文件的大小(字节) 1237182763 -> 1.5MB
        logger.debug("文件的大小size:{}",size);

        String fileName = getFileName(part);
        logger.debug("文件的原始名字:{}",fileName);

        saveFile(part);*/



    }

    /*private void saveFile(Part part) throws IOException{
        File dir = new File("D:/upload");
        if(!dir.exists()) {
            dir.mkdir();
        }

        String fileName = getFileName(part); //2.jpg
        String extName = fileName.substring(fileName.indexOf(".")); // .jpg
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + extName;

        InputStream input = part.getInputStream();
        FileOutputStream output = new FileOutputStream(new File(dir,fileName));

        IOUtils.copy(input,output);

        output.flush();
        output.close();
        input.close();


        *//*InputStream input = part.getInputStream();
        FileOutputStream output = new FileOutputStream(new File(dir,getFileName(part)));

        BufferedInputStream bufferInput = new BufferedInputStream(input);
        BufferedOutputStream bufferOutput = new BufferedOutputStream(output);

        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = bufferInput.read(buffer)) != -1) {
            bufferOutput.write(buffer,0,len);
        }

        bufferOutput.flush();
        bufferOutput.close();
        bufferInput.close();*//*

    }*/


    private String getFileName(Part part) throws UnsupportedEncodingException {
        String headerValue = part.getHeader("Content-Disposition");
        headerValue = headerValue.substring(headerValue.indexOf("filename=\""));
        headerValue = headerValue.substring(headerValue.indexOf("\"")+1,headerValue.length()-1);
        return headerValue;
    }





}
