package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Daily;
import cn.wolfcode.trip.base.domain.NormalComment;
import cn.wolfcode.trip.base.query.CircleForFriendsCommentQueryObject;
import cn.wolfcode.trip.base.query.DailyQueryObject;
import cn.wolfcode.trip.base.service.IDailyService;
import cn.wolfcode.trip.base.service.INormalCommentService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("dailies")
public class DailyController {

    //注入日报service
    @Autowired
    private IDailyService dailyService;

    //二级评论
    @Autowired
    private INormalCommentService normalCommentService;

    //展示到index页
    @GetMapping
    public PageInfo<?> list(DailyQueryObject qo){
        //展示普通状态
        qo.setState(Daily.STATE_NORMAL);
        //按照创建时间降序展示
        qo.setOrderBy("createTime desc");

        return dailyService.query(qo);
    }


    @GetMapping("/{id}")
    public Daily getByDailyId(@PathVariable Long id){
        return dailyService.getByDailyId(id);
    }


    /**
     * 保存二级评论
     * @param entry
     * @return
     */
    @PostMapping
    public JsonResult save(NormalComment entry) {
        normalCommentService.insert(entry);
        return new JsonResult();
    }

}
