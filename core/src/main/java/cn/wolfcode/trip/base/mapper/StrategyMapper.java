package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.QueryObject;
import java.util.List;

public interface StrategyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Strategy record);

    Strategy selectByPrimaryKey(Long id);

    List<Strategy> selectAll();

    int updateByPrimaryKey(Strategy record);

    List selectForList(QueryObject qo);
}