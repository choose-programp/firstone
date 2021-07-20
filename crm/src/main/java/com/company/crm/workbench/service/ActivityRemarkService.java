package com.company.crm.workbench.service;

import com.company.crm.workbench.domain.ActivityRemark;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/01
 */
public interface ActivityRemarkService {

    List<ActivityRemark> selectAllActivityRemarkByActivityId(String activityId);

    ActivityRemark selectActivityRemarkDetailsById(String id);

    int saveActivityRemark(ActivityRemark activityRemark);

    int deleteActivityRemarkById(String id);

    int updateEditFlagById(ActivityRemark activityRemark);

    int updateActivityRemark(ActivityRemark activityRemark);
}
