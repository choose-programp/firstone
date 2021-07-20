package com.company.crm.workbench.service.impl;

import com.company.crm.commons.utils.DateUtils;
import com.company.crm.commons.utils.UUIDUtils;
import com.company.crm.workbench.domain.Customer;
import com.company.crm.workbench.domain.Tran;
import com.company.crm.workbench.domain.TranHistory;
import com.company.crm.workbench.dto.CreateTranDto;
import com.company.crm.workbench.dto.InitValue;
import com.company.crm.workbench.mapper.TranMapper;
import com.company.crm.workbench.service.CustomerService;
import com.company.crm.workbench.service.TranHistoryService;
import com.company.crm.workbench.service.TranService;
import com.company.crm.workbench.vo.FunnelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author conrad
 * @date 2021/07/06
 */
@Service("tranServiceImpl")
public class TranServiceImpl implements TranService {

    @Autowired
    private TranMapper tranMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TranHistoryService tranHistoryService;

    /**
     * 保存创建交易
     *
     * @param tran
     */
    @Override
    public int saveTran(Tran tran) {
        return tranMapper.insertTran(tran);
    }

    /**
     * 根据id查询交易明细信息
     *
     * @param id
     */
    @Override
    public Tran selectTranForDetailById(String id) {
        return tranMapper.selectTranForDetailById(id);
    }

    /**
     * 查询交易表中各个阶段的数据量
     */
    @Override
    public List<FunnelVO> selectCountOfTranGroupByStage() {
        return tranMapper.selectCountOfTranGroupByStage();
    }

    @Override
    public List<Tran> selectAllTranForDetail() {
        return tranMapper.selectAllTranForDetail();
    }

    @Override
    public String readProperties(String key) throws IOException {
        InputStream resource = TranServiceImpl.class.getClassLoader().getResourceAsStream("possibility.properties");
        Properties properties = new Properties();
        properties.load(resource);
        return properties.getProperty(key);
    }

    @Override
    public int saveTran(CreateTranDto createTranDto, String userId) {
        Tran tran = new Tran();
        BeanUtils.copyProperties(createTranDto, tran);
        tran.setId(UUIDUtils.getUUID());
        tran.setOwner(userId);
        tran.setCreateBy(userId);
        tran.setCreateTime(DateUtils.formatDateTime(new Date()));
        int j = 1;
        if (StringUtils.isEmpty(createTranDto.getCustomerId().trim())) {
            //用户
            Customer customer = new Customer();
            BeanUtils.copyProperties(new InitValue(), customer);
            customer.setName(createTranDto.getName());
            tran.setCustomerId(customer.getId());
            j = customerService.saveCustomer(customer);
        }
        int i = saveTran(tran);
        //交易历史
        TranHistory tranHistory = new TranHistory();
        BeanUtils.copyProperties(tran, tranHistory);
        BeanUtils.copyProperties(new InitValue(), tranHistory);
        tranHistory.setTranId(tran.getId());
        int k = tranHistoryService.saveTranHistory(tranHistory);
        return i & j & k;
    }

}
