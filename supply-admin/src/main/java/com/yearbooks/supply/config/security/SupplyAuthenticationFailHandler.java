package com.yearbooks.supply.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yearbooks.supply.model.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangdouyun
 * @version 1.0
 * @className SupplyAuthenticationFailHandler
 * @description TODO
 * @since 2021-11-04 20:39
 */
@Component
public class SupplyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @param request
     * @param response
     * @param exception
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(RespBean.error("用户名或密码错误!")));
    }
}
