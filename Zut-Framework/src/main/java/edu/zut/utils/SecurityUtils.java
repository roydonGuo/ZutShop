package edu.zut.utils;

import edu.zut.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
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