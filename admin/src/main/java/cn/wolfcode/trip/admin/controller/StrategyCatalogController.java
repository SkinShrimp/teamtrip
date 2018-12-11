package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.StrategyCatalogQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
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

import java.util.List;

@Controller
@RequestMapping("/strategyCatalog")
public class StrategyCatalogController {

    @Autowired
    private IStrategyCatalogService strategyCatalogService;

    @Autowired
    private IStrategyService strategyService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") StrategyCatalogQueryObject qo, Model model){
        model.addAttribute("strategies",strategyService.listAll());
        model.addAttribute("pageInfo",strategyCatalogService.query(qo));
        return "strategyCatalog/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(StrategyCatalog strategyCatalog){
        strategyCatalogService.saveOrUpdate(strategyCatalog);
        return new JsonResult();
    }


    @RequestMapping("/listByStrategyId")
    @ResponseBody
    public List<StrategyCatalog> listByStrategyId(Long strategyId){
        return strategyCatalogService.listByStrategyId(strategyId);
    }

}
