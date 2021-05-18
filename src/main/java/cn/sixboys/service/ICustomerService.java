package cn.sixboys.service;

import cn.sixboys.domain.Customer;
import cn.sixboys.util.PageResult;
import cn.sixboys.util.QueryObject;

import java.util.List;

/**
 * @author mingjuntang
 * @Data 2021/5/15 17:47
 */
public interface ICustomerService {
    PageResult<Customer> selectCustomers(QueryObject queryObject);

    /**
     * 新增一条客户数据
     * @param customer
     */
    void insert(Customer customer);

    /**
     * 更新一条客户数据
     * @param customer
     */
    void update(Customer customer);

}
