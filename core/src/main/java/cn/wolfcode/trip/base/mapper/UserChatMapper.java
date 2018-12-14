package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.domain.UserChat;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.query.UserChatQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserChatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserChat record);

    UserChat selectByPrimaryKey(Long id);

    List<UserChat> selectAll();

    int updateByPrimaryKey(UserChat record);

    List selectForList(QueryObject qo);

    List<UserChat> selectForListByOrder(UserChatQueryObject qo);

    Integer selectByReceiverId(@Param("receiverId") Long receiverId);

    Integer selectUnreadsBySenderAndrReceiverId(UserChatQueryObject qo);

    void updateByStatus(UserChat userChat);
}