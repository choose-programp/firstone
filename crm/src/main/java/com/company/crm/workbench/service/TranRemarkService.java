package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.TranRemark;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/06
 */
public interface TranRemarkService {
    /**
     * 批量保存创建的交易备注
     */
    int saveTranRemarkByList(List<TranRemark> remarkList);

    /**
     * 根据tranId查询该交易下所有备注的明细信息
     */
    List<TranRemark> selectTranRemarkForDetailByTranId(String tranId);
}
