package com.company.crm.workbench.web.controller;

import com.company.crm.commons.contants.Constants;
import com.company.crm.commons.domain.ResponseObject;
import com.company.crm.commons.utils.ConvertBean;
import com.company.crm.commons.utils.DateUtils;
import com.company.crm.commons.utils.UUIDUtils;
import com.company.crm.settings.domain.User;
import com.company.crm.settings.service.UserService;
import com.company.crm.workbench.domain.Activity;
import com.company.crm.workbench.domain.ActivityRemark;
import com.company.crm.workbench.dto.CreateActivity;
import com.company.crm.workbench.dto.SearchActivity;
import com.company.crm.workbench.service.ActivityRemarkService;
import com.company.crm.workbench.service.ActivityService;
import com.company.crm.workbench.vo.ActivityVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author conrad
 * @date 2021/06/27
 */
@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/index")
    public String index(ModelMap modelMap) {
        //jetbrains://idea/navigate/reference?project=crm&path=WEB-INF/pages/workbench/activity/index.jsp
        List<User> userList = userService.selectAllUsers();
        modelMap.addAttribute("userList", userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/queryActivityForPageByCondition")
    @ResponseBody
    public Object queryActivityForPageByCondition(SearchActivity searchActivity) {
        Integer pageNo = (searchActivity.getPageNo() - 1) * searchActivity.getPageSize();
        searchActivity.setPageNo(pageNo);
        Map<String, Object> map = null;
        try {
            map = ConvertBean.convertBeanToMap(searchActivity);
        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        List<Activity> activityList = activityService.selectActivityForPageByCondition(map);
        long totalRows = activityService.selectActivityCountByCondition(map);
        Map<String, Object> data = new HashMap<>(4);
        data.put("activityList", activityList);
        data.put("totalRows", totalRows);
        return data;
    }

    @RequestMapping("/saveCreateActivity")
    @ResponseBody
    public Object saveCreateActivity(HttpSession session, CreateActivity createActivity) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        Activity activity = new Activity();
        BeanUtils.copyProperties(createActivity, activity);
        activity.setId(UUIDUtils.getUUID());
        activity.setOwner(user.getId());
        activity.setCreateBy(user.getId());
        String time = DateUtils.formatDateTime(new Date());
        activity.setCreateTime(time);
        int i = activityService.saveActivity(activity);
        if (i == 0) {
            return ResponseObject.error("新增失败");
        } else {
            return ResponseObject.success();
        }
    }

    @RequestMapping("/detailActivity")
    public String detailActivity(String id, ModelMap modelMap) {
        Activity activity = activityService.selectActivityForDetailById(id);
        List<ActivityRemark> remarkList = activityRemarkService.selectAllActivityRemarkByActivityId(id);
        modelMap.addAttribute("activity", activity);
        modelMap.addAttribute("remarkList", remarkList);
        return "workbench/activity/detail";
    }

    @RequestMapping("editActivity")
    @ResponseBody
    public Object editActivity(String id) {
        return activityService.selectActivityById(id);
    }

    @RequestMapping("/saveEditActivity")
    @ResponseBody
    public Object saveEditActivity(HttpSession session, Activity activity) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        activity.setOwner(user.getId());
        activity.setEditBy(user.getId());
        String time = DateUtils.formatDateTime(new Date());
        activity.setEditTime(time);
        int i = activityService.updateActivity(activity);
        if (i == 0) {
            return ResponseObject.error("更新失败");
        } else {
            return ResponseObject.success();
        }
    }

    @RequestMapping("/deleteActivityByIds")
    @ResponseBody
    public Object deleteActivityByIds(String[] id) {
        int i = activityService.deleteActivityByIds(id);
        if (i == 0) {
            return ResponseObject.error("删除失败");
        } else {
            return ResponseObject.success();
        }
    }

    @RequestMapping("/exportAllActivity")
    @ResponseBody
    public Object exportAllActivity(String[] id, HttpServletResponse response) {
        List<ActivityVo> activityVoList = activityService.selectAllActivityForExcel(id);
        HSSFWorkbook wb = activityService.exportExcel(activityVoList);
        String fileName = URLEncoder.encode("市场活动", StandardCharsets.UTF_8);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename="+fileName+".xls");
            response.setContentType("application/octet-stream;charset=UTF-8");
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseObject.success();
    }

    @RequestMapping("/importActivity")
    @ResponseBody
    public Object importActivity(HttpSession session, MultipartFile activityFile){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        List<Activity> activityList = activityService.parseExcel(user, activityFile);
        int i = activityService.saveActivityByList(activityList);
        if (i == 0){
            return ResponseObject.error("上传失败");
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("code", "1");
        map.put("count", activityList.size());
        return map;
    }

}
