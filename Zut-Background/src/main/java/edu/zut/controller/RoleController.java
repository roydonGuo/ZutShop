package edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.Role;
import edu.zut.service.RoleService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/page")
    public ResponseResult page(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String name) {
        LambdaQueryWrapper<Role> lambda = new LambdaQueryWrapper<>();
        lambda.like(Role::getName, name);
        return ResponseResult.okResult(roleService.page(new Page<>(pageNum, pageSize), lambda));
    }

    @PostMapping
    public ResponseResult add(@RequestBody Role role) {
        return ResponseResult.okResult(roleService.saveOrUpdate(role));
    }

    @DeleteMapping("/del/{rid}")
    public ResponseResult deleteRole(@PathVariable Integer rid) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        //处于可用状态无法删除
        queryWrapper.ne(Role::getStatus, 0);
        queryWrapper.eq(Role::getRid, rid);
        return ResponseResult.okResult(roleService.remove(queryWrapper));
    }

}

