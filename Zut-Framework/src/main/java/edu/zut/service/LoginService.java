package edu.zut.service;

import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.User;

/**
 * 登录服务
 */
public interface LoginService {
    ResponseResult login(User user);
    ResponseResult logout();
}
