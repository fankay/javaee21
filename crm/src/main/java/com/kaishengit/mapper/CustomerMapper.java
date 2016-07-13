package com.kaishengit.mapper;

import com.kaishengit.pojo.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    List<Customer> findByParam(Map<String, Object> params);

    Long count();

    Long countByParam(Map<String, Object> params);

    List<Customer> findByType(String type);

    void save(Customer customer);

    Customer findById(Integer id);

    List<Customer> findCompanyLikeName(String keyword);
}
