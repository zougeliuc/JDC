package com.example.jdc.controller;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/anonymous/captcha")
public class CaptchaController {
    public static  final String VERIFY_CODE_IN_SESSION_KEY = "captcha";

    //前端请求的验证码参数
    public static final String REQUEST_VERIFY_CODE_PARAMETER = "requestCode";

    @GetMapping
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // ChineseCaptcha chineseCaptcha = new ChineseCaptcha(130, 48, 4);
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        specCaptcha.setFont(new Font("Verdana",Font.PLAIN,32));
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        String code = specCaptcha.text();
        System.out.println(code);
        //将验证码存储在Session,回话跟踪
        request.getSession().setAttribute(VERIFY_CODE_IN_SESSION_KEY,code);
        CaptchaUtil.out(specCaptcha,request,response);
    }

}
