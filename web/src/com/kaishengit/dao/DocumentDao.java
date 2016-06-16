package com.kaishengit.dao;

import com.kaishengit.entity.Document;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class DocumentDao {

    public void save(Document document) {
        String sql = "INSERT INTO t_document(filename, savename, md5, extname, size, displaysize) values(?,?,?,?,?,?)";
        DbHelp.update(sql,document.getFilename(),document.getSavename(),document.getMd5(),document.getExtname(),document.getSize(),document.getDisplaysize());
    }

    public Document findByMd5(String md5) {
        String sql = "select * from t_document where md5 = ?";
        return DbHelp.query(sql,new BeanHandler<>(Document.class),md5);
    }

    public List<Document> findAll() {
        String sql = "select * from t_document order by id desc";
        return DbHelp.query(sql,new BeanListHandler<>(Document.class));
    }
}
