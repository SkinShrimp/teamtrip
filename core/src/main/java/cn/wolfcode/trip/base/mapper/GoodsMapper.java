package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Goods;
import cn.wolfcode.trip.base.query.GoodsQueryObject;
import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    Goods selectByPrimaryKey(Long id);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);

    List<Goods> selectForList(GoodsQueryObject qo);
}