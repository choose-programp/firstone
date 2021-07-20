package com.company.crm.settings.web.controller;

import com.company.crm.commons.domain.ResponseObject;
import com.company.crm.settings.domain.DicType;
import com.company.crm.settings.service.DicTypeService;
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
@RequestMapping("settings/dictionary/type")
public class DictionaryController {

    @Autowired
    private DicTypeService dicTypeService;

    @RequestMapping("/index")
    public String index(ModelMap map) {
        List<DicType> dicTypeList = dicTypeService.selectAllDicTypes();
        map.addAttribute("dicTypeList", dicTypeList);
        return "settings/dictionary/type/index";
    }

    @RequestMapping("/toSave")
    public String toSave() {
        return "settings/dictionary/type/save";
    }

    @RequestMapping("/checkCode")
    @ResponseBody
    public Object checkCode(String code) {
        DicType dicType = dicTypeService.selectDicTypeByCode(code);
        if (dicType != null) {
            return ResponseObject.error("编码重复");
        }
        return ResponseObject.success();
    }

    @RequestMapping("/saveCreateDicType")
    @ResponseBody
    public Object saveCreateDicType(DicType dicType) {
        int i = dicTypeService.saveDicType(dicType);
        if (i == 0) {
            return ResponseObject.error("新增失败");
        }
        return ResponseObject.success();
    }

    @RequestMapping("/editDicType")
    public String editDicType(ModelMap map, String code) {
        DicType dicType = dicTypeService.selectDicTypeByCode(code);
        map.addAttribute("dicType", dicType);
        return "settings/dictionary/type/edit";
    }

    @RequestMapping("/saveEditDicType")
    @ResponseBody
    public Object ee(DicType dicType){
        int i = dicTypeService.updateDicType(dicType);
        if (i == 0){
            return ResponseObject.error("修改失败");
        }
        return ResponseObject.success();
    }


    @RequestMapping("/deleteDicTypeByCodes")
    @ResponseBody
    public Object deleteDicTypeByCodes(String[] code){
        int i = dicTypeService.deleteDicTypeByCodes(code);
        if (i == 0){
            return ResponseObject.error("删除失败");
        }
        return ResponseObject.success();
    }




}
