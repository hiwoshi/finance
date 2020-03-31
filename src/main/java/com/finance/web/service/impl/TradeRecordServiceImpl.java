package com.finance.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.web.entity.po.Position;
import com.finance.web.entity.po.TradeRecord;
import com.finance.web.mapper.PositionMapper;
import com.finance.web.mapper.TradeRecordMapper;
import com.finance.web.service.TradeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author : shenhao
 * @date : 2020/3/25 13:30
 */
@Service
public class TradeRecordServiceImpl extends ServiceImpl<TradeRecordMapper, TradeRecord> implements TradeRecordService {

    @Autowired
    private PositionMapper positionMapper;

    @Override
    public boolean save(TradeRecord entity) {// 在更新买入卖出时，同步更新持仓表
        boolean save = super.save(entity);
        // 整理计算持仓数量，新买入则新键，否则则更新持仓数量
        Position position = new Position();
        position.setUserId(entity.getUserId());
        position.setProductCode(entity.getProductCode());
        List<Position> positions = positionMapper.selectList(new QueryWrapper<>(position));
        // 新买入
        if (CollectionUtils.isEmpty(positions)) {
            position.setNumber(entity.getNumber());
            positionMapper.insert(position);
        }else {
            // 更新持仓数量
            positionMapper.updateNumber(entity.getUserId(),entity.getProductCode());
        }
        return save;
    }

    @Override
    public boolean updateById(TradeRecord entity){
        boolean b = super.updateById(entity);
        positionMapper.updateNumber(entity.getUserId(),entity.getProductCode());
        return b;
    }

    @Override
    public boolean removeById(Serializable id){
        TradeRecord entity = baseMapper.selectById(id);
        if (null == entity) return true;
        boolean b = super.removeById(id);
        positionMapper.updateNumber(entity.getUserId(),entity.getProductCode());
        return b;
    }


}
