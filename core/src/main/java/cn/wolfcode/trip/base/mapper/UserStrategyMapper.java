package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.UserStrategy;
import cn.wolfcode.trip.base.query.UserChatQueryObject;
import cn.wolfcode.trip.base.query.UserStrategyQueryObject;

import java.util.List;

public interface UserStrategyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserStrategy record);

    UserStrategy selectByPrimaryKey(Long id);

    List<UserStrategy> selectAll();

    int updateByPrimaryKey(UserStrategy record);

    List selectByUserId(UserStrategyQueryObject qo);
}