package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.Task;
import com.kaishengit.pojo.User;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.SalesService;
import com.kaishengit.service.TaskService;
import com.kaishengit.service.UserService;
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
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Inject
    private CustomerService customerService;
    @Inject
    private UserService userService;
    @Inject
    private SalesService salesService;
    @Inject
    private TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("companyList",customerService.findAllCompany());
        return "customer/list";
    }


    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Customer> load(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");

        Map<String,Object> params = Maps.newHashMap();
        params.put("start",start);
        params.put("length",length);
        params.put("keyword",keyword);

        List<Customer> customerList = customerService.findCustomerByParams(params);
        Long count = customerService.count();
        Long filterCount = customerService.countByParam(params);

        return new DataTablesResult<>(draw,customerList,count,filterCount);

    }

    /**
     * 显示所有公司信息
     * @return
     */
    @RequestMapping(value = "/company.json",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> showAllCompanyJson() {
        return customerService.findAllCompany();
    }

    /**
     * 添加新客户
     * @param customer
     * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String save(Customer customer) {
        customerService.saveCustomer(customer);
        return "success";
    }

    /**
     * 删除客户
     */
    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String del(@PathVariable Integer id) {
        customerService.delCustomer(id);
        return "success";
    }

    /**
     * 显示客户信息
     */
    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String viewCustomer(@PathVariable Integer id,Model model) {
        Customer customer = customerService.findCustomerById(id);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(customer.getUserid() != null && !customer.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }
        model.addAttribute("customer",customer);

        if(customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)) {
            List<Customer> customerList = customerService.findCustomerByCompanyId(id);
            model.addAttribute("customerList",customerList);
        }

        //加载所有员工
        List<User> userList = userService.finAllUser();
        model.addAttribute("userList",userList);

        //加载客户对应的销售机会列表
        List<Sales> salesList = salesService.findSalesByCustId(id);
        model.addAttribute("salesList",salesList);

        //待办事项列表


        return "customer/view";
    }


    /**
     * 编辑客户
     */
    @RequestMapping(value = "/edit/{id:\\d+}.json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> editCustomer(@PathVariable Integer id) {

        Customer customer = customerService.findCustomerById(id);

        Map<String,Object> result = Maps.newHashMap();


        if(customer == null) {
            result.put("state","error");
            result.put("message","找不到对应的客户");
        } else {
            List<Customer> companyList = customerService.findAllCompany();
            result.put("state","success");
            result.put("customer",customer);
            result.put("companyList",companyList);
        }
        return result;
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public String edit(Customer customer) {
        customerService.editCustomer(customer);
        return "success";
    }

    /**
     * 公开客户
     */
    @RequestMapping(value = "/open/{id:\\d+}",method = RequestMethod.GET)
    public String openCustomer(@PathVariable Integer id) {
        Customer customer = customerService.findCustomerById(id);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(customer.getUserid() != null && !customer.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }
        customerService.openCustomer(customer);
        return "redirect:/customer/"+id;
    }

    /**
     * 转移客户
     */
    @RequestMapping(value = "/move",method = RequestMethod.POST)
    public String moveCust(Integer id,Integer userid) {
        Customer customer = customerService.findCustomerById(id);
        if(customer == null) {
            throw new NotFoundException();
        }
        if(customer.getUserid() != null && !customer.getUserid().equals(ShiroUtil.getCurrentUserID()) && !ShiroUtil.isManager()) {
            throw new ForbiddenException();
        }
        customerService.moveCust(customer,userid);
        return "redirect:/customer";
    }

    /**
     * 将客户信息生成二维码
     */
    @RequestMapping(value = "/qrcode/{id:\\d+}.png",method = RequestMethod.GET)
    public void makeQrCode(@PathVariable Integer id,HttpServletResponse response) throws IOException, WriterException {
        String mecard = customerService.makeMeCard(id);

        Map<EncodeHintType,String> hints = Maps.newHashMap();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(mecard, BarcodeFormat.QR_CODE,200,200,hints);

        OutputStream outputStream = response.getOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"png",outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 给关联客户添加待办事项
     * @param task
     * @param hour
     * @param min
     * @return
     */
    @RequestMapping(value = "/task/new",method = RequestMethod.POST)
    public String newTask(Task task,String hour,String min) {
        taskService.saveTask(task,hour,min);
        return "redirect:/customer/"+task.getCustid();
    }
}


