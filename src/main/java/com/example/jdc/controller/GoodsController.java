package com.example.jdc.controller;

import com.example.jdc.Service.GoodsService;
import com.example.jdc.entity.Goods;
import com.example.jdc.untils.view.JsonView;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @GetMapping("/anonymous/goodsList")
    public JsonView getGoodsList(int page, int size, String keyword) {
        if (page <= 0 || size < 1 || keyword == null || "".equals(keyword)) {
            return JsonView.failure("没有数据");
        }

        Page<Goods> goodsPage = goodsService.searchGoodsList(keyword, page, size);

        return JsonView.success("商品列表")
                .put("goodsList", goodsPage.getResult())
                .put("pages", goodsPage.getPages())
                .put("page", page);
    }

    @GetMapping("/anonymous/goods/{gid}")
    public JsonView getGoods(@PathVariable("gid") int gid) {
        Goods goods = goodsService.searchGoods(gid);
        return JsonView.success("商品详情信息").put("goods", goods);
    }
}
