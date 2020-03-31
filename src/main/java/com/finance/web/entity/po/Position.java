package com.finance.web.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : shenhao
 * @date : 2020/3/26 9:24
 */
@Data
@ApiModel("持仓表")
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "产品编码")
    private String productCode;

    @ApiModelProperty(value = "份额")
    private Double number;

    public Position(Long userId, String productCode) {
        this.userId = userId;
        this.productCode = productCode;
    }
}
