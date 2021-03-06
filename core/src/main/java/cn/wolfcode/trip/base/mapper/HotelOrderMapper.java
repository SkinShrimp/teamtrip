package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.HotelOrder;
import cn.wolfcode.trip.base.query.HotelOrderQuery;

import java.util.List;

public interface HotelOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HotelOrder record);

    HotelOrder selectByPrimaryKey(Long id);

    List<HotelOrder> selectAll();

    int updateByPrimaryKey(HotelOrder record);

    List selectForList(HotelOrderQuery qo);
}