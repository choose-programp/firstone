package com.company.crm.workbench.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author conrad
 * @date 2021/06/26
 */
@Controller
@RequestMapping("/workbench")
public class WorkbenchController {

    @RequestMapping("/index.do")
    public String index(){
        //jetbrains://idea/navigate/reference?project=crm&path=WEB-INF/pages/workbench/index.jsp
        return "workbench/index";
    }

    @RequestMapping("/main/index")
    public String toIndex(){
        return "workbench/main/index";
    }






}
