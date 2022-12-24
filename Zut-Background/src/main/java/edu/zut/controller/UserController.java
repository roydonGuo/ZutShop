package edu.zut.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.User;
import edu.zut.domain.entity.UserRole;
import edu.zut.service.UserRoleService;
import edu.zut.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/username/{username}")
    public ResponseResult userInfo(@PathVariable String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return ResponseResult.okResult(userService.getOne(queryWrapper));
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
     * 新增或者更新
     *
     * @param user
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addUser(@RequestBody User user) {
        return ResponseResult.okResult(userService.saveOrUpdateUser(user));
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

    @GetMapping("/page")
    public ResponseResult findPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String username,
                                   @RequestParam(defaultValue = "") String phone,
                                   @RequestParam(defaultValue = "") String email) {
        return ResponseResult.okResult(userService.userRolePage(pageNum, pageSize, username, phone, email));
    }

    /**
     * 把用户设置为删除状态
     *
     * @param uid
     * @return
     */
    @DeleteMapping("/{uid}")
    public ResponseResult setDeleted(@PathVariable Integer uid) {
        return ResponseResult.okResult(userService.setDeletedByUid(uid));
    }

    @Resource
    private UserRoleService userRoleService;

    /**
     * 根据用户id删除
     *
     * @param uid
     * @return
     */
    @DeleteMapping("/del/{uid}")
    public ResponseResult deleteUser(@PathVariable Integer uid) {
        //删除用户权限
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserRole::getUid, uid);
        userRoleService.remove(queryWrapper);
        return ResponseResult.okResult(userService.removeById(uid));
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/del/batch")
    public ResponseResult deleteBatch(@RequestBody List<Integer> ids) {
        return ResponseResult.okResult(userService.removeByIds(ids));
    }


}

