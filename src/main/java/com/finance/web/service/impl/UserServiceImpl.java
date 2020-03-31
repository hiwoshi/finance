package com.finance.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.web.entity.po.User;
import com.finance.web.mapper.UserMapper;
import com.finance.web.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author : shenhao
 * @date : 2020/3/25 13:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(String name, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return baseMapper.selectOne(new QueryWrapper<>(user));
    }
}
