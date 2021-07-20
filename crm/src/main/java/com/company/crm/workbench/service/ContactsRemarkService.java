package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.ContactsRemark;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
public interface ContactsRemarkService {
    /**
     * 批量保存创建联系人备注
     */
    int saveContactsRemarkByList(List<ContactsRemark> remarkList);
}
