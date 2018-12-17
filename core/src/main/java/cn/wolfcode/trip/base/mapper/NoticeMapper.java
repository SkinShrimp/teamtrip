package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Notice;
import cn.wolfcode.trip.base.query.CircleForFriendsQueryObject;
import cn.wolfcode.trip.base.query.NoticeQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;

import java.util.List;

public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    Notice selectByPrimaryKey(Long id);

    List<Notice> selectAll();

    int updateByPrimaryKey(Notice record);

    List selectForList(QueryObject qo);

    void updateByStatus(Notice notice);

    Integer selectCountByStatus(NoticeQueryObject qo);
}