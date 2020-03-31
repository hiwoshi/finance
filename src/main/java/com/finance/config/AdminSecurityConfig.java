package com.finance.config;

import com.alibaba.fastjson.JSONObject;
import com.finance.common.R;
import com.finance.interfaces.Inner;
import com.finance.security.AuthUserDetail;
import com.finance.web.entity.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : shenhao
 * @date : 2019/10/8 14:46
 */
@Configuration
@Slf4j
public class AdminSecurityConfig implements WebMvcConfigurer {

    @Value("${background.errorLog:false}")
    private boolean errorLog;

    public final static String SESSION_KEY = "userInfo";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
        //排除的路径
        addInterceptor.excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/error");

        //拦截所有路径
        addInterceptor.addPathPatterns("/**");
        addInterceptor.order(9);
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            log.info("AdminSecurityConfig请求的url：{}",request.getRequestURI());
            // 当前方法是否存在注解
            boolean isAssignableHandlerMethod = handler.getClass().isAssignableFrom(HandlerMethod.class);
            if (isAssignableHandlerMethod){
                Inner inner = ((HandlerMethod) handler).getMethodAnnotation(Inner.class);
                if (inner == null || inner.value()){
                    String accessToken = request.getHeader("authorization");
                    try {
                        User sysUser = AuthUserDetail.checked(accessToken);
                        request.getSession(true).setAttribute(SESSION_KEY,sysUser);
                    } catch (Exception e) {
                        response.setStatus(401);
                        response.setContentType("text/html;charset=UTF-8");
                        try (PrintWriter writer = response.getWriter()){
                            writer.print(JSONObject.toJSONString(R.error(e.getMessage())));
                            writer.flush();
                        }
//                        if (errorLog) log.error("验证异常:",e);
                        return false;
                    }
                }
            }
            return true;

        }
    }

}
