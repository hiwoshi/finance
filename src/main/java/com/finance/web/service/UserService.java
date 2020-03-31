package com.finance.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.finance.web.entity.po.User;

/**
 * @author : shenhao
 * @date : 2020/3/25 13:29
 */
public interface UserService extends IService<User> {
    User login(String name, String password);
}
