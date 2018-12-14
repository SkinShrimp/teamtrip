package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Daily;
import cn.wolfcode.trip.base.query.QueryObject;

import java.util.List;

public interface DailyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Daily record);

    Daily selectByPrimaryKey(Long id);

    List<Daily> selectAll();

    int updateByPrimaryKey(Daily record);

    /**
     * 分页
     * @param qo
     * @return
     */
    List<Daily> selectForList(QueryObject qo);
}