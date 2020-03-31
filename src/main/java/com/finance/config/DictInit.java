package com.finance.config;

import com.finance.web.entity.po.Dict;
import com.finance.web.service.DictService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : shenhao
 * @date : 2020/3/26 15:02
 */
@Component
public class DictInit implements InitializingBean, ServletContextAware {

    @Autowired
    private DictService dictService;

    public static Map<String,String> dictMap;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        List<Dict> list = dictService.list();
        dictMap = list.parallelStream().collect(Collectors.toMap(Dict::getDicKey,Dict::getDicValue,(prep,next)->next));
    }
}
