package com.example.jdc.controller;

import com.example.jdc.Service.CartService;
import com.example.jdc.entity.CartVo;
import com.example.jdc.untils.view.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/cart/add")
    public JsonView addCart(String username, int gid) {
        boolean addSuccess = cartService.addCart(username, gid);
        return addSuccess ? JsonView.success("添加成功") : JsonView.success("添加失败");
    }

    @GetMapping("/cart/list")
    public JsonView getCartList(String username) {
        List<CartVo> cartList = cartService.getCartList(username);
        return JsonView.success("购物车列表").put("cartList", cartList);
    }

    @PostMapping("/cart/clear")
    public JsonView clearCart(String username) {
        boolean clear = cartService.clearCart(username);
        return clear ? JsonView.success("清除购物车成功") : JsonView.failure("清除失败");
    }
}
