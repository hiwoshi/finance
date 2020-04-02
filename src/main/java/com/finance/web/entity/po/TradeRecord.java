package com.finance.web.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : shenhao
 * @date : 2020/3/25 10:15
 */
@Data
@ApiModel("交易记录表")
@NoArgsConstructor
@AllArgsConstructor
public class TradeRecord {

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty(value = "交易日期")
    private LocalDate tradeDate;

    @ApiModelProperty(value = "交易类型，买入/卖出")
    private String type;

    @ApiModelProperty(value = "单价")
    private Double price;

    @ApiModelProperty(value = "数量")
    private Double number;

    @ApiModelProperty(value = "交易税")
    private Double tax;

    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value = "总计")
    private Double total;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    public TradeRecord(String productCode, Long userId) {
        this.productCode = productCode;
        this.userId = userId;
    }

    public TradeRecord(Long userId) {
        this.userId = userId;
    }
}
