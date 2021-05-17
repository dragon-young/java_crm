package cn.sixboys.service.impl;

import cn.sixboys.domain.Customer;
import cn.sixboys.domain.Employee;
import cn.sixboys.mapper.CustomerMapper;
import cn.sixboys.service.ICustomerService;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/15 17:47
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public PageResult<Customer> selectCustomers(QueryObject queryObject) {
        List<Customer> customers = customerMapper.queryForList(queryObject);
        // 查询总记录数
        int i = customerMapper.queryForCount(queryObject);
        PageResult<Customer> pageResult = new PageResult<>(queryObject.getCurrentPage(), queryObject.getPageSize(), i, customers);
        return  pageResult;
    }

    @Override
    public void insert(Customer customer) {
        customerMapper.insert(customer);
    }

    @Override
    public void update(Customer customer) {
        customerMapper.update(customer);
    }
}
