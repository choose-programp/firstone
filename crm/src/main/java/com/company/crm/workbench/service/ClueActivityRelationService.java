package com.company.crm.workbench.service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/03
 */
public interface ClueActivityRelationService {
    /**
     * 批量保存创建的线索和市场活动的关联关系
     */
    int saveClueActivityRelationByList(List<com.company.crm.workbench.domain.ClueActivityRelation> relationList);

    /**
     * 根据clueId和activityId删除线索和市场活动的关联关系
     */
    int deleteClueActivityRelationByClueIdActivityId(com.company.crm.workbench.domain.ClueActivityRelation relation);

    /**
     * 根据clueId查询线索和市场活动的关联关系
     */
    List<com.company.crm.workbench.domain.ClueActivityRelation> selectClueActivityRelationByClueId(String clueId);

    /**
     * 根据clueId删除线索和市场活动的关联关系
     */
    int deleteClueActivityRelationByClueId(String clueId);
}
