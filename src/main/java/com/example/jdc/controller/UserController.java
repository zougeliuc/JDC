package com.example.jdc.controller;

import com.example.jdc.Service.UserService;
import com.example.jdc.entity.User;
import com.example.jdc.untils.validation.BindingResultHelper;
import com.example.jdc.untils.view.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/anonymous/register")
    public JsonView register(@Validated User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> map = BindingResultHelper.toErrorMap(bindingResult);
            return JsonView.failure("参数错误").put("args", map);
        }
        boolean register = userService.register(user);

        return register
                ? JsonView.success("注册成功，欢迎您！").put("username", user.getUsername())
                : JsonView.success("注册失败，数据错误！");

    }

    @GetMapping("/user/userDetails")
    public JsonView userDetails(String username){

        if (username == null){
            return JsonView.failure("找不到用户信息...");
        }

        User user = userService.getUserDetails(username);

        // 注册时间
        LocalDateTime regtime = user.getRegtime();

        // Duration 表示时间段
        // Period   表示日期段

        Period period = Period.between(regtime.toLocalDate(), LocalDate.now());
        int days = period.getDays();

        return  JsonView.success("用户信息").put("user", user).put("regdays", days);
    }

    @PostMapping("/user/updateUserInfo")
    public JsonView updateUserInfo(String username,
                                   String showname,
                                   String phone,
                                   String sex,
                                   String email,
                                   String address,
                                   String info){

        if (username == null || "".equals(username)){
            return JsonView.failure("参数错误");
        }

        boolean update = userService.updateUserDetails(username, showname, sex, phone, email, address, info);

        return update
                ? JsonView.success("更新成功")
                : JsonView.failure("更新失败");

    }

    @PostMapping("/user/updatePassword")
    public JsonView updatePassword(String username, String oldPassword, String newPassword){

        boolean updatePassword = userService.updatePassword(username, oldPassword, newPassword);
        if (updatePassword){

            // 跟新成功后 退出登录
            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(null);

            return JsonView.success("更新密码成功");
        }

        return JsonView.failure("更新密码失败");

    }
}
