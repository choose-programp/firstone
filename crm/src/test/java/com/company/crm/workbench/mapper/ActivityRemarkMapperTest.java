package com.company.crm.workbench.mapper;

import basetest.MyBaseTest;
import com.company.crm.workbench.domain.ActivityRemark;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ActivityRemarkMapperTest extends MyBaseTest {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Test
    public void selectAllActivityRemarkByActivityId() {
        List<ActivityRemark> activityRemarks = activityRemarkMapper.selectAllActivityRemarkByActivityId("6");
        System.out.println(activityRemarks.size());
        activityRemarks.forEach(activityRemark -> {
            System.out.println(activityRemark);
        });
    }
}