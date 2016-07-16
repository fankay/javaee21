package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.SalesFile;
import com.kaishengit.pojo.SalesLog;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.SalesService;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @Inject
    private SalesService salesService;
    @Inject
    private CustomerService customerService;
    @Value("${imagePath}")
    private String savePath;

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

        //查找当前机会的跟进记录
        List<SalesLog> salesLogList = salesService.findSalesLogBySalesId(id);
        model.addAttribute(salesLogList);

        //查找当前机会的文件列表
        List<SalesFile> salesFileList = salesService.findSalesFileBySalesId(id);
        model.addAttribute(salesFileList);


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

    /**
     * 上传资料文件
     * @param file
     * @param salesid
     * @return
     */
    @RequestMapping(value = "/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public String updateFile(MultipartFile file ,Integer salesid) throws IOException {
        salesService.updateFile(file.getInputStream(),file.getOriginalFilename(),file.getContentType(),file.getSize(),salesid);
        return "success";
    }

    /**
     * 下载文件
     * @param id
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/file/{id:\\d+}/download",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Integer id) throws FileNotFoundException, UnsupportedEncodingException {
        SalesFile salesFile = salesService.findSalesFileById(id);
        if(salesFile == null) {
            throw new NotFoundException();
        }
        File file = new File(savePath,salesFile.getFilename());
        if(!file.exists()) {
            throw new NotFoundException();
        }
        FileInputStream inputStream = new FileInputStream(file);
        String fileName = salesFile.getName();
        fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");
        return ResponseEntity
                .ok()
                .contentLength(salesFile.getSize())
                .contentType(MediaType.parseMediaType(salesFile.getContenttype()))
                .header("Content-Disposition","attachment;filename=\""+fileName+"\"")
                .body(new InputStreamResource(inputStream));
    }

    /**
     * 删除销售机会
     * @return
     */
    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    public String delSales(@PathVariable Integer id) {
        salesService.delSales(id);
        return "redirect:/sales";
    }

}
