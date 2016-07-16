package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.mapper.SalesFileMapper;
import com.kaishengit.mapper.SalesLogMapper;
import com.kaishengit.mapper.SalesMapper;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.SalesFile;
import com.kaishengit.pojo.SalesLog;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Value("${imagePath}")
    private String savePath;

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

    /**
     * 根据机会ID查找对应的文件列表
     * @param salesId
     * @return
     */
    public List<SalesFile> findSalesFileBySalesId(Integer salesId) {
        return salesFileMapper.findBySalesId(salesId);
    }

    /**
     * 保存机会相关资料文件
     * @param inputStream
     * @param originalFilename
     * @param contentType
     * @param size
     * @param salesid
     */
    @Transactional
    public void updateFile(InputStream inputStream, String originalFilename, String contentType, long size, Integer salesid) {
        String newName = UUID.randomUUID().toString();
        if(originalFilename.lastIndexOf(".")   != -1) {
             newName += originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(new File(savePath,newName));
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        SalesFile salesFile = new SalesFile();
        salesFile.setSalesid(salesid);
        salesFile.setContenttype(contentType);
        salesFile.setFilename(newName);
        salesFile.setName(originalFilename);
        salesFile.setSize(size);

        salesFileMapper.save(salesFile);

    }

    /**
     * 根据主键获取文件
     * @param id
     * @return
     */
    public SalesFile findSalesFileById(Integer id) {
        return salesFileMapper.findById(id);
    }

    /**
     * 根据主键删除销售机会
     * @param id
     */
    @Transactional
    public void delSales(Integer id) {
        Sales sales = salesMapper.findById(id);
        if(sales != null) {
            //删除对应的文件
            List<SalesFile> salesFileList = salesFileMapper.findBySalesId(id);
            if(!salesFileList.isEmpty()) {
                salesFileMapper.del(salesFileList);
            }
            //删除对应的跟进
            List<SalesLog> salesLogList = salesLogMapper.findBySalesId(id);
            if(!salesLogList.isEmpty()) {
                salesLogMapper.del(salesLogList);
            }

            //删除自己
            salesMapper.del(id);
        }
    }
}
