package com.finance.web.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : shenhao
 * @date : 2020/3/26 9:24
 */
@Data
@ApiModel("字典表")
public class Dict {

    @ApiModelProperty(value = "键")
    private String dicKey;

    @ApiModelProperty(value = "值")
    private String dicValue;

    @ApiModelProperty(value = "备注")
    private String mark;

}
