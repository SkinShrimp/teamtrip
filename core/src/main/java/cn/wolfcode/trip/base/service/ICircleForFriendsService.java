package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.CircleForFriends;
import cn.wolfcode.trip.base.query.CircleForFriendsQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ICircleForFriendsService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(CircleForFriendsQueryObject qo);

    List<CircleForFriends> selectAll();

    List<CircleForFriends> selectReleaseTime(Long id);
}
