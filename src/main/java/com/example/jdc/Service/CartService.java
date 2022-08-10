package com.example.jdc.Service;

import com.example.jdc.entity.Cart;
import com.example.jdc.entity.CartVo;
import com.example.jdc.mapper.CartMapper;
import com.example.jdc.mapper.GoodsMapper;
import com.example.jdc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    CartMapper cartMapper;

    @Autowired
    GoodsMapper goodsMapper;

    public boolean addCart(String username, int gid) {
        //    业务添加购物车
        int uid = userMapper.selectByUsername(username).getUid();

        Cart cart = cartMapper.select(uid, gid);

        if (cart == null) {
            Cart _cart = new Cart();
            // _cart.setCid(0);
            _cart.setUid(uid);
            _cart.setGid(gid);
            _cart.setCnt(1);
            BigDecimal price = goodsMapper.selectByGid(gid).getPrice();
            _cart.setPrice(price);
            cartMapper.insert(_cart);
        } else {
            cartMapper.update(uid, gid);
        }
        return true;
    }

    public List<CartVo> getCartList(String username) {
        int uid = userMapper.selectByUsername(username).getUid();
        return cartMapper.selectByUid(uid);
    }

    public boolean clearCart(String username) {
        int uid = userMapper.selectByUsername(username).getUid();
        int count = cartMapper.deleteByUid(uid);
        return count > 0;
        
    }
}
