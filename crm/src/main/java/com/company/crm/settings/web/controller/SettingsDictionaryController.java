package com.company.crm.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author conrad
 * @date 2021/06/27
 */
@Controller
public class SettingsDictionaryController {

    @RequestMapping("settings/dictionary/index.do")
    public String dictionaryIndex(){
        return "settings/dictionary/index";
    }
}
