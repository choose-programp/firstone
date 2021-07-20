package com.company.crm.workbench.mapper;

import com.company.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * @author conrad
 * @date 2021/06/28
 */
public interface ActivityRemarkMapper {

    List<ActivityRemark> selectAllActivityRemarkByActivityId(String activityId);

    ActivityRemark selectActivityRemarkDetailsById(String id);

    int insertActivityRemark(ActivityRemark activityRemark);

    int deleteActivityRemarkById(String id);

    int updateEditFlagById(ActivityRemark activityRemark);

    int updateActivityRemark(ActivityRemark activityRemark);
}
