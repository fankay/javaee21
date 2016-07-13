package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.Page;
import com.kaishengit.util.ShiroUtil;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class CustomerService {

    @Inject
    private CustomerMapper customerMapper;

    public List<Customer> findCustomerByParams(Map<String, Object> params) {
        if(ShiroUtil.isEmployee()) {
            params.put("userid",ShiroUtil.getCurrentUserID());
        }
        return customerMapper.findByParam(params);
    }

    public Long count() {
        if(ShiroUtil.isEmployee()) {
            Map<String,Object> params = Maps.newHashMap();
            params.put("userid",ShiroUtil.getCurrentUserID());
            return customerMapper.countByParam(params);
        }
        return customerMapper.count();
    }

    public Long countByParam(Map<String, Object> params) {
        if(ShiroUtil.isEmployee()) {
            params.put("userid",ShiroUtil.getCurrentUserID());
        }
        return customerMapper.countByParam(params);
    }
}
