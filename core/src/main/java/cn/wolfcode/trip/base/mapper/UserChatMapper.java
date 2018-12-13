package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.UserChat;
import java.util.List;

public interface UserChatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserChat record);

    UserChat selectByPrimaryKey(Long id);

    List<UserChat> selectAll();

    int updateByPrimaryKey(UserChat record);
}