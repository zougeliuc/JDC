package com.example.jdc.mapper;

import com.example.jdc.entity.Cart;
import com.example.jdc.entity.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {
    // 通过用户id, gid查询该商品是否加入购物车
    Cart select(@Param("uid") int uid, @Param("gid") int gid);

    // 第一次添加到购物车
    int insert(Cart cart);

    // 多次加入购物车
    int update(@Param("uid") int uid,
               @Param("gid") int gid);

    List<CartVo> selectByUid(int uid);

    int deleteByUid(int uid);
}
