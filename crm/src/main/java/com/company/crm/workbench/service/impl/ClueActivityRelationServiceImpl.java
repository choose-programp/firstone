package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.ClueActivityRelation;
import com.company.crm.workbench.mapper.ClueActivityRelationMapper;
import com.company.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/03
 */
@Service
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    /**
     * 批量保存创建的线索和市场活动的关联关系
     *
     * @param relationList
     */
    @Override
    public int saveClueActivityRelationByList(List<ClueActivityRelation> relationList) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(relationList);
    }

    /**
     * 根据clueId和activityId删除线索和市场活动的关联关系
     *
     * @param relation
     */
    @Override
    public int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation relation) {
        return clueActivityRelationMapper.deleteClueActivityRelationByClueIdActivityId(relation);
    }

    /**
     * 根据clueId查询线索和市场活动的关联关系
     *
     * @param clueId
     */
    @Override
    public List<ClueActivityRelation> selectClueActivityRelationByClueId(String clueId) {
        return clueActivityRelationMapper.selectClueActivityRelationByClueId(clueId);
    }

    /**
     * 根据clueId删除线索和市场活动的关联关系
     *
     * @param clueId
     */
    @Override
    public int deleteClueActivityRelationByClueId(String clueId) {
        return clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);
    }
}
