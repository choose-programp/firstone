package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.Contacts;

/**
 * @author conrad
 * @date 2021/07/05
 */
public interface ContactsService {
    /**
     * 保存创建的联系人
     */
    int saveContacts(Contacts contacts);
}
