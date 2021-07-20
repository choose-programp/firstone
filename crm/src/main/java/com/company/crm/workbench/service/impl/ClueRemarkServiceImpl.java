package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.ClueRemark;
import com.company.crm.workbench.mapper.ClueRemarkMapper;
import com.company.crm.workbench.service.ClueRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/05
 */
@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public int saveClueRemark(ClueRemark record) {
        return clueRemarkMapper.insertClueRemark(record);
    }

    /**
     * 根据clueId查询该线索下所有备注明细信息
     *
     */
    @Override
    public List<ClueRemark> selectClueRemarkForDetailByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkForDetailByClueId(clueId);
    }

    /**
     *
     * 根据clueId查询该线索下所有的备注
     */
    @Override
    public List<ClueRemark> selectClueRemarkByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkByClueId(clueId);
    }

    @Override
    public ClueRemark selectClueRemarkById(String id) {
        return clueRemarkMapper.selectClueRemarkById(id);
    }

    /**
     * 根据clueId删除该线索下所有的备注
     */
    @Override
    public int deleteClueRemarkByClueId(String clueId) {
        return clueRemarkMapper.deleteClueRemarkByClueId(clueId);
    }

    /**
     * 更新备注明细信息
     */
    @Override
    public int updateClueRemark(ClueRemark clueRemark) {
        return clueRemarkMapper.updateClueRemark(clueRemark);
    }
}
