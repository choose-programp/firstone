package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.ClueRemark;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/05
 */
public interface ClueRemarkService {


    int saveClueRemark(ClueRemark record);

    /**
     * 根据clueId查询该线索下所有备注明细信息
     */
    List<ClueRemark> selectClueRemarkForDetailByClueId(String clueId);

    /**
     * @根据clueId查询该线索下所有的备注
     */
    List<ClueRemark> selectClueRemarkByClueId(String clueId);

    ClueRemark selectClueRemarkById(String id);

    /**
     * 根据clueId删除该线索下所有的备注
     */
    int deleteClueRemarkByClueId(String clueId);

    /**
     *更新备注明细信息
     */
    int updateClueRemark(ClueRemark clueRemark);

}
