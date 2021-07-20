package com.company.crm.workbench.service.impl;

import basetest.MyBaseTest;
import com.company.crm.workbench.domain.Activity;
import com.company.crm.workbench.service.ActivityService;
import com.company.crm.workbench.vo.ActivityVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityServiceImplTest extends MyBaseTest {

    @Autowired
    private ActivityService activityService;

    @Test
    public void selectActivityForPageByCondition() {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNo", 0);
        map.put("pageSize", 10);
        map.put("name", "xx");
        map.put("owner", null);
//        map.put("startDate", "2021-09-01");
//        map.put("endDate", "2021-10-01");
        List<Activity> activityList = activityService.selectActivityForPageByCondition(map);
        System.out.println(activityList.size());
    }

    @Test
    public void exportExcel() {
        List<ActivityVo> activityVos = activityService.selectAllActivityForExcel(new String[0]);
        activityService.exportExcel(activityVos);
    }

    @Test
    public void convertBean() throws IOException {
        List<ActivityVo> activityVos = activityService.selectAllActivityForExcel(new String[0]);
        HSSFWorkbook wb = activityService.exportExcels(activityVos);
        wb.write(new FileOutputStream(new File("d:\\cd.xls")));
    }
}