package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.TravelContent;

import java.util.List;

public interface TravelContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TravelContent record);

    TravelContent selectByPrimaryKey(Long id);

    List<TravelContent> selectAll();

    int updateByPrimaryKey(TravelContent record);
}