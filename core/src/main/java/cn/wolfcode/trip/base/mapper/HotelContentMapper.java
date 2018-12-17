package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.HotelContent;
import java.util.List;

public interface HotelContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HotelContent record);

    HotelContent selectByPrimaryKey(Integer id);

    List<HotelContent> selectAll();

    int updateByPrimaryKey(HotelContent record);
}