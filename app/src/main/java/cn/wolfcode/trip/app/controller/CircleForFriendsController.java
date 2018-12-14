package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.CircleForFriends;
import cn.wolfcode.trip.base.query.CircleForFriendsQueryObject;
import cn.wolfcode.trip.base.service.ICircleForFriendsService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        qo.setOrderBy("cs.releaseTime desc");
        return circleForFriendsService.query(qo);
    }
    /**发布保存朋友圈
     * @return
     */
    @PostMapping()
    public void saveCircle(CircleForFriends circle){
         circleForFriendsService.insert(circle);
    }
}
