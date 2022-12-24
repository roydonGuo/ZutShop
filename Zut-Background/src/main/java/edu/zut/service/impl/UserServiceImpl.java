package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.entity.Role;
import edu.zut.domain.entity.User;
import edu.zut.domain.entity.UserRole;
import edu.zut.domain.vo.UserInfoVo;
import edu.zut.domain.vo.UserRoleVo;
import edu.zut.mapper.RoleMapper;
import edu.zut.mapper.UserMapper;
import edu.zut.service.UserRoleService;
import edu.zut.service.UserService;
import edu.zut.utils.BeanCopyUtils;
import edu.zut.utils.RedisCache;
import edu.zut.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author roydon
 * @since 2022-12-12 10:08:33
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfoVo getUserInfo(Integer uid) {
        //根据用户id查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(uid), User::getUid, uid);
        User user = getOne(queryWrapper);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return vo;
    }

    @Override
    public boolean updateUserInfo(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, user.getUid());
        return update(user, queryWrapper);
    }

    @Override
    public Integer setDeletedByUid(Integer uid) {
        return userMapper.setDeletedByUid(uid);

    }

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public Page<UserRoleVo> userRolePage(Integer pageNum, Integer pageSize, String username, String phone, String email) {

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(username), User::getUsername, username);
        queryWrapper.like(Strings.isNotEmpty(phone), User::getUsername, phone);
        queryWrapper.like(Strings.isNotEmpty(email), User::getUsername, email);
        queryWrapper.ne(User::getUid, SecurityUtils.getUserId());
        Page<User> userPage = page(new Page<>(pageNum, pageSize), queryWrapper);

        List<User> userList = userPage.getRecords();

        List<UserRoleVo> orderGoodVoList = new ArrayList<>();
        //封装用户权限
        userList.forEach(u -> {
            Integer uid = u.getUid();
            List<Role> userRoleList = roleMapper.getUserRoleList(uid);
            UserRoleVo userRoleVo = BeanCopyUtils.copyBean(u, UserRoleVo.class);
            userRoleVo.setRoleList(userRoleList);
            orderGoodVoList.add(userRoleVo);
        });

        Page<UserRoleVo> userRoleVoPage = new Page<>();
        userRoleVoPage.setCurrent(userPage.getCurrent());
        userRoleVoPage.setPages(userPage.getPages());
        userRoleVoPage.setSize(userPage.getSize());
        userRoleVoPage.setTotal(userPage.getTotal());

        userRoleVoPage.setRecords(orderGoodVoList);

        return userRoleVoPage;
    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean saveOrUpdateUser(User user) {

        //密码加密
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userMapper.insertUser(user);

        System.out.println(user);
        Integer uid = user.getUid();
        log.info("新增加的用户==>{}",uid);
        //增加普通权限
        userRoleService.save(new UserRole(null,uid,2));
//        userMapper.insertUserRole(uid,2);

        return true;
    }
}

