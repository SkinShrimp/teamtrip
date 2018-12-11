package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") TravelQueryObject qo, Model model){
        //待审核状态
        if (qo.getState()==null){
            qo.setState(Travel.STATE_AUDIT);
        }
        //公开
        qo.setIsPublic(true);
        //最后更新时间升序
        qo.setOrderBy("t.lastUpdateTime asc");
        model.addAttribute("pageInfo",travelService.query(qo));
        return "travel/list";
    }

    @RequestMapping("/releaseList")
    public String releaseList(@ModelAttribute("qo") TravelQueryObject qo, Model model){
        //发布状态
        qo.setState(Travel.STATE_RELEASE);
        //发布时间倒序
        qo.setOrderBy("t.releaseTime desc");
        model.addAttribute("pageInfo",travelService.query(qo));
        return "travel/releaseList";
    }

    @RequestMapping("/getContentById")
    @ResponseBody
    public TravelContent getContentById(Long id){
        return travelService.getContentById(id);
    }


    @RequestMapping("/changeState")
    @ResponseBody
    public JsonResult changeState(Travel travel){
        travelService.changeState(travel);
        return new JsonResult();
    }
}
