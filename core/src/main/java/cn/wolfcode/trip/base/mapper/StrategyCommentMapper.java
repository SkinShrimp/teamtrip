package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StrategyCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StrategyComment record);

    StrategyComment selectByPrimaryKey(Long id);

    List<StrategyComment> selectAll();

    int updateByPrimaryKey(StrategyComment record);

    List selectForList(QueryObject qo);

    void insertRelation(@Param("commentId") Long commentId, @Param("tagId") Long tagId);
}