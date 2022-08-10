package com.example.jdc.mapper;

import com.example.jdc.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    public Goods selectByGid(int gid);

    List<Goods> selectGoodsByTitleLike(String keyword);
}
