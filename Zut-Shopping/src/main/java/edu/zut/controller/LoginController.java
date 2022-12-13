package edu.zut.controller;

import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.User;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import edu.zut.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUsername())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @RequestMapping("/logout")
    @ApiOperation(value = "退出登录")
    public ResponseResult logout(){
        return loginService.logout();
    }


}