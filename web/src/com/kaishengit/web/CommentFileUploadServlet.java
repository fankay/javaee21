package com.kaishengit.web;

import com.kaishengit.service.DocumentService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CommentFileUploadServlet extends HttpServlet {

    private Logger logger = LoggerFactory.getLogger(CommentFileUploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          if(ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();

            ServletContext servletContext = getServletContext();
            //tomcat的temp目录
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            diskFileItemFactory.setRepository(repository);//设置文件上传的临时路径
            diskFileItemFactory.setSizeThreshold(1024); //设置缓冲区大小

            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);

            try {
                List<FileItem> fileItemList = servletFileUpload.parseRequest(req);
                for(FileItem fileItem : fileItemList) {
                    if(fileItem.isFormField()) {
                        //isFormField()返回值为true，表示普通表单元素
                        String fieldName = fileItem.getFieldName(); //获取表单元素name属性值
                        if("fileDesc".equals(fieldName)) {
                            String value = fileItem.getString("UTF-8"); //获取表单元素的值
                            logger.debug("{} -> {}",fieldName,value);
                        }
                    } else {
                        //代表是文件上传表单元素
                       /* DocumentService documentService = new DocumentService();
                        documentService.up*/

                        String fileName = fileItem.getName();
                        long size = fileItem.getSize();
                        String contentType = fileItem.getContentType();

                        logger.debug("fileName: {},Size: {},ContentType:{}",fileName,size,contentType);

                        DocumentService documentService = new DocumentService();
                        documentService.updateFile(fileName,size,fileItem.getInputStream());

                        //InputStream inputStream = fileItem.getInputStream();


                    }
                }


                resp.sendRedirect("/file/list");
            } catch (FileUploadException e) {
                e.printStackTrace();
            }


        }

    }
}
