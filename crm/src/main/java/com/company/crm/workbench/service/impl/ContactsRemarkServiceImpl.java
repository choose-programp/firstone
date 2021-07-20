package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.ContactsRemark;
import com.company.crm.workbench.mapper.ContactsRemarkMapper;
import com.company.crm.workbench.service.ContactsRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
@Service
public class ContactsRemarkServiceImpl implements ContactsRemarkService {

    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;

    /**
     * 批量保存创建联系人备注
     *
     * @param remarkList
     */
    @Override
    public int saveContactsRemarkByList(List<ContactsRemark> remarkList) {
        return contactsRemarkMapper.insertContactsRemarkByList(remarkList);
    }
}
