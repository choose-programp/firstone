package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.TranRemark;
import com.company.crm.workbench.mapper.TranRemarkMapper;
import com.company.crm.workbench.service.TranRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
@Service
public class TranRemarkServiceImpl implements TranRemarkService {

    @Autowired
    private TranRemarkMapper tranRemarkMapper;

    /**
     * 批量保存创建的交易备注
     *
     * @param remarkList
     */
    @Override
    public int saveTranRemarkByList(List<TranRemark> remarkList) {
        return tranRemarkMapper.insertTranRemarkByList(remarkList);
    }

    /**
     * 根据tranId查询该交易下所有备注的明细信息
     *
     * @param tranId
     */
    @Override
    public List<TranRemark> selectTranRemarkForDetailByTranId(String tranId) {
        return tranRemarkMapper.selectTranRemarkForDetailByTranId(tranId);
    }
}
