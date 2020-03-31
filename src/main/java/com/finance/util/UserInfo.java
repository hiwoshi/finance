package com.finance.util;

import com.finance.config.AdminSecurityConfig;
import com.finance.web.entity.po.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : shenhao
 * @date : 2020/3/27 9:51
 */
public class UserInfo {

    public static User getUser(HttpServletRequest request){
        return (User) request.getSession().getAttribute(AdminSecurityConfig.SESSION_KEY);
    }

}
