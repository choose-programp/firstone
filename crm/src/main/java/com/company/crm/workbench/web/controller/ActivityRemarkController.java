package com.company.crm.workbench.web.controller;

import com.company.crm.commons.contants.Constants;
import com.company.crm.commons.domain.ResponseObject;
import com.company.crm.commons.utils.DateUtils;
import com.company.crm.commons.utils.UUIDUtils;
import com.company.crm.settings.domain.User;
import com.company.crm.workbench.domain.ActivityRemark;
import com.company.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author conrad
 * @date 2021/07/01
 */
@Controller
@RequestMapping("/workbench/activity")
public class ActivityRemarkController {


    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/saveCreateActivityRemark")
    @ResponseBody
    public Object saveCreateActivityRemark(ActivityRemark activityRemark, HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activityRemark.setId(UUIDUtils.getUUID());
        activityRemark.setCreateTime(DateUtils.formatDateTime(new Date()));
        activityRemark.setCreateBy(user.getId());
        activityRemark.setEditFlag(Constants.NOT_DELETED);
        int i = activityRemarkService.saveActivityRemark(activityRemark);
        if (i == 0) {
            return ResponseObject.error("新增失败");
        } else {
            String id = activityRemark.getId();
            ActivityRemark remark = activityRemarkService.selectActivityRemarkDetailsById(id);
            Map<String, Object> map = new HashMap<>(2);
            map.put("retData", remark);
            return map;
        }
    }

    @RequestMapping("/deleteActivityRemarkById")
    @ResponseBody
    public Object deleteActivityRemarkById(ActivityRemark activityRemark){
        activityRemark.setEditFlag(Constants.IS_DELETED);
        int i = activityRemarkService.updateEditFlagById(activityRemark);
        if (i == 0) {
            return ResponseObject.error("删除失败");
        } else {
            return ResponseObject.success();
        }
    }

    @RequestMapping("/saveEditActivityRemark")
    @ResponseBody
    public Object saveEditActivityRemark(ActivityRemark activityRemark,HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activityRemark.setEditBy(user.getId());
        activityRemark.setEditTime(DateUtils.formatDateTime(new Date()));
        activityRemark.setEditFlag(Constants.IS_EDITED);
        int i = activityRemarkService.updateActivityRemark(activityRemark);
        if (i == 0) {
            return ResponseObject.error("更新失败");
        } else {
            ActivityRemark remark = activityRemarkService.selectActivityRemarkDetailsById(activityRemark.getId());
            return ResponseObject.success(remark);
        }
    }

}
