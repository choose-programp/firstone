package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.Customer;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/05
 */
public interface CustomerService {

    /**
     * 保存创建的客户
     */
    int saveCustomer(Customer customer);

    /**
     * 根据name模糊查询客户
     */
    List<Customer> selectCustomerByName(String name);

}
