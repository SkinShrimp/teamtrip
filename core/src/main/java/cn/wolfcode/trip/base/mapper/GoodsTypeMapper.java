package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.GoodsType;
import java.util.List;

public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsType record);

    GoodsType selectByPrimaryKey(Long id);

    List<GoodsType> selectAll();

    int updateByPrimaryKey(GoodsType record);
}