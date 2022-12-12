package edu.zut.service;

import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.User;

public interface AdminLoginService {
    ResponseResult login(User user);

}
