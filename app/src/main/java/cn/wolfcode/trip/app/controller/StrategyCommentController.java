package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TagQueryObject;
import cn.wolfcode.trip.base.service.*;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 攻略资源控制器
 */
@RestController
@RequestMapping("/strategyComments")
public class StrategyCommentController {

    @Autowired
    private IStrategyCommentService strategyCommentService;

    @GetMapping()
    public List<StrategyComment> selectStrategy(StrategyComment strategyComment){
        strategyComment.setState(1);
        return   strategyCommentService.selectStrategyAll(strategyComment);
    }

}
