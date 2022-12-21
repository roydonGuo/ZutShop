package edu.zut.controller;

import edu.zut.service.RoleService;
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
public class RoleController{

    @Resource
    private RoleService roleService;

}

