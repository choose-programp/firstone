package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.Contacts;
import com.company.crm.workbench.mapper.ContactsMapper;
import com.company.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author conrad
 * @date 2021/07/05
 */
@Service
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsMapper contactsMapper;

    /**
     * 保存创建的联系人
     *
     * @param contacts
     */
    @Override
    public int saveContacts(Contacts contacts) {
        return contactsMapper.insertContacts(contacts);
    }
}
