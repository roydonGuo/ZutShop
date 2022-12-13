package edu.zut.controller;


import edu.zut.domain.ResponseResult;
import edu.zut.domain.dto.UserDto;
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

    @PostMapping("/register")
    public ResponseResult register(@RequestBody UserDto userDto) {
        return ResponseResult.okResult(userService.register(userDto));
    }

    @GetMapping("/page")
    public ResponseResult selectAll() {
        return ResponseResult.okResult(this.userService.list());
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody UserDto userDto) {
        return ResponseResult.okResult(userService.updatePwd(userDto));
    }

}

