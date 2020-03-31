package com.finance.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finance.web.entity.po.Position;
import org.apache.ibatis.annotations.Param;

/**
 * @author : shenhao
 * @date : 2020/3/25 10:35
 */
public interface PositionMapper extends BaseMapper<Position> {

    /**
     * 依据交易记录表，更新用户持仓记录
     * @param userId 用户id
     * @param productCode 产品编码
     * @return 更新条数
     */
    int updateNumber(@Param("userId") Long userId, @Param("productCode") String productCode);

}
