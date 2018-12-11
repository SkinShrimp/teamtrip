package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 游记资源控制器
 */
@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @Autowired
    private ITravelCommendService commendService;

    @PostMapping
    public JsonResult save(Travel travel){
        travelService.saveOrUpdate(travel);
        return new JsonResult();
    }

    @PutMapping("/{id}")
    public JsonResult update(Travel travel){
        travelService.saveOrUpdate(travel);
        return new JsonResult();
    }

    @GetMapping("/{id}")
    public Travel getById(@PathVariable Long id){
        return travelService.getById(id);
    }

    @GetMapping
    public PageInfo query(TravelQueryObject qo){
        //已发布游记
        qo.setState(Travel.STATE_RELEASE);
        return travelService.query(qo);
    }

    @GetMapping("/commends")
    public PageInfo listCommends(TravelCommendQueryObject qo){
        qo.setOrderBy("tc.schedule desc");
        return commendService.queryForApp(qo);
    }

}
