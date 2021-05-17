package cn.sixboys.controller;

import cn.sixboys.domain.Customer;
import cn.sixboys.domain.JsonResult;
import cn.sixboys.service.ICustomerService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/15 17:49
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private ICustomerService customerService;

    @RequestMapping("/queryForList")
    @ResponseBody
    public JsonResult queryForList(Integer currentPage, Integer pageSize, String keyword, Integer status){
        if(currentPage == null) {
            currentPage = 1;
        }
        if(pageSize ==null ) {
            pageSize = 4;
        }

        QueryObject queryObject = new QueryObject();
        queryObject.setCurrentPage(currentPage);
        queryObject.setPageSize(pageSize);
        if(keyword !=null){
            queryObject.setKeyword(keyword);
        }
        if (status != null){
            queryObject.setStatus(status);
        }
        PageResult<Customer> customers = customerService.selectCustomers(queryObject);
        return new JsonResult(true,"查询成功",customers);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public JsonResult insert(Customer customer){
        customer.setInputTime(new Date());
        customerService.insert(customer);
        return new JsonResult(true,"插入成功");
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(Customer customer){
        customerService.update(customer);
        return new JsonResult(true,"更新成功");
    }
}
