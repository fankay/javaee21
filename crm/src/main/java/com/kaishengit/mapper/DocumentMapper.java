package com.kaishengit.mapper;

import com.kaishengit.pojo.Document;

import java.util.List;

public interface DocumentMapper {

    void save(Document document);

    List<Document> findByFid(Integer fid);

    Document findById(Integer id);

    void del(Integer id);

}
