package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.ContactsActivityRelation;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
public interface ContactsActivityRelationService {
    /**
     * 批量添加联系人和市场活动的关联关系
     */
    int saveContactsActivityRelationByList(List<ContactsActivityRelation> relationList);
}
