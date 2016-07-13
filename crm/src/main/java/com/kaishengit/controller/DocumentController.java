package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Document;
import com.kaishengit.service.DocumentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/doc")
public class DocumentController {

    @Inject
    private DocumentService documentService;
    @Value("${imagePath}")
    private String savePath;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model,
                       @RequestParam(required = false,defaultValue = "0") Integer fid) {

        List<Document> documentList = documentService.findDocumentByFid(fid);
        model.addAttribute("documentList",documentList);
        model.addAttribute("fid",fid);
        return "document/list";
    }

    /**
     * 保存新的文件夹
     */
    @RequestMapping(value = "/dir/new",method = RequestMethod.POST)
    public String saveDir(String name,Integer fid) {
        documentService.saveDir(name,fid);
        return "redirect:/doc?fid="+fid;
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public String saveFile(MultipartFile file,Integer fid) throws IOException {
        if(file.isEmpty()) {
            throw new NotFoundException();
        } else {
            documentService.saveFile(file.getInputStream(),file.getOriginalFilename(),file.getContentType(),file.getSize(),fid);
        }
        return "success";
    }


    @RequestMapping(value = "/download/{id:\\d+}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Integer id) throws FileNotFoundException, UnsupportedEncodingException {
        Document document = documentService.findDocumentById(id);
        if(document == null) {
            throw new NotFoundException();
        }
        File file = new File(savePath,document.getFilename());
        if(!file.exists()) {
            throw new NotFoundException();
        }

        FileInputStream inputStream = new FileInputStream(file);
        String fileName = document.getName();
        fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(document.getContexttype()))
                .contentLength(file.length())
                .header("Content-Disposition","attachment;filename=\""+fileName+"\"")
                .body(new InputStreamResource(inputStream));
    }
}
