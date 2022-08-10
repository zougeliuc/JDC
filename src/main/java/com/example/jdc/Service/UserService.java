package com.example.jdc.Service;

import com.example.jdc.entity.User;
import com.example.jdc.mapper.UserMapper;
import com.example.jdc.untils.encrypt.BcryptHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public boolean register(User user) {
        // 1. 设置注册时间
        user.setRegtime(LocalDateTime.now());

        // 2. 加密密码
        String password = user.getPassword();
        String bcrypt = BcryptHelper.bcrypt(password);
        user.setPassword(bcrypt);

        // 3. 随机昵称
        String randomShowName = UUID.randomUUID().toString().substring(0, 11);
        user.setShowname(randomShowName);

        return userMapper.add(user) == 1;
    }

    @Cacheable(cacheNames = "user",key = "'userDetails'+#username")
    public User getUserDetails(String username) {
        return userMapper.selectUserDetailsByUsername(username);
    }

    @CacheEvict(cacheNames = "user",key = "'userDetails'+#username")
    public boolean updateUserDetails(String username, String showname, String sex, String phone, String email, String address,String info) {
        return userMapper.updateUserDetailsByUsername(username, showname,sex, phone,email, address, info) == 1;
    }

    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        User user = userMapper.selectByUsername(username);
        if (BcryptHelper.verify(oldPassword, user.getPassword())) {

            String encrypt = BcryptHelper.bcrypt(newPassword);
            return userMapper.updatePasswordByUsername(username, encrypt) == 1;
        }

        return false;
    }
}
