package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import java.util.List;

public interface TravelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Travel record);

    Travel selectByPrimaryKey(Long id);

    List<Travel> selectAll();

    int updateByPrimaryKey(Travel record);

    List selectForList(TravelQueryObject qo);

    void changeState(Travel travel);
}