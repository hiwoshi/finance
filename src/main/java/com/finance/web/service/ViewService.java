package com.finance.web.service;

import com.finance.web.entity.vo.View;

/**
 * @author : shenhao
 * @date : 2020/3/26 15:53
 */
public interface ViewService {

    View getView(String productCode, Long userId);

}
