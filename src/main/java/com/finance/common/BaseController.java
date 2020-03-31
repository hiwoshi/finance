package com.finance.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author : shenhao
 * @date : 2020/3/25 14:20
 */
public class BaseController<S extends IService<P>,P> {

    @Autowired
    protected S service;

    @PostMapping("/add")
    @ApiOperation(value = "新增数据", notes = "新增数据")
    @ResponseBody
    @CrossOrigin
    public R add(@RequestBody @Valid P p){
        service.save(p);
        return R.success("ok");
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新数据", notes = "更新数据")
    @ResponseBody
    @CrossOrigin
    public R update(@RequestBody @Valid P p){
        service.updateById(p);
        return R.success("ok");
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @ResponseBody
    @CrossOrigin
    public R delete(@PathVariable Long id){
        service.removeById(id);
        return R.success("ok");
    }

    @GetMapping("/findall")
    @ApiOperation(value = "查询所有", notes = "查询所有")
    @ResponseBody
    @CrossOrigin
    public R findall(){
        List result = service.list();
        return R.success(result);
    }

    @GetMapping("/findByPage")
    @ApiOperation(value = "分页条件查询", notes = "分页条件查询")
    @ResponseBody
    @CrossOrigin
    public R findByPage(P entity, Page<P> page){
        return R.success(service.page(page,new QueryWrapper<>(entity)));
    }

    @GetMapping("/findByExample")
    @ApiOperation(value = "条件查询", notes = "条件查询")
    @ResponseBody
    @CrossOrigin
    public R findByPage(P entity){
        return R.success(service.list(new QueryWrapper<>(entity)));
    }

    @GetMapping("/findById")
    @ApiOperation(value = "依据id查询", notes = "依据id查询")
    @ResponseBody
    @CrossOrigin
    public R findById(Long id){
        return R.success(service.getById(id));
    }

}
