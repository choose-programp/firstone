package com.company.crm.workbench.web.controller;

import com.company.crm.commons.contants.Constants;
import com.company.crm.commons.domain.ResponseObject;
import com.company.crm.commons.utils.DateUtils;
import com.company.crm.commons.utils.UUIDUtils;
import com.company.crm.settings.domain.User;
import com.company.crm.workbench.domain.ClueRemark;
import com.company.crm.workbench.service.ClueRemarkService;
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
 * @date 2021/07/05
 */
@Controller
@RequestMapping("/workbench/clue")
public class ClueRemarkController {

    @Autowired
    private ClueRemarkService clueRemarkService;

    @RequestMapping("/saveCreateClueRemark")
    @ResponseBody
    public Object saveCreateClueRemark(ClueRemark clueRemark, HttpSession session) {
        clueRemark.setId(UUIDUtils.getUUID());
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        clueRemark.setCreateBy(user.getId());
        clueRemark.setCreateTime(DateUtils.formatDateTime(new Date()));
        clueRemark.setEditFlag(Constants.NOT_DELETED);
        int i = clueRemarkService.saveClueRemark(clueRemark);
        if (i == 0) {
            return ResponseObject.error("新增失败");
        } else {
            String id = clueRemark.getId();
            ClueRemark remark = clueRemarkService.selectClueRemarkById(id);
            Map<String, Object> map = new HashMap<>(2);
            map.put("retData", remark);
            return map;
        }
    }

    @RequestMapping("/deleteClueRemarkById")
    @ResponseBody
    public Object deleteClueRemarkById(ClueRemark clueRemark) {
        clueRemark.setEditFlag(Constants.IS_DELETED);
        int i = clueRemarkService.updateClueRemark(clueRemark);
        if (i == 0) {
            return ResponseObject.error("删除失败");
        } else {
            return ResponseObject.success();
        }
    }

    @RequestMapping("/saveEditClueRemark")
    @ResponseBody
    public Object saveEditClueRemark(ClueRemark clueRemark, HttpSession session) {
        initClueRemark(clueRemark, session,Constants.IS_EDITED);
        int i = clueRemarkService.updateClueRemark(clueRemark);
        if (i == 0) {
            return ResponseObject.error("更新失败");
        } else {
            ClueRemark remark = clueRemarkService.selectClueRemarkById(clueRemark.getId());
            return ResponseObject.success(remark);
        }
    }

    private void initClueRemark(ClueRemark clueRemark, HttpSession session,String editFlag) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        clueRemark.setEditBy(user.getId());
        clueRemark.setEditTime(DateUtils.formatDateTime(new Date()));
        clueRemark.setEditFlag(editFlag);
    }

}
