package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.DailyContent;

import java.util.List;

public interface DailyContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DailyContent record);

    DailyContent selectByPrimaryKey(Long id);

    List<DailyContent> selectAll();

    int updateByPrimaryKey(DailyContent record);
}