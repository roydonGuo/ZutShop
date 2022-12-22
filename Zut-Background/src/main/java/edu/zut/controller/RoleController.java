package edu.zut.controller;

import edu.zut.domain.ResponseResult;
import edu.zut.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Role)表控制层
 *
 * @author roydon
 * @since 2022-12-21 23:15:58
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 获取所有权限
     *
     * @return
     */
    @GetMapping
    public ResponseResult findAll() {
        return ResponseResult.okResult(roleService.list());
    }

}

