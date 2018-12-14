package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Daily;
import cn.wolfcode.trip.base.query.DailyQueryObject;
import cn.wolfcode.trip.base.service.IDailyService;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dailies")
public class DailyController {

    //注入日报service
    @Autowired
    private IDailyService dailyService;

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

}
