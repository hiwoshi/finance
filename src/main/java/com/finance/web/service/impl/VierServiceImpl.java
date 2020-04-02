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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return getOne(position,tradeRecords);
    }

    @Override
    public List<View> getViews(Long userId) {
        List<TradeRecord> tradeRecords = tradeRecordMapper.selectList(new QueryWrapper<>(new TradeRecord(userId)).orderByAsc("trade_date"));
        List<Position> positions = positionMapper.selectList(new QueryWrapper<>(new Position(userId)).ne("number",0));
        Map<String,List<TradeRecord>> map = new HashMap<>();
        tradeRecords.forEach(item -> {
            if (map.containsKey(item.getProductCode())){
                map.get(item.getProductCode()).add(item);
            }else {
                map.put(item.getProductCode(),new ArrayList<>());
                map.get(item.getProductCode()).add(item);
            }
        });
        List<View> result = new ArrayList<>();
        positions.forEach(position -> {
            if (position.getProductCode().startsWith("f_")){
                if (map.containsKey(position.getProductCode())){
                    View view = getOne(position,map.get(position.getProductCode()));
                    view.setPosition(position);
                    result.add(view);
                }
            }else{
                View view = new View();
                view.setPosition(position);
                result.add(view);
            }

        });
        return result;
    }

    private View getOne(Position position, List<TradeRecord> tradeRecords){
        if (position == null) return null;
        Double currentPrice = CurrentRate.getCurrentPrice(position.getProductCode());
        TradeRecord nowRecord = new TradeRecord();
        nowRecord.setPrice(currentPrice);
        nowRecord.setTotal(currentPrice * position.getNumber());
        nowRecord.setTradeDate(LocalDate.now());
        tradeRecords.add(nowRecord);
        Double[] result = RateCompute.allData(tradeRecords);
        return new View(position.getProductCode(), DictInit.dictMap.get(position.getProductCode()),result[0],result[1],currentPrice * position.getNumber());
    }
}
