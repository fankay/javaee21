package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.mapper.SalesFileMapper;
import com.kaishengit.mapper.SalesLogMapper;
import com.kaishengit.mapper.SalesMapper;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.SalesLog;
import com.kaishengit.util.ShiroUtil;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class SalesService {

    @Inject
    private SalesMapper salesMapper;
    @Inject
    private SalesFileMapper salesFileMapper;
    @Inject
    private SalesLogMapper salesLogMapper;
    @Inject
    private CustomerMapper customerMapper;

    /**
     * 新增销售机会
     * @param sales
     */
    @Transactional
    public void saveSales(Sales sales) {
        sales.setUserid(ShiroUtil.getCurrentUserID());
        sales.setUsername(ShiroUtil.getCurrentRealName());
        sales.setCustname(customerMapper.findById(sales.getCustid()).getName());

        salesMapper.save(sales);

        //自动产生创建日志
        SalesLog salesLog = new SalesLog();
        salesLog.setType(SalesLog.LOG_TYPE_AUTO);
        salesLog.setContext(ShiroUtil.getCurrentRealName() + " 创建了该销售机会");
        salesLog.setSalesid(sales.getId());
        salesLogMapper.save(salesLog);
    }

    public List<Sales> findByParam(Map<String, Object> params) {
        if(ShiroUtil.isEmployee()) {
            params.put("userid",ShiroUtil.getCurrentUserID());
        }
        return salesMapper.findByParam(params);
    }

    public Long count() {
        Map<String,Object> params = Maps.newHashMap();
        if(ShiroUtil.isEmployee()) {
            params.put("userid",ShiroUtil.getCurrentUserID());
        }
        return salesMapper.countByParam(params);
    }

    public Long countByParam(Map<String, Object> params) {
        if(ShiroUtil.isEmployee()) {
            params.put("userid",ShiroUtil.getCurrentUserID());
        }
        return salesMapper.countByParam(params);
    }

    /**
     * 查找客户ID对应的所有销售机会
     * @param custId
     * @return
     */
    public List<Sales> findSalesByCustId(Integer custId) {
        return salesMapper.findByCustId(custId);
    }

    /**
     * 根据ID查找销售机会
     * @param id
     * @return
     */
    public Sales findSalesById(Integer id) {
        return salesMapper.findById(id);
    }

    /**
     * 根据销售机会ID查找对应的跟进日志
     * @param salesId
     * @return
     */
    public List<SalesLog> findSalesLogBySalesId(Integer salesId) {
        return salesLogMapper.findBySalesId(salesId);
    }

    /**
     * 保存新的跟进日志
     * @param salesLog
     */
    @Transactional
    public void saveLog(SalesLog salesLog) {
        salesLog.setType(SalesLog.LOG_TYPE_INPUT);
        salesLogMapper.save(salesLog);

        //修改机会的最后跟进时间
        Sales sales = salesMapper.findById(salesLog.getSalesid());
        sales.setLasttime(DateTime.now().toString("yyyy-MM-dd"));
        salesMapper.update(sales);
    }

    /**
     * 修改机会的进度
     * @param id
     * @param progress
     */
    @Transactional
    public void editSalesProgress(Integer id, String progress) {
        Sales sales = salesMapper.findById(id);
        sales.setProgress(progress);
        //修改最后跟进时间
        sales.setLasttime(DateTime.now().toString("yyyy-MM-dd"));
        //判断进度是否是『完成交易』
        if("完成交易".equals(progress)) {
            sales.setSuccesstime(DateTime.now().toString("yyyy-MM-dd"));
        }
        salesMapper.update(sales);

        //添加跟进日志
        SalesLog salesLog = new SalesLog();
        salesLog.setType(SalesLog.LOG_TYPE_AUTO);
        salesLog.setContext(ShiroUtil.getCurrentRealName() + " 更改进度为：" + progress);
        salesLog.setSalesid(sales.getId());
        salesLogMapper.save(salesLog);
    }
}
