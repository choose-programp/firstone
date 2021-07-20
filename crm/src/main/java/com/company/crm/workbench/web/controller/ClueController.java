package com.company.crm.workbench.web.controller;

import com.company.crm.commons.contants.Constants;
import com.company.crm.commons.domain.ResponseObject;
import com.company.crm.commons.utils.DateUtils;
import com.company.crm.commons.utils.UUIDUtils;
import com.company.crm.settings.domain.DicValue;
import com.company.crm.settings.domain.User;
import com.company.crm.settings.service.DicValueService;
import com.company.crm.settings.service.UserService;
import com.company.crm.workbench.domain.Activity;
import com.company.crm.workbench.domain.Clue;
import com.company.crm.workbench.domain.ClueActivityRelation;
import com.company.crm.workbench.domain.ClueRemark;
import com.company.crm.workbench.dto.ConvertClue;
import com.company.crm.workbench.service.ActivityService;
import com.company.crm.workbench.service.ClueActivityRelationService;
import com.company.crm.workbench.service.ClueRemarkService;
import com.company.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author conrad
 * @date 2021/06/27
 */
@Controller
@RequestMapping("/workbench/clue")
public class ClueController {

    @Autowired
    private ClueService clueService;

    @Autowired
    private ClueRemarkService clueRemarkService;

    @Autowired
    private UserService userService;

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ClueActivityRelationService clueActivityRelationService;

    @RequestMapping("/index")
    public String index(ModelMap map) {
        List<Clue> clueList = clueService.selectAllClue();
        List<User> userList = userService.selectAllUsers();
        List<DicValue> sourceList = dicValueService.selectDicValueByTypeCode("source");
        List<DicValue> appellationList = dicValueService.selectDicValueByTypeCode("appellation");
        List<DicValue> clueStateList = dicValueService.selectDicValueByTypeCode("clueState");
        map.put("clueList", clueList);
        map.put("userList", userList);
        map.put("sourceList", sourceList);
        map.put("appellationList", appellationList);
        map.put("clueStateList", clueStateList);
        return "workbench/clue/index";
    }


    @RequestMapping("/saveCreateClue")
    @ResponseBody
    public Object saveCreateClue(HttpSession session, Clue clue) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateUtils.formatDateTime(new Date()));
        clue.setCreateBy(user.getId());
        int i = clueService.saveClue(clue);
        if (i == 0) {
            return ResponseObject.error("新建失败");
        }
        return ResponseObject.success();
    }

    @RequestMapping("/detailClue")
    public String detailClue(String clueId, ModelMap map) {
        Clue clue = clueService.selectClueForDetailById(clueId);
        List<Activity> activityList = activityService.selectActivityForDetailByClueId(clueId);
        List<ClueRemark> clueRemarkList = clueRemarkService.selectClueRemarkForDetailByClueId(clueId);
        map.addAttribute("clue", clue);
        map.addAttribute("activityList", activityList);
        map.addAttribute("clueRemarkList", clueRemarkList);
        return "workbench/clue/detail";
    }

    @RequestMapping("/searchActivity")
    @ResponseBody
    public Object searchActivity(String activityName, String clueId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("activityName", Objects.requireNonNullElse(activityName, ""));
        map.put("clueId", clueId);
        return activityService.selectActivityNoBoundById(map);
    }


    @RequestMapping("/saveBoundActivity")
    @ResponseBody
    public Object saveBoundActivity(String[] activityId, String clueId) {
        List<ClueActivityRelation> list = new ArrayList<>();
        ClueActivityRelation clueActivityRelation;
        for (String id : activityId) {
            clueActivityRelation = new ClueActivityRelation();
            clueActivityRelation.setId(UUIDUtils.getUUID());
            clueActivityRelation.setClueId(clueId);
            clueActivityRelation.setActivityId(id);
            list.add(clueActivityRelation);
        }
        int i = clueActivityRelationService.saveClueActivityRelationByList(list);
        if (i == 0) {
            return ResponseObject.error("线索和市场活动的关联失败");
        }
        List<Activity> activityList = activityService.selectActivityForDetailByIds(activityId);
        return ResponseObject.success(activityList);
    }


    @RequestMapping("/saveUnboundActivity")
    @ResponseBody
    public Object saveUnboundActivity(String activityId, String clueId) {
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setClueId(clueId);
        clueActivityRelation.setActivityId(activityId);
        int i = clueActivityRelationService.deleteClueActivityRelationByClueIdActivityId(clueActivityRelation);
        if (i == 0) {
            return ResponseObject.error("线索和市场活动的解除关联失败");
        }
        return ResponseObject.success();
    }


    @RequestMapping("/convertClue")
    public String convertClue(String clueId, ModelMap map) {
        Clue clue = clueService.selectClueForDetailById(clueId);
        List<DicValue> stageList = dicValueService.selectDicValueByTypeCode("stage");
        map.put("clue", clue);
        map.put("stageList", stageList);
        return "workbench/clue/convert";
    }

    @RequestMapping("/queryActivityForDetailByName")
    @ResponseBody
    public Object queryActivityForDetailByName(String activityName, String clueId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("activityName", activityName);
        map.put("clueId", clueId);
        return activityService.selectActivityHasBoundById(map);
    }

    @RequestMapping("/saveConvertClue")
    @ResponseBody
    public Object saveConvertClue(HttpSession session, ConvertClue convertClue) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        convertClue.setOwner(user.getId());
        convertClue.setCreateBy(user.getId());
        int i = clueService.saveConvertClue(convertClue);
        if (i == 0) {
            return ResponseObject.error("转换失败");
        }
        return ResponseObject.success();
    }

}
