package com.finance.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finance.config.DictInit;
import com.finance.util.tradeutil.CurrentRate;
import com.finance.util.tradeutil.RateCompute;
import com.finance.web.entity.po.Position;
import com.finance.web.entity.po.TradeRecord;
import com.finance.web.entity.vo.View;
import com.finance.web.mapper.PositionMapper;
import com.finance.web.mapper.TradeRecordMapper;
import com.finance.web.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : shenhao
 * @date : 2020/3/26 15:53
 */
@Service
public class VierServiceImpl implements ViewService {

    @Autowired
    private TradeRecordMapper tradeRecordMapper;
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public View getView(String productCode, Long userId) {
        List<TradeRecord> tradeRecords = tradeRecordMapper.selectList(new QueryWrapper<>(new TradeRecord(productCode, userId)).orderByAsc("trade_date"));
        Position position = positionMapper.selectOne(new QueryWrapper<>(new Position(userId, productCode)));
        if (position == null) return null;
        Double currentPrice = CurrentRate.getCurrentPrice(position.getProductCode());
        TradeRecord nowRecord = new TradeRecord();
        nowRecord.setPrice(currentPrice);
        nowRecord.setTotal(currentPrice * position.getNumber());
        nowRecord.setTradeDate(LocalDate.now());
        tradeRecords.add(nowRecord);
        Double[] result = RateCompute.allData(tradeRecords);
        View view = new View(position.getProductCode(), DictInit.dictMap.get(position.getProductCode()),result[0],result[1]);
        return view;
    }
}
