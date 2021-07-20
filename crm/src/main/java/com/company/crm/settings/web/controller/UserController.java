package com.company.crm.settings.web.controller;

import com.company.crm.commons.contants.Constants;
import com.company.crm.commons.domain.ResponseObject;
import com.company.crm.commons.utils.DateUtils;
import com.company.crm.commons.utils.MD5Util;
import com.company.crm.settings.domain.User;
import com.company.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hp
 */
@Controller
@RequestMapping("settings/qx/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/toLogin.do")
    public String index(HttpServletRequest request) {
        User loginUser = (User) request.getSession().getAttribute(Constants.SESSION_USER);
        if (loginUser != null) {
            return "redirect:/workbench/index.do";
        }
        Cookie[] cookies = request.getCookies();
        String loginAct = null;
        String loginPwd = null;
        for (Cookie cookie : cookies) {
            if ("loginAct".equals(cookie.getName())) {
                loginAct = cookie.getValue();
                continue;
            }
            if ("loginPwd".equals(cookie.getName())) {
                loginPwd = cookie.getValue();
            }
        }
        if (loginAct != null && loginPwd != null) {
            Map<String, Object> map = new HashMap<>(8);
            map.put("loginAct", loginAct);
            map.put("loginPwd", MD5Util.getMD5(loginPwd));
            User user = userService.selectUserByLoginActAndPwd(map);
            if (user != null) {
                request.getSession().setAttribute(Constants.SESSION_USER, user);
                return "redirect:/workbench/index.do";
            }
        }

        /*
        /WEB-INF/pages/

        /WEB-INF/pages/settings/qx/user/login.jsp

         */
        return "settings/qx/user/login";
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        if (loginAct == null || loginPwd == null) {
            return ResponseObject.error("用户名和密码不能为空");
        }
        String time = DateUtils.formatDateTime(new Date());
        Map<String, Object> map = new HashMap<>(8);
        map.put("loginAct", loginAct);
        map.put("loginPwd", MD5Util.getMD5(loginPwd));
        User user = userService.selectUserByLoginActAndPwd(map);
        if (user == null) {
            return ResponseObject.error("用户名或密码错误");
        }
        System.out.println(request.getRemoteAddr());
        if (user.getLockState().equals(Constants.USER_LOCK_STATE)) {
            return ResponseObject.error("用户已禁用");
        } else if (user.getExpireTime().compareTo(time) < 0) {
            return ResponseObject.error("用户已过期");
        } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
            return ResponseObject.error("ip不允许访问");
        }
        System.out.println(isRemPwd);
        if (Constants.COOKIES_SET.equals(isRemPwd)) {
            Cookie name = new Cookie("loginAct", loginAct);
            name.setMaxAge(60 * 60 * 24 * 10);
            Cookie pass = new Cookie("loginPwd", loginPwd);
            pass.setMaxAge(60 * 60 * 24 * 10);
            response.addCookie(name);
            response.addCookie(pass);
        }
        session.setAttribute(Constants.SESSION_USER, user);
        return ResponseObject.success(user);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie name = new Cookie("loginAct", null);
        name.setMaxAge(0);
        Cookie pass = new Cookie("loginPwd", null);
        pass.setMaxAge(0);
        response.addCookie(name);
        response.addCookie(pass);
        return "forward:/";
    }


}
