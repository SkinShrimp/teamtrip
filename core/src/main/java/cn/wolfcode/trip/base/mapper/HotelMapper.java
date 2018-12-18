package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Hotel;
import cn.wolfcode.trip.base.query.HotelQueryObject;

import java.util.List;

public interface HotelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Hotel record);

    Hotel selectByPrimaryKey(Long id);

    List<Hotel> selectAll();

    int updateByPrimaryKey(Hotel record);

    List<Hotel> selectForList(HotelQueryObject qo);
}