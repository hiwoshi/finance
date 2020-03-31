package com.finance.web.controller;

import com.finance.common.BaseController;
import com.finance.common.R;
import com.finance.interfaces.Inner;
import com.finance.security.AuthUserDetail;
import com.finance.security.UserDetail;
import com.finance.web.entity.po.User;
import com.finance.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author : shenhao
 * @date : 2020/3/25 14:07
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户菜单", tags = "用户菜单")
@Slf4j
public class UserController extends BaseController<UserService, User> {

    @PostMapping("/login")
    @ApiOperation(value = "登陆用户", notes = "登陆用户")
    @Inner(value = false)
    @CrossOrigin
    public R login(@RequestBody User user) {
        User sysUser = service.login(user.getName(),user.getPassword());
        if (null == sysUser) return R.error("账号名或密码错误");
        UserDetail userDetail = AuthUserDetail.produce(sysUser);
        return R.success(userDetail);
    }

}
