package edu.zut.handler.security;

import com.alibaba.fastjson.JSON;
import edu.zut.domain.ResponseResult;
import edu.zut.enums.AppHttpCodeEnum;
import edu.zut.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义失败处理
 * 授权过程中出现异常
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        // 返回403无权限操作
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);
    }
}
