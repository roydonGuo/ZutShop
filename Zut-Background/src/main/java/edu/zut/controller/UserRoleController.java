package edu.zut.controller;

import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.UserRole;
import edu.zut.service.UserRoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (UserRole)表控制层
 *
 * @author roydon
 * @since 2022-12-23 21:41:39
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController  {

    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/add")
    public ResponseResult add(@RequestBody UserRole userRole){
        return ResponseResult.okResult(userRoleService.addUserRole(userRole));
    }

}

