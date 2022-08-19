package com.example.jdc.untils.encrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

/*------>BCrypt加密<----------*/
public class BcryptHelper {

    private BcryptHelper() {}

    public static String bcrypt(String password) {
        //检查密码是否为空
        Objects.requireNonNull(password,"password must not be null");
        //生成随机盐
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password,salt);
    }

    public static boolean verify(String password,String encrypt) {
        return new BCryptPasswordEncoder().matches(password,encrypt);
    }

}
