package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/strategy")
public class StrategyController {

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyQueryObject qo, Model model){
        model.addAttribute("regions",regionService.listAll(null));
        model.addAttribute("pageInfo",strategyService.query(qo));
        return "strategy/list";
    }



    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Strategy strategy, MultipartFile file){
        //判断是否上传图片
        if(file!=null&&file.getSize()>0){
            String url = UploadUtil.upload(file, UploadUtil.PATH + "/upload");
            strategy.setCoverUrl(url);
        }

        strategyService.saveOrUpdate(strategy);
        return new JsonResult();
    }

}
