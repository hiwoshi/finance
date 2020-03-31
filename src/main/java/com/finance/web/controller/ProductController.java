package com.finance.web.controller;

import com.finance.common.BaseController;
import com.finance.web.entity.po.Product;
import com.finance.web.service.ProductService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : shenhao
 * @date : 2020/3/25 14:07
 */
@RestController
@RequestMapping("/product")
@Api(value = "产品表", tags = "产品表")
@Slf4j
public class ProductController extends BaseController<ProductService, Product> {

}
