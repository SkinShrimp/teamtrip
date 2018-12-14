package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.CircleForFriends;
import cn.wolfcode.trip.base.query.CircleForFriendsQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CircleForFriendsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CircleForFriends record);

    CircleForFriends selectByPrimaryKey(Long id);

    List<CircleForFriends> selectAll();

    int updateByPrimaryKey(CircleForFriends record);

    List selectForList(CircleForFriendsQueryObject qo);

    List<CircleForFriends> selectReleaseTime(@Param("id") Long id);

    //void selectReleaseTime(CircleForFriends circleForFriends);
}