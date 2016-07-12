package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class FilePreviewController {

    @Value("${imagePath}")
    private String filePath;


    @RequestMapping("/preview/{fileName}")
    public void previewFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {

        File file = new File(filePath,fileName);
        if(!file.exists()) {
            throw new NotFoundException();
        }
        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);

        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }


}
