package edu.zut.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import edu.zut.domain.ResponseResult;
import edu.zut.domain.entity.LoginUser;
import edu.zut.domain.entity.User;
import edu.zut.domain.vo.UserInfoVo;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.exception.SystemException;
import edu.zut.service.AdminLoginService;
import edu.zut.utils.BeanCopyUtils;
import edu.zut.utils.JwtUtil;
import edu.zut.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

import static edu.zut.constants.RedisConstants.LOGIN_USER_KEY;

/**
 * Author: roydon - 2022/10/10
 **/
@Slf4j
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    /**
     * 登录
     *
     * @param user
     * @return ResponseResult.okResult(blogUserLoginVo)
     */
    @Override
    public ResponseResult login(User user) {

        if (StringUtils.isEmpty(user.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //判断是否认证通过
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 认证成功，从Authentication获取LoginUser
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        log.info("loginUser:{}", loginUser);

        String userId = loginUser.getUser().getUid().toString();
        // 生成token
        String jwt = JwtUtil.createJWT(userId);
        // 存入redis
        redisCache.setCacheObject(LOGIN_USER_KEY + userId, loginUser);

        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        log.info("当前登录用户：{}", userInfoVo);

        return ResponseResult.okResult(userInfoVo);
    }


}
