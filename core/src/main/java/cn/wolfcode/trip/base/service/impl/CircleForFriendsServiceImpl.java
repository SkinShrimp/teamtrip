package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.CircleForFriends;
import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.mapper.CircleForFriendsMapper;
import cn.wolfcode.trip.base.mapper.StrategyCatalogMapper;
import cn.wolfcode.trip.base.query.CircleForFriendsQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ICircleForFriendsService;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CircleForFriendsServiceImpl implements ICircleForFriendsService {

    @Autowired
    private CircleForFriendsMapper circleForFriendsMapper;

    public PageInfo query(CircleForFriendsQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize(), qo.getOrderBy());
        qo.setLoginUserId(UserContext.getUser().getId());
        List list = circleForFriendsMapper.selectForList(qo);
        return new PageInfo(list);
    }

    public List<CircleForFriends> selectAll() {
        return circleForFriendsMapper.selectAll();
    }

    public List<CircleForFriends> selectReleaseTime(Long id) {
        return circleForFriendsMapper.selectReleaseTime(id);
    }

    public void insert(CircleForFriends record) {
        //设置当前时间
        record.setReleaseTime(new Date());
        //设置当前用户
        record.setUser(UserContext.getUser());
        circleForFriendsMapper.insert(record);
    }


}
