package com.company.crm.workbench.service.impl;

import com.company.crm.workbench.domain.ActivityRemark;
import com.company.crm.workbench.mapper.ActivityRemarkMapper;
import com.company.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author conrad
 * @date 2021/07/01
 */
@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> selectAllActivityRemarkByActivityId(String activityId) {
        return activityRemarkMapper.selectAllActivityRemarkByActivityId(activityId);
    }

    @Override
    public ActivityRemark selectActivityRemarkDetailsById(String id) {
        return activityRemarkMapper.selectActivityRemarkDetailsById(id);
    }

    @Override
    public int saveActivityRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.insertActivityRemark(activityRemark);
    }

    @Override
    public int deleteActivityRemarkById(String id) {
        return activityRemarkMapper.deleteActivityRemarkById(id);
    }

    @Override
    public int updateEditFlagById(ActivityRemark activityRemark) {
        return activityRemarkMapper.updateEditFlagById(activityRemark);
    }

    @Override
    public int updateActivityRemark(ActivityRemark activityRemark) {
        return activityRemarkMapper.updateActivityRemark(activityRemark);
    }
}
