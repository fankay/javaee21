package com.kaishengit.mapper;

import com.kaishengit.pojo.Sales;

import java.util.List;
import java.util.Map;

public interface SalesMapper {

    void save(Sales sales);

    List<Sales> findByParam(Map<String, Object> params);

    Long countByParam(Map<String, Object> params);

    List<Sales> findByCustId(Integer custId);

    Sales findById(Integer id);

    void update(Sales sales);

    void del(Integer id);
}
