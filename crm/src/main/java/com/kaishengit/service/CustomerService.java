package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 查询客户中所有的公司
     * @return
     */
    public List<Customer> findAllCompany() {
        return customerMapper.findByType(Customer.CUSTOMER_TYPE_COMPANY);
    }

    /**
     * 保存新客户
     * @param customer
     */
    public void saveCustomer(Customer customer) {
        if(customer.getCompanyid() != null) {
            Customer company = customerMapper.findById(customer.getCompanyid());
            customer.setCompanyname(company.getName());
        }
        customer.setUserid(ShiroUtil.getCurrentUserID());
        customer.setPinyin(Strings.toPinyiin(customer.getName()));
        customerMapper.save(customer);
    }

    public List<Customer> findCompanyByKeyword(String keyword) {
        return customerMapper.findCompanyLikeName(keyword);
    }

    /**
     * 根据ID删除客户
     * @param id
     */
    @Transactional
    public void delCustomer(Integer id) {
        Customer customer = customerMapper.findById(id);
        if(customer != null) {
            if(customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)) {
                //被删除用户是公司，查找是否有关联客户，如果有，则将公司ID和名称设置为空
                List<Customer> customerList = customerMapper.findByCompanyId(id);
                for(Customer cust : customerList) {
                    cust.setCompanyname(null);
                    cust.setCompanyid(null);
                    customerMapper.update(cust);
                }
            }

            //TODO 删除关联的项目
            //TODO 删除关联的代办事项

            customerMapper.del(id);
        }
    }

    /**
     * 根据客户ID查找客户
     * @param id
     * @return
     */
    public Customer findCustomerById(Integer id) {
        return customerMapper.findById(id);
    }

    /**
     * 修改客户
     * @param customer
     */
    @Transactional
    public void editCustomer(Customer customer) {
        if(customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)) {
            //找到关联的客户，并修改名字
            List<Customer> customerList = customerMapper.findByCompanyId(customer.getId());
            for(Customer cust : customerList) {
                cust.setCompanyid(customer.getId());
                cust.setCompanyname(customer.getName());
                customerMapper.update(cust);
            }
        } else {
            if(customer.getCompanyid() != null) {
                Customer company = customerMapper.findById(customer.getCompanyid());
                customer.setCompanyname(company.getName());
            }
        }

        customer.setPinyin(Strings.toPinyiin(customer.getName()));
        customerMapper.update(customer);
    }

    /**
     * 根据公司ID查找所有的客户
     * @param id
     * @return
     */
    public List<Customer> findCustomerByCompanyId(Integer id) {
        return customerMapper.findByCompanyId(id);
    }

    /**
     * 将客户公开
     * @param customer
     */
    public void openCustomer(Customer customer) {
        customer.setUserid(null);
        customerMapper.update(customer);
    }

    /**
     * 转移客户
     * @param customer
     * @param userid
     */
    public void moveCust(Customer customer, Integer userid) {
        customer.setUserid(userid);
        customerMapper.update(customer);
    }

    /**
     * 将客户信息生成MECard格式
     * @param id
     * @return
     */
    public String makeMeCard(Integer id) {
        Customer customer = customerMapper.findById(id);

        StringBuilder mecard = new StringBuilder("MECARD:");
        if(StringUtils.isNotEmpty(customer.getName())) {
            mecard.append("N:"+customer.getName()+";");
        }
        if(StringUtils.isNotEmpty(customer.getTel())) {
            mecard.append("TEL:"+customer.getTel()+";");
        }
        if(StringUtils.isNotEmpty(customer.getEmail())) {
            mecard.append("EMAIL:"+customer.getEmail()+";");
        }
        if(StringUtils.isNotEmpty(customer.getAddress())) {
            mecard.append("ADR:"+customer.getAddress()+";");
        }
        if(StringUtils.isNotEmpty(customer.getCompanyname())) {
            mecard.append("ORG:"+customer.getCompanyname()+";");
        }
        mecard.append(";");

        return mecard.toString();
    }

    /**
     * 获取所有的客户
     * @return
     */
    public List<Customer> findAllCustomer() {
        Integer userid = null;
        if(ShiroUtil.isEmployee()) {
            userid = ShiroUtil.getCurrentUserID();
        }
        return customerMapper.findAll(userid);
    }
}
