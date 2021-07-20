package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.CustomerRemark;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
public interface CustomerRemarkService {
    /**
     * 批量保存创建的客户备注
     */
    int saveCustomerRemarkByList(List<CustomerRemark> remarkList);
}
