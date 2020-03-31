package com.finance.web.entity.po;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author : shenhao
 * @date : 2020/3/25 10:13
 */
@Data
@ApiModel("用户表")
public class User {

    private Long id;
    private String name;
    private String password;
    private String type;

}
