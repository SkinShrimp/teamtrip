package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.CircleForFriends;
import cn.wolfcode.trip.base.query.CircleForFriendsQueryObject;
import cn.wolfcode.trip.base.service.ICircleForFriendsService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 朋友圈资源控制器
 */
@RestController
@RequestMapping("/circleForFriends")
public class CircleForFriendsController {

    @Autowired
    private ICircleForFriendsService circleForFriendsService;

    @GetMapping()
    public PageInfo queryComments(CircleForFriendsQueryObject qo){

        return circleForFriendsService.query(qo);
    }
    /**
     * 返回json格式的时间给前台
     * @return
     *//*
    @GetMapping("/{user.id}/releaseTime")
    public JsonResult queryReleaseTime(@PathVariable Long id){
        List<CircleForFriends> circles = circleForFriendsService.selectReleaseTime(id);
        return new JsonResult();
    }*/
}
