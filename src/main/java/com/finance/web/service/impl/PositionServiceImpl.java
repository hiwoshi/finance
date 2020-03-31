package com.finance.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.web.entity.po.Position;
import com.finance.web.mapper.PositionMapper;
import com.finance.web.service.PositionService;
import org.springframework.stereotype.Service;

/**
 * @author : shenhao
 * @date : 2020/3/25 13:30
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

}
