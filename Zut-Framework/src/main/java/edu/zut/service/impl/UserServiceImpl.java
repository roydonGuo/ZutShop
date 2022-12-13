package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.dto.UserDto;
import edu.zut.domain.entity.User;
import edu.zut.domain.vo.UserInfoVo;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import edu.zut.mapper.UserMapper;
import edu.zut.service.UserService;
import edu.zut.utils.BeanCopyUtils;
import edu.zut.utils.SecurityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-12-12 10:08:33
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        //根据用户id查询用户信息
        User user = getById(SecurityUtils.getUserId());
        //封装成UserInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        return ResponseResult.okResult(updateById(user));
    }

    @Resource
    private PasswordEncoder passwordEncoder;

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
        save(BeanCopyUtils.copyBean(userDto,User.class));
        return ResponseResult.okResult();
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, userName);
        return count(queryWrapper) > 0;
    }

}

