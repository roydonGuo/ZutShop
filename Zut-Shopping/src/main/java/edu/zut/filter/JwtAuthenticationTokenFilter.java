package edu.zut.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import edu.zut.domain.entity.LoginUser;
import edu.zut.utils.JwtUtil;
import edu.zut.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static edu.zut.constants.RedisConstants.LOGIN_USER_KEY;


@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        //解析获取userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            // token超时，或token非法
            e.printStackTrace();
//            ResponseResult responseResult = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
//            WebUtils.renderString(response, JSON.toJSONString(responseResult));
            return;
        }
        String userId = claims.getSubject();
        //从redis中获取用户信息
        LoginUser loginUser = redisCache.getCacheObject(LOGIN_USER_KEY + userId);
        //如果redis获取不到
        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息封装到 Authentication 中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

}