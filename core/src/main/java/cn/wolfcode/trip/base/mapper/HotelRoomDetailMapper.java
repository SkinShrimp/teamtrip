package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.HotelRoomDetail;
import cn.wolfcode.trip.base.query.QueryObject;

import java.util.List;

public interface HotelRoomDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(HotelRoomDetail record);

    HotelRoomDetail selectByPrimaryKey(Long id);

    List<HotelRoomDetail> selectAll();

    int updateByPrimaryKey(HotelRoomDetail record);

    List selectForList(QueryObject qo);
}