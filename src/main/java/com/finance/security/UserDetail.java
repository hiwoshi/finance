package com.finance.security;

import com.finance.web.entity.po.User;
import lombok.Data;

/**
 * @author : shenhao
 * @date : 2019/11/15 10:14
 */
@Data
public class UserDetail {

    private Long userId;
    private String userName;
    private String accessToken;
    private String loginTime;
    private User sysUser;

    public UserDetail() {
    }

    public UserDetail(Long userId, String userName, String loginTime, User sysUser) {
        this.userId = userId;
        this.userName = userName;
        this.loginTime = loginTime;
        this.sysUser = sysUser;
    }
}
