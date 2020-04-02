package com.finance.web.entity.vo;

import com.finance.web.entity.po.Position;
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

    @ApiModelProperty(value = "现投入")
    private Double current;

    public View(String productCode, String productName, Double rate, Double income, Double current) {
        this.productCode = productCode;
        this.productName = productName;
        this.rate = rate;
        this.income = income;
        this.current = current;
    }

    private Position position;

}
