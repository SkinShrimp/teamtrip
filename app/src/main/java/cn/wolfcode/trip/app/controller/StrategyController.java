package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.*;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
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
@RequestMapping("/strategies")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IStrategyCatalogService catalogService;

    @Autowired
    private IStrategyDetailService detailService;

    @Autowired
    private IStrategyCommentService commentService;

    @GetMapping
    public PageInfo query(StrategyQueryObject qo){
        return strategyService.query(qo);
    }


    @GetMapping("/{id}")
    public Strategy getById(@PathVariable Long id){
        return strategyService.getById(id);
    }

    @GetMapping("/{id}/catalogs")
    public List<StrategyCatalog> listByStrategyId(@PathVariable Long id){
        List<StrategyCatalog> catalogs = catalogService.listByStrategyId(id);
        return catalogs;
    }

    @GetMapping("/details/{id}")
    public StrategyDetail getByDetailsId(@PathVariable Long id){
        return detailService.getByDetailsId(id);
    }

    @GetMapping("/{strategyId}/comments")
    public PageInfo queryComments(StrategyCommentQueryObject qo){
        qo.setOrderBy("sc.createTime desc");
        return commentService.query(qo);
    }

    @PostMapping("/{strategy.id}/comments")
    public JsonResult saveComment(StrategyComment strategyComment,String[] tags){
        commentService.save(strategyComment,tags);
        return new JsonResult();
    }

}
