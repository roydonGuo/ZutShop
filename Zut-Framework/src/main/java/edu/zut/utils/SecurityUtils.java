package edu.zut.utils;

import edu.zut.domain.entity.LoginUser;
import edu.zut.exception.SystemException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static edu.zut.enums.AppHttpCodeEnum.NEED_LOGIN;

public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        LoginUser loginUser = null;
        try {
            loginUser =  (LoginUser) getAuthentication().getPrincipal();
        }catch (Exception e) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
         return loginUser;
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        Integer id = getLoginUser().getUser().getUid();
        return id != null && id.equals(1);
    }

    public static Integer getUserId() {
        return getLoginUser().getUser().getUid();
    }
}