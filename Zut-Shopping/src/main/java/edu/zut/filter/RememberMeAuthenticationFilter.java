//package edu.zut.filter;
//
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.core.log.LogMessage;
//import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.IOException;
//
//public class RememberMeAuthenticationFilter extends GenericFilterBean implements ApplicationEventPublisherAware {
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        //获取当前用户实例 继续过滤校验
//        if (SecurityContextHolder.getContext().getAuthentication() != null) {
//            chain.doFilter(request, response);
//            return;
//        }
//        //登录获取Auth
//        Authentication rememberMeAuth = this.rememberMeServices.autoLogin(request, response);
//        if (rememberMeAuth != null) {
//            // Attempt authenticaton via AuthenticationManager
//            try {
//                //进行remember-me校验
//                rememberMeAuth = this.authenticationManager.authenticate(rememberMeAuth);
//                // Store to SecurityContextHolder
//                //保存用户实例
//                SecurityContextHolder.getContext().setAuthentication(rememberMeAuth);
//                //成功页面跳转
//                onSuccessfulAuthentication(request, response, rememberMeAuth);
//                this.logger.debug(LogMessage.of(() -> "SecurityContextHolder populated with remember-me token: '"
//                        + SecurityContextHolder.getContext().getAuthentication() + "'"));
//                if (this.eventPublisher != null) {
//                    this.eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(
//                            SecurityContextHolder.getContext().getAuthentication(), this.getClass()));
//                }
//                if (this.successHandler != null) {
//                    this.successHandler.onAuthenticationSuccess(request, response, rememberMeAuth);
//                    return;
//                }
//            } catch (AuthenticationException ex) {
//                this.logger.debug(LogMessage
//                                .format("SecurityContextHolder not populated with remember-me token, as AuthenticationManager "
//                                        + "rejected Authentication returned by RememberMeServices: '%s'; "
//                                        + "invalidating remember-me token", rememberMeAuth),
//                        ex);
//                this.rememberMeServices.loginFail(request, response);
//                //失败页面跳转
//                onUnsuccessfulAuthentication(request, response, ex);
//            }
//        }
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//
//    }
//}
