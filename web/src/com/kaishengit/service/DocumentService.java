package com.kaishengit.service;

import com.kaishengit.dao.DocumentDao;
import com.kaishengit.entity.Document;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class DocumentService {

    private DocumentDao documentDao = new DocumentDao();

    public void updateFile(String fileName,Long size,InputStream inputStream) throws IOException {
        //为了支持inputstream的reset方法，将inputstream转换为ByteArrayInputStream
        ByteArrayInputStream byteArrayInputStream =
                new ByteArrayInputStream(IOUtils.toByteArray(inputStream));

        //计算文件的md5值
        String md5 = DigestUtils.md5Hex(byteArrayInputStream);

        //根据MD5值去数据库中查找是否有已经存在的文件
        Document document = documentDao.findByMd5(md5);
        if(document == null) {

            //保存文件
            String saveFileName = saveFile(fileName, byteArrayInputStream);

            document = new Document();
            document.setFilename(fileName);
            document.setSavename(saveFileName);
            document.setExtname(fileName.substring(fileName.indexOf(".")));
            document.setMd5(md5);
            document.setSize(size);
            document.setDisplaysize(FileUtils.byteCountToDisplaySize(size));

            documentDao.save(document);
        }

    }

    private String saveFile(String fileName,InputStream inputStream) throws IOException {

        inputStream.reset();

        File dir = new File("D:/upload");
        if(!dir.exists()) {
            dir.mkdir();
        }

        String extName = fileName.substring(fileName.indexOf(".")); // .jpg
        String uuid = UUID.randomUUID().toString();
        fileName = uuid + extName;
        FileOutputStream output = new FileOutputStream(new File(dir,fileName));

        IOUtils.copy(inputStream,output);

        output.flush();
        output.close();
        inputStream.close();

        return fileName;
    }

    public List<Document> findAllDocument() {
        return documentDao.findAll();
    }

    public Document findDocumentByMd5(String md5) {
        return documentDao.findByMd5(md5);
    }
}
