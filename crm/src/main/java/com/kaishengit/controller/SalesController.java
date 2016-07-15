package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.SalesLog;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.SalesService;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Inject
    private SalesService salesService;
    @Inject
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("customerList",customerService.findAllCustomer());
        return "sales/list";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Sales> load(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        //query param
        String name = request.getParameter("name");
        name = Strings.toUTF8(name);
        String progress = request.getParameter("progress");
        progress = Strings.toUTF8(progress);
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");

        Map<String,Object> params = Maps.newHashMap();
        params.put("start",start);
        params.put("length",length);
        params.put("name",name);
        params.put("progress",progress);
        params.put("startdate",startDate);
        params.put("enddate",endDate);

        List<Sales> salesList = salesService.findByParam(params);
        Long count = salesService.count();
        Long countParam = salesService.countByParam(params);

        return new DataTablesResult<>(draw,salesList,count,countParam);
    }

    /**
     * 新增销售机会
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String save(Sales sales) {
        salesService.saveSales(sales);
        return "success";
    }


    /**
     * 查看销售计划详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String viewSales(@PathVariable Integer id,Model model) {
        Sales sales = salesService.findSalesById(id);
        if(sales == null) {
            throw new NotFoundException();
        }
        if(!sales.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }

        model.addAttribute("sales",sales);

        //查找当前客户的跟进记录
        List<SalesLog> salesLogList = salesService.findSalesLogBySalesId(id);
        model.addAttribute(salesLogList);


        return "sales/view";
    }

    /**
     * 保存新的跟进日志
     * @param salesLog
     * @return
     */
    @RequestMapping(value = "/log/new",method = RequestMethod.POST)
    public String saveLog(SalesLog salesLog) {
        salesService.saveLog(salesLog);
        return "redirect:/sales/"+salesLog.getSalesid();
    }

    /**
     * 修改机会的进度
     */
    @RequestMapping(value = "/progress/edit",method = RequestMethod.POST)
    public String editProgress(Integer id,String progress) {
        salesService.editSalesProgress(id,progress);
        return "redirect:/sales/"+id;
    }


}
