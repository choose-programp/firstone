package com.company.crm.settings.web.interceptor;

import com.company.crm.commons.contants.Constants;
import com.company.crm.settings.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>NAME: LoginInterceptor</p>
 * @author Administrator
 * @date 2020-05-16 17:27:52
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //如果已经登录过，则放行
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        if (user == null) {
            String servletPath = httpServletRequest.getContextPath();
            httpServletResponse.sendRedirect(servletPath+"/settings/qx/user/toLogin.do");
            return false;
        }
        return true;
    }


}
