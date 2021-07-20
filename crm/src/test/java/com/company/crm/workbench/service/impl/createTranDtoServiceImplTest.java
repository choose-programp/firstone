package com.company.crm.workbench.service.impl;

import basetest.MyBaseTest;
import com.company.crm.workbench.service.TranService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

public class createTranDtoServiceImplTest extends MyBaseTest {

    @Qualifier("tranServiceImpl")
    @Autowired
    private TranService tranService;

    @Test
    public void readProperties() throws IOException {
        String s = tranService.readProperties("05提案/报价");
        System.out.println(s);
    }
}