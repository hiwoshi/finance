package com.finance.web.controller;

import com.finance.common.BaseController;
import com.finance.web.entity.po.TradeRecord;
import com.finance.web.service.TradeRecordService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : shenhao
 * @date : 2020/3/25 14:07
 */
@RestController
@RequestMapping("/tradeRecord")
@Api(value = "交易记录表", tags = "交易记录表")
@Slf4j
public class TradeRecordController extends BaseController<TradeRecordService, TradeRecord> {

}
