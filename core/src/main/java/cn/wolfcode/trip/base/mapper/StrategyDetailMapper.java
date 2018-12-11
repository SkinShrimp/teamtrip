package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.QueryObject;
import java.util.List;

public interface StrategyDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StrategyDetail record);

    StrategyDetail selectByPrimaryKey(Long id);

    List<StrategyDetail> selectAll();

    int updateByPrimaryKey(StrategyDetail record);

    List selectForList(QueryObject qo);

    int getMaxSequence(Long catalogId);
}