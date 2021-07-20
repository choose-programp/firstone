package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.TranHistory;
import com.company.crm.workbench.mapper.TranHistoryMapper;
import com.company.crm.workbench.service.TranHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
@Service
public class TranHistoryServiceImpl implements TranHistoryService {

    @Autowired
    private TranHistoryMapper tranHistoryMapper;

    /**
     * 保存创建的交易历史
     *
     * @param tranHistory
     */
    @Override
    public int saveTranHistory(TranHistory tranHistory) {
        return tranHistoryMapper.insertTranHistory(tranHistory);
    }

    /**
     * 根据tranId查询该交易下所有的历史记录明细信息
     *
     * @param tranId
     */
    @Override
    public List<TranHistory> selectTranHistoryForDetailByTranId(String tranId) {
        return tranHistoryMapper.selectTranHistoryForDetailByTranId(tranId);
    }

    /**
     * 根据tranId查询该交易下所有的历史记录信息
     *
     * @param tranId
     */
    @Override
    public List<TranHistory> selectTranHistoryByTranId(String tranId) {
        return tranHistoryMapper.selectTranHistoryByTranId(tranId);
    }
}
