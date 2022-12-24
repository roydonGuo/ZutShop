package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.dto.UserDto;
import edu.zut.domain.entity.LoginUser;
import edu.zut.domain.entity.User;
import edu.zut.domain.entity.UserRole;
import edu.zut.domain.vo.UserInfoVo;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import edu.zut.mapper.UserMapper;
import edu.zut.service.UserRoleService;
import edu.zut.service.UserService;
import edu.zut.utils.BeanCopyUtils;
import edu.zut.utils.RedisCache;
import edu.zut.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Objects;

import static edu.zut.constants.RedisConstants.LOGIN_USER_KEY;
import static edu.zut.enums.AppHttpCodeEnum.NEED_LOGIN;

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

    @Override
    public ResponseResult getUserInfo(Integer uid) {
        //根据用户id查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(uid), User::getUid, uid);
        User user = getOne(queryWrapper);
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, user.getUid());
        redisCache.setCacheObject(LOGIN_USER_KEY + user.getUid(), new LoginUser(user));
        return ResponseResult.okResult(update(user, queryWrapper));
    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleService userRoleService;

    @Override
    public ResponseResult register(UserDto userDto) {
        //非空判断
        if (StringUtils.isEmpty(userDto.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (StringUtils.isEmpty(userDto.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        //数据库中是否存在此用户名
        if (userNameExist(userDto.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        //密码加密
        String encodePassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePassword);
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        userMapper.insert(user);
        //TODO 添加普通用户权限
        Integer insertUserUid = user.getUid();
        userRoleService.save(new UserRole(null, insertUserUid, 2));
        return ResponseResult.okResult();
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userName);
        return count(queryWrapper) > 0;
    }

    @Override
    public ResponseResult updatePwd(UserDto userDto) {
        //非空判断
        if (StringUtils.isEmpty(userDto.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        //取出登录用户的id
        Integer userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            //未登录
            throw new SystemException(NEED_LOGIN);
        }
        if (Objects.isNull(userId)) {
            //没有携带token
            throw new SystemException(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        }
        //查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, userId);
        User one = getOne(queryWrapper);
        //判断输入密码是否与数据库相同
        boolean matches = passwordEncoder.matches(userDto.getPassword(), one.getPassword());
        if (!matches) {
            //不存在用户
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_MATCH);
        }
        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(LOGIN_USER_KEY + userId);
        //如果redis获取不到
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        //新密码加密
        String encodePassword = passwordEncoder.encode(userDto.getNewPassword());
        User user = loginUser.getUser();
        user.setPassword(encodePassword);
        log.info("修改后的用户：{}", user);
        redisCache.setCacheObject(LOGIN_USER_KEY + userId, new LoginUser(user));
        return ResponseResult.okResult(update(user, queryWrapper));
    }
}

