package com.kaishengit.mapper;

import com.kaishengit.pojo.Customer;
import org.apache.ibatis.annotations.Param;

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

    List<Customer> findByCompanyId(Integer id);

    void update(Customer cust);

    void del(Integer id);

    List<Customer> findAll(Integer userId);

    Long findNewCustomerCount(@Param("start") String start,@Param("end") String end);
}
