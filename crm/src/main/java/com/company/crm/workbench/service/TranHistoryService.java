package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.TranHistory;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
public interface TranHistoryService {
    /**
     * 保存创建的交易历史
     */
    int saveTranHistory(TranHistory tranHistory);

    /**
     * 根据tranId查询该交易下所有的历史记录明细信息
     */
    List<TranHistory> selectTranHistoryForDetailByTranId(String tranId);

    /**
     * 根据tranId查询该交易下所有的历史记录信息
     */
    List<TranHistory> selectTranHistoryByTranId(String tranId);
}
