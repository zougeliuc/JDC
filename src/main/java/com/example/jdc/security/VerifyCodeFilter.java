package com.example.jdc.security;

import com.example.jdc.controller.CaptchaController;
import com.example.jdc.entity.User;
import com.example.jdc.untils.view.JsonView;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerifyCodeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!request.getRequestURI().contains(User.LOGIN_URL)){
            filterChain.doFilter(request,response);
        }else {
            verifyCode(request,response,filterChain);
        }
    }

    private void verifyCode(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object codeInSession = session.getAttribute(CaptchaController.VERIFY_CODE_IN_SESSION_KEY);
        String requestCode = request.getParameter(CaptchaController.REQUEST_VERIFY_CODE_PARAMETER);
        if (codeInSession == null || requestCode == null) {
            JsonView.failure("非法访问...").responseWebClient(response);
        }else if (!codeInSession.toString().equalsIgnoreCase(requestCode)) {
            session.removeAttribute(CaptchaController.VERIFY_CODE_IN_SESSION_KEY);
            JsonView.failure("验证码不正确").responseWebClient(response);
        }else {
            filterChain.doFilter(request,response);
        }

    }
}
