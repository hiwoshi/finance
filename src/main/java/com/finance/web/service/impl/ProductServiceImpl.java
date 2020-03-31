package com.finance.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.finance.web.entity.po.Product;
import com.finance.web.mapper.ProductMapper;
import com.finance.web.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author : shenhao
 * @date : 2020/3/25 13:30
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
