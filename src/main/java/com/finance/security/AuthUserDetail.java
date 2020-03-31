package com.finance.security;

import com.finance.util.AESUtil;
import com.finance.util.LocalDateTimeUtils;
import com.finance.util.SpringContext;
import com.finance.web.entity.po.User;
import com.finance.web.service.UserService;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @author : shenhao
 * @date : 2019/11/15 11:07
 */
public class AuthUserDetail {

    private static UserService userService;
    private static final int DAYS = 7;

    private static UserService getUserService(){
        if (null == userService){
            userService = (UserService) SpringContext.getBean(UserService.class);
        }
        return userService;
    }

    public static UserDetail produce(User sysUser){
        sysUser.setPassword(null);
        String time = LocalDateTimeUtils.getPureDateTime(LocalDateTime.now());
        UserDetail userDetail = new UserDetail(sysUser.getId(),sysUser.getName(),time,sysUser);
        String content = time + sysUser.getId();
        try {
            String token = AESUtil.encrypt(content);
            userDetail.setAccessToken(token);
        } catch (Exception e) {
            throw new RuntimeException("生成token异常！");
        }
        return userDetail;
    }

    public static User checked(String accessToken) throws Exception {
        String decrypt = null;
        try {
            decrypt = AESUtil.decrypt(accessToken);
        } catch (Exception e) {
            throw new SecureException("Token 验证错误！" , e);
        }
        if (StringUtils.isEmpty(decrypt) || decrypt.length()<15)
            throw new SecureException("token错误");
        String time = decrypt.substring(0,14);
        if (isNotValid(time)) throw new SecureException("登陆token失效！");
        Long id = Long.parseLong(decrypt.substring(14));
        User sysUser = getUserService().getById(id);
        if (null == sysUser) throw new SecureException("当前账号异常！");
        return sysUser;
    }

    private static Boolean isNotValid(String time){
        LocalDateTime localDateTime = LocalDateTimeUtils.parsePureDateTime(time);
        return localDateTime.plusDays(DAYS).isBefore(LocalDateTime.now());
    }

}
