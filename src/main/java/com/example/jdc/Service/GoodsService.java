package com.example.jdc.Service;

import com.example.jdc.entity.Goods;
import com.example.jdc.mapper.GoodsMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    @Cacheable(cacheNames = "goods",key = "'searchGoods'+#gid")
    public Goods searchGoods(int gid) {
        return goodsMapper.selectByGid(gid);
    }

    public Page<Goods> searchGoodsList(String keyword, int page, int size) {
        Page<Goods> pageBean = PageHelper.startPage(page, size);
        goodsMapper.selectGoodsByTitleLike(keyword);
        return pageBean;
    }
}
