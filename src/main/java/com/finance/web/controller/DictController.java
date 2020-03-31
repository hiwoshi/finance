package com.finance.web.controller;

import com.finance.common.R;
import com.finance.config.DictInit;
import com.finance.web.entity.po.Dict;
import com.finance.web.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : shenhao
 * @date : 2020/3/25 14:07
 */
@RestController
@RequestMapping("/dict")
@Api(value = "字典表", tags = "字典表")
@Slf4j
public class DictController{

    @Autowired
    DictService service;

    @PostMapping("/add")
    @ApiOperation(value = "新增数据", notes = "新增数据")
    @ResponseBody
    @CrossOrigin
    public R add(@RequestBody @Valid Dict p){
        service.save(p);
        DictInit.dictMap.put(p.getDicKey(),p.getDicValue());
        return R.success("ok");
    }

}
