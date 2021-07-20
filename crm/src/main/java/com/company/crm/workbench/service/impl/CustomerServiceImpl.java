package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.Customer;
import com.company.crm.workbench.mapper.CustomerMapper;
import com.company.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/05
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 保存创建的客户
     *
     * @param customer
     */
    @Override
    public int saveCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 根据name模糊查询客户
     *
     * @param name
     */
    @Override
    public List<Customer> selectCustomerByName(String name) {
        return customerMapper.selectCustomerByName(name);
    }
}
