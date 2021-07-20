package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.ContactsActivityRelation;
import com.company.crm.workbench.mapper.ContactsActivityRelationMapper;
import com.company.crm.workbench.service.ContactsActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */

@Service
public class ContactsActivityRelationServiceImpl implements ContactsActivityRelationService {

    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;

    /**
     * 批量添加联系人和市场活动的关联关系
     *
     * @param relationList
     */
    @Override
    public int saveContactsActivityRelationByList(List<ContactsActivityRelation> relationList) {
        return contactsActivityRelationMapper.insertContactsActivityRelationByList(relationList);
    }
}
