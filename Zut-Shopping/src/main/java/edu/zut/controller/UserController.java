package edu.zut.controller;


import edu.zut.domain.ResponseResult;
import edu.zut.domain.dto.UserDto;
import edu.zut.domain.entity.User;
import edu.zut.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author roydon
 * @since 2022-12-12 10:08:26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userDto
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody UserDto userDto) {
        return ResponseResult.okResult(userService.register(userDto));
    }

    /**
     * 根据uid查询用户信息
     *
     * @param uid
     * @return
     */
    @GetMapping("/{uid}")
    public ResponseResult findOne(@PathVariable Integer uid) {
        return ResponseResult.okResult(userService.getUserInfo(uid));
    }

    /**
     * 修改密码
     *
     * @param userDto
     * @return
     */
    @PostMapping("/password")
    public ResponseResult update(@RequestBody UserDto userDto) {
        return ResponseResult.okResult(userService.updatePwd(userDto));
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public ResponseResult updateUser(@RequestBody User user) {
        return ResponseResult.okResult(userService.updateUserInfo(user));
    }


}

