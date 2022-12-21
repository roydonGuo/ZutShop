package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import edu.zut.constants.AppHttpCodeEnum;
import edu.zut.domain.entity.LoginUser;
import edu.zut.domain.entity.User;
import edu.zut.exception.SystemException;
import edu.zut.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static edu.zut.constants.SystemConstants.IS_DELETED;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // 方法引用
        queryWrapper.eq(StringUtils.isNotEmpty(username),User::getUsername,username);

        User user = userMapper.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //判断用户是否被删除
        if (Objects.equals(user.getIsDelete(), IS_DELETED)) {
            throw new SystemException(AppHttpCodeEnum.USER_IS_DELETED);
        }

        log.info("数据库登录用户：{}",user);
        //TODO 查询角色权限
        List<String> permissions = userMapper.selectRoleByUid(user.getUid());
        log.info("当前登录用户：{}；拥有权限：{}",user.getUsername(),permissions);

        return new LoginUser(user,permissions);
    }

}
