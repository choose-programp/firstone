package com.company.crm.settings.service.impl;

import basetest.MyBaseTest;
import com.company.crm.commons.utils.MD5Util;
import com.company.crm.settings.domain.User;
import com.company.crm.settings.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImplTest extends MyBaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void selectUserByLoginActAndPwd() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("loginAct", "ls");
        String pass = MD5Util.getMD5("ls");
        map.put("loginPwd", pass);
        User user = userService.selectUserByLoginActAndPwd(map);
        System.out.println(user.getName());
    }

    @Test
    public void selectAllUsers() {
        List<User> users = userService.selectAllUsers();
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectAllUsers() {

    }

    @Test
    public void testSelectUserByLoginActAndPwd() {
    }
}