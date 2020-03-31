package com.finance.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author : shenhao
 * @date : 2019/11/15 11:29
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static Object  getBean(Class clazz) {
        return applicationContext != null?applicationContext.getBean(clazz):null;
    }

    public static Object  getBean(String name) {
        return applicationContext != null?applicationContext.getBean(name):null;
    }
}
