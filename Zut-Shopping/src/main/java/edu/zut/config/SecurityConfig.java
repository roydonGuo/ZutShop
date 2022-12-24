package edu.zut.config;

import edu.zut.filter.JwtAuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Security配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * BCryptPasswordEncoder加密
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * token拦截器
     */
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    /**
     * 自定义失败处理
     * 认证过程中出现异常
     */
    @Resource
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * 自定义失败处理
     * 授权过程中出现异常
     */
    @Resource
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// 关闭csrf
        http
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口、注册接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
//                .antMatchers("/alipay/pay").anonymous()
//                .antMatchers("/alipay/notify").anonymous()
                //注销接口需要认证才能访问
                .antMatchers("/user/logout").authenticated()
                .antMatchers("/user/update").authenticated()
//                .antMatchers("/address/*").authenticated()
//                .antMatchers("/cart/*").authenticated()
//                .antMatchers("/order/*").authenticated()
//                .antMatchers("/orderItem/*").authenticated()
                // 除上面外的所有请求全部不需要认证即可访问
                .anyRequest().permitAll();

        // 过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理器
        http.exceptionHandling()
                // 认证失败处理
                .authenticationEntryPoint(authenticationEntryPoint)
                // 授权失败
                .accessDeniedHandler(accessDeniedHandler);

        //关闭默认的注销功能
        http.logout().disable();
        //允许跨域
        http.cors();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
