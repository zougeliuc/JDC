package com.example.jdc.mapper;

import com.example.jdc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User selectByUsername(String username);

    int add(User user);

    User selectUserDetailsByUsername(String username);

    int updateUserDetailsByUsername(@Param("username") String username,
                                    @Param("showname") String showname,
                                    @Param("sex") String sex,
                                    @Param("phone") String phone,
                                    @Param("email") String email,
                                    @Param("address") String address,
                                    @Param("info") String info);

    int updatePasswordByUsername(@Param("username") String username, @Param("password")String password);
}
