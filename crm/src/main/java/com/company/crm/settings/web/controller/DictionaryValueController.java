package com.company.crm.settings.web.controller;

import com.company.crm.commons.domain.ResponseObject;
import com.company.crm.commons.utils.UUIDUtils;
import com.company.crm.settings.domain.DicType;
import com.company.crm.settings.domain.DicValue;
import com.company.crm.settings.service.DicTypeService;
import com.company.crm.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author conrad
 * @date 2021/06/27
 */
@Controller
@RequestMapping("settings/dictionary/value")
public class DictionaryValueController {

    @Autowired
    private DicValueService dicValueService;

    @Autowired
    private DicTypeService dicTypeService;

    @RequestMapping("/index")
    public String index(ModelMap map) {
        List<DicValue> dicValueList = dicValueService.selectAllDicValues();
        map.addAttribute("dicValueList", dicValueList);
        return "settings/dictionary/value/index";
    }

    @RequestMapping("/toSave")
    public String toSave(ModelMap map){
        List<DicType> dicTypeList = dicTypeService.selectAllDicTypes();
        map.addAttribute("dicTypeList", dicTypeList);
        return "settings/dictionary/value/save";
    }

    @RequestMapping("/saveCreateDicValue")
    @ResponseBody
    public Object saveCreateDicValue(DicValue dicValue){

        dicValue.setId(UUIDUtils.getUUID());
        int i = dicValueService.saveDicValue(dicValue);
        if ( i == 0){
            return ResponseObject.error("新增失败");
        }
        return ResponseObject.success();
    }

    @RequestMapping("/editDicValue")
    public String editDicValue(String id, ModelMap map){
        DicValue dicValue = dicValueService.selectDicValueById(id);
        map.addAttribute("dicValue", dicValue);
        return "settings/dictionary/value/edit";
    }

    @RequestMapping("/saveEditDicValue")
    @ResponseBody
    public Object saveEditDicValue(DicValue dicValue){
        int i = dicValueService.updateDicValue(dicValue);
        if ( i == 0){
            return ResponseObject.error("更新失败");
        }
        return ResponseObject.success();
    }

    @RequestMapping("/deleteDicValueByIds")
    @ResponseBody
    public Object deleteDicValueByIds(String[] id){
        int i = dicValueService.deleteDicValueByIds(id);
        if ( i == 0){
            return ResponseObject.error("删除失败");
        }
        return ResponseObject.success();
    }

}
