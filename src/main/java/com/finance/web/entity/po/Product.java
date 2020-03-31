package com.finance.web.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : shenhao
 * @date : 2020/3/26 9:22
 */
@Data
@ApiModel("产品表")
public class Product {

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "产品名称")
    private String name;

    @ApiModelProperty(value = "产品类型")
    private String type;

    @ApiModelProperty(value = "产品编码")
    private String code;

    @ApiModelProperty(value = "产品当前净值")
    private Double price;

}
