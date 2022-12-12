package edu.zut.controller;


import edu.zut.domain.ResponseResult;
import edu.zut.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/page")
    public ResponseResult selectAll() {
        return ResponseResult.okResult(this.userService.list());
    }

}

