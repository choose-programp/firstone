package com.company.crm.workbench.service.impl;

import com.company.crm.commons.contants.Constants;
import com.company.crm.commons.utils.UUIDUtils;
import com.company.crm.workbench.domain.*;
import com.company.crm.workbench.dto.ConvertClue;
import com.company.crm.workbench.dto.InitValue;
import com.company.crm.workbench.mapper.ClueMapper;
import com.company.crm.workbench.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author conrad
 * @date 2021/07/03
 */
@Service
public class ClueServiceImpl implements ClueService {

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRemarkService customerRemarkService;

    @Autowired
    private ContactsService contactsService;

    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private ContactsActivityRelationService contactsActivityRelationService;

    @Autowired
    private ContactsRemarkService contactsRemarkService;

    @Autowired
    @Qualifier("tranServiceImpl")
    private TranService tranService;

    @Autowired
    private TranRemarkService tranRemarkService;


    /**
     * 保存创建的线索
     *
     * @param clue
     */
    @Override
    public int saveClue(Clue clue) {
        return clueMapper.insertClue(clue);
    }

    /**
     * 根据id查询线索明细信息
     *
     * @param id
     */
    @Override
    public Clue selectClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }

    @Override
    public List<Clue> selectAllClue() {
        return clueMapper.selectAllClue();
    }

    /**
     * 根据id查询线索信息
     *
     * @param id
     */
    @Override
    public Clue selectClueById(String id) {
        return clueMapper.selectClueById(id);
    }

    /**
     * 根据id删除线索
     *
     * @param id
     */
    @Override
    public int deleteClueById(String id) {
        return clueMapper.deleteClueById(id);
    }

    @Override
    public int saveConvertClue(ConvertClue convertClue) {
        //线索id
        String clueId = convertClue.getClueId();
        Clue clue = clueMapper.selectClueById(clueId);
        ContactsActivityRelation contactsActivityRelation;
        CustomerRemark customerRemark;
        ContactsRemark contactsRemark;
        Tran tran;
        TranRemark tranRemark;
        List<ContactsActivityRelation> contactsActivityRelationList = new ArrayList<>();
        List<CustomerRemark> customerRemarkList = new ArrayList<>();
        List<ContactsRemark> contactsRemarkList = new ArrayList<>();
        List<ClueActivityRelation> clueActivityRelationList = clueActivityRelationService.selectClueActivityRelationByClueId(clueId);
        List<ClueRemark> clueRemarkList = clueRemarkService.selectClueRemarkByClueId(clueId);
        List<TranRemark> tranRemarkList = new ArrayList<>();
        //创建客户
        Customer customer = new Customer();
        BeanUtils.copyProperties(clue, customer);
        BeanUtils.copyProperties(new InitValue(), customer);
        customer.setOwner(convertClue.getOwner());
        customer.setCreateBy(convertClue.getCreateBy());
        customer.setName(clue.getFullName());
        int i = customerService.saveCustomer(customer);
        //创建联系人
        Contacts contacts = new Contacts();
        BeanUtils.copyProperties(clue, contacts);
        BeanUtils.copyProperties(new InitValue(), contacts);
        contacts.setCreateBy(convertClue.getCreateBy());
        contacts.setOwner(convertClue.getOwner());
        contacts.setCustomerId(customer.getId());
        int i1 = contactsService.saveContacts(contacts);
        //线索备注转换
        if (!CollectionUtils.isEmpty(clueRemarkList)) {
            for (ClueRemark clueRemark : clueRemarkList) {
                //线索备注转为客户备注
                customerRemark = new CustomerRemark();
                BeanUtils.copyProperties(clueRemark, customerRemark);
                customerRemark.setCustomerId(customer.getId());
                customerRemark.setId(UUIDUtils.getUUID());
                customerRemarkList.add(customerRemark);
                //线索备注转为联系人备注
                contactsRemark = new ContactsRemark();
                BeanUtils.copyProperties(clueRemark, contactsRemark);
                contactsRemark.setContactsId(contacts.getId());
                contactsRemark.setId(UUIDUtils.getUUID());
                contactsRemarkList.add(contactsRemark);
            }
        }
        int i2 = customerRemarkService.saveCustomerRemarkByList(customerRemarkList);
        int i3 = contactsRemarkService.saveContactsRemarkByList(contactsRemarkList);
        //(线索-活动关)系转为(联系人-活动)关系
        if (!CollectionUtils.isEmpty(clueActivityRelationList)) {
            for (ClueActivityRelation clueActivityRelation : clueActivityRelationList) {
                contactsActivityRelation = new ContactsActivityRelation();
                contactsActivityRelation.setId(UUIDUtils.getUUID());
                contactsActivityRelation.setContactsId(contacts.getId());
                contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
                contactsActivityRelationList.add(contactsActivityRelation);
            }
        }
        int i4 = contactsActivityRelationService.saveContactsActivityRelationByList(contactsActivityRelationList);
        //是否创建交易
        int y = 1;
        if (Constants.IS_CREATE_TRAN.equals(convertClue.getIsCreateTran())) {
            //创建交易
            tran = new Tran();
            BeanUtils.copyProperties(convertClue, tran);
            BeanUtils.copyProperties(clue, tran);
            BeanUtils.copyProperties(new InitValue(), tran);
            tran.setId(UUIDUtils.getUUID());
            tran.setName(clue.getFullName());
            tran.setMoney(convertClue.getAmountOfMoney());
            tran.setExpectedDate(convertClue.getExpectedClosingDate());
            tran.setContactsId(contacts.getId());
            tran.setCustomerId(customer.getId());
            tran.setType(Constants.TRAN_TYPE);
            int i5 = tranService.saveTran(tran);
            //创建交易备注
            if (!CollectionUtils.isEmpty(clueRemarkList)){
                for (ClueRemark clueRemark : clueRemarkList) {
                    tranRemark = new TranRemark();
                    BeanUtils.copyProperties(clueRemark, tranRemark);
                    tranRemark.setId(UUIDUtils.getUUID());
                    tranRemark.setTranId(tran.getId());
                    tranRemarkList.add(tranRemark);
                }
            }
            int i6 = tranRemarkService.saveTranRemarkByList(tranRemarkList);
            y = i5 & i6;
        }
        //删除线索备注
        int i5 = clueRemarkService.deleteClueRemarkByClueId(clueId);
        //删除线索
        int i6 = clueMapper.deleteClueById(clueId);
        //删除市场活动和线索的关系
        int i7 = clueActivityRelationService.deleteClueActivityRelationByClueId(clueId);
        return i & i1 & i2 & i3 & i4 & y & i5 & i6 & i7;
    }

}
