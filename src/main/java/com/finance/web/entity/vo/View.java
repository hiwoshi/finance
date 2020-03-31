package com.finance.web.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : shenhao
 * @date : 2020/3/27 9:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class View {

    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value = "产品名称")
    private String productName;

    @ApiModelProperty(value = "年均收益率")
    private Double rate;

    @ApiModelProperty(value = "总收入")
    private Double income;

}
