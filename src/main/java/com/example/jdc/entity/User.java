package com.example.jdc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  public static final String LOGIN_URL = "/api/v1/user/login";
  public static final String LOGOUT_URL = "/api/v1/user/logout";
  private static final long serialVersionUID = -4680012425826674L;

  private Integer uid;

  @NotNull(message = "账号不能为空")
  @Size(min = 3, max = 20, message = "账号为3~20位")
  private String username;

  @NotNull(message = "密码不能为空")
  @Size(min = 6, max = 16, message = "密码为6~16位")
  private String password;

  @NotNull(message = "手机号码不能为空")
  @Pattern(regexp ="^(?:(?:\\+|00)86)?1(?:(?:3[\\d])|(?:4[5-7|9])|(?:5[0-3|5-9])|(?:6[5-7])|(?:7[0-8])|(?:8[\\d])|(?:9[1|8|9]))\\d{8}$",message = "手机号码格式不对")
  private String phone;

  private String sex;
  private String info;
  private String address;
  private String showname;
  private LocalDateTime regtime;
  private String email;

  private List<Role> roles;

}
