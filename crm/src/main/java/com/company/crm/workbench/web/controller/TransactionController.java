package com.company.crm.workbench.web.controller;

import com.company.crm.commons.contants.Constants;
import com.company.crm.commons.domain.ResponseObject;
import com.company.crm.settings.domain.DicValue;
import com.company.crm.settings.domain.User;
import com.company.crm.settings.service.DicValueService;
import com.company.crm.settings.service.UserService;
import com.company.crm.workbench.domain.Tran;
import com.company.crm.workbench.domain.TranHistory;
import com.company.crm.workbench.dto.CreateTranDto;
import com.company.crm.workbench.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author conrad
 * @date 2021/06/27
 */
@Controller
@RequestMapping("/workbench/transaction")
public class TransactionController {

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("tranServiceImpl")
    private TranService tranService;

    @Autowired
    private TranHistoryService tranHistoryService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ContactsService contactsService;

    @RequestMapping("/index")
    public String index(ModelMap modelMap) {
        List<Tran> tranList = tranService.selectAllTranForDetail();
        List<DicValue> stageList = dicValueService.selectDicValueByTypeCode("stage");
        List<DicValue> sourceList = dicValueService.selectDicValueByTypeCode("source");
        List<DicValue> transactionTypeList = dicValueService.selectDicValueByTypeCode("transactionType");
        modelMap.addAttribute("tranList", tranList);
        modelMap.addAttribute("stageList", stageList);
        modelMap.addAttribute("sourceList", sourceList);
        modelMap.addAttribute("transactionTypeList", transactionTypeList);
        return "workbench/transaction/index";
    }

    @RequestMapping("/createTran")
    public String createTran(ModelMap modelMap) {
        List<User> userList = userService.selectAllUsers();
        List<DicValue> stageList = dicValueService.selectDicValueByTypeCode("stage");
        List<DicValue> sourceList = dicValueService.selectDicValueByTypeCode("source");
        List<DicValue> transactionTypeList = dicValueService.selectDicValueByTypeCode("transactionType");
        modelMap.addAttribute("userList", userList);
        modelMap.addAttribute("stageList", stageList);
        modelMap.addAttribute("sourceList", sourceList);
        modelMap.addAttribute("transactionTypeList", transactionTypeList);
        return "workbench/transaction/save";
    }

    @RequestMapping("/saveCreateTran")
    @ResponseBody
    public Object saveCreateTran(HttpSession session, CreateTranDto createTranDto) {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        int i = tranService.saveTran(createTranDto, user.getId());
        if (i == 0){
            return ResponseObject.error("创建失败");
        }
        return ResponseObject.success();
    }

    @RequestMapping("/detailTran")
    public String detailTran(ModelMap modelMap, String tranId) {
        Tran tran = tranService.selectTranForDetailById(tranId);
        List<DicValue> stageList = dicValueService.selectDicValueByTypeCode("stage");
        List<TranHistory> tranHistoryList = tranHistoryService.selectTranHistoryForDetailByTranId(tranId);
        String theOrderNo = null;
        if (!CollectionUtils.isEmpty(tranHistoryList)) {
            TranHistory tranHistory = tranHistoryList.get(tranHistoryList.size() - 1);
            theOrderNo = tranHistory.getOrderNo();
        }
        String possibility = null;
        try {
            possibility = tranService.readProperties(tran.getStage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        tran.setPossibility(Objects.requireNonNullElse(possibility + "%", ""));
        modelMap.addAttribute("tran", tran);
        modelMap.addAttribute("stageList", stageList);
        modelMap.addAttribute("tranHistoryList", tranHistoryList);
        modelMap.addAttribute("theOrderNo", Objects.requireNonNullElse(theOrderNo, ""));
        return "workbench/transaction/detail";
    }

    @RequestMapping("/getPossibilityByStageValue")
    @ResponseBody
    public Object getPossibilityByStageValue(String stageValue) {
        String possibility = null;
        try {
            possibility = tranService.readProperties(stageValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return possibility;
    }

    @RequestMapping("/queryCustomerByName")
    @ResponseBody
    public Object queryCustomerByName(String customerName) {
        return customerService.selectCustomerByName(customerName);
    }

    @RequestMapping("/queryActivityForDetailByName")
    @ResponseBody
    public Object queryActivityForDetailByName(String activityName) {
        return activityService.selectActivityForDetailByName(activityName);
    }

    @RequestMapping("/queryContactsForDetailByName")
    @ResponseBody
    public Object queryContactsForDetailByName(String contactsName) {
        return "";
    }

}
