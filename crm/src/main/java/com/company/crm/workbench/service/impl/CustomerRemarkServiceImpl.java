package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.CustomerRemark;
import com.company.crm.workbench.mapper.CustomerRemarkMapper;
import com.company.crm.workbench.service.CustomerRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
@Service
public class CustomerRemarkServiceImpl implements CustomerRemarkService {

    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;

    /**
     * 批量保存创建的客户备注
     *
     * @param remarkList
     */
    @Override
    public int saveCustomerRemarkByList(List<CustomerRemark> remarkList) {
        return customerRemarkMapper.insertCustomerRemarkByList(remarkList);
    }
}
