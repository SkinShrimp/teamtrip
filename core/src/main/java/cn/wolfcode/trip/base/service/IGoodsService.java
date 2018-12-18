package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Goods;
import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.query.GoodsQueryObject;
import com.github.pagehelper.PageInfo;


public interface IGoodsService {
    PageInfo query(GoodsQueryObject qo);

    void saveOrUpdate(Goods goods);

    Goods getGoodsById(Long id);

    void deleteById(Long id);
}
