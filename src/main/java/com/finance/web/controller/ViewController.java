package com.finance.web.controller;

import com.finance.common.R;
import com.finance.util.UserInfo;
import com.finance.web.entity.po.User;
import com.finance.web.service.ViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : shenhao
 * @date : 2020/3/26 15:52
 */
@RestController
@RequestMapping("/view")
@Api(value = "视图表", tags = "视图表，获取视图")
@Slf4j
public class ViewController {

    @Autowired
    private ViewService viewService;

    @PostMapping("/getOne")
    @ApiOperation(value = "获取单个基金收益", notes = "获取单个基金收益")
    @CrossOrigin
    public R getOne(String productCode, HttpServletRequest request) {
        if (StringUtils.isEmpty(productCode)) return R.error("请求参数缺失");
        User user = UserInfo.getUser(request);
        return R.success(viewService.getView(productCode,user.getId()));
    }

}
