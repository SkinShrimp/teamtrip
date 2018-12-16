package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Goods;
import cn.wolfcode.trip.base.query.GoodsQueryObject;
import cn.wolfcode.trip.base.service.IGoodsService;
import cn.wolfcode.trip.base.service.IGoodsTypeService;
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
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsTypeService goodsTypeService;


    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") GoodsQueryObject qo, Model model){
        model.addAttribute("goodsTypes",goodsTypeService.listAll());
        model.addAttribute("pageInfo",goodsService.query(qo));
        return "integralStore/list";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Goods goods, MultipartFile file){
        if(file!=null&&file.getSize()>0){
            String url = UploadUtil.upload(file, UploadUtil.PATH + "/upload");
            goods.setPicture(url);
        }

        goodsService.saveOrUpdate(goods);
        return new JsonResult();
    }


    /*@RequestMapping("/getContentById")
    @ResponseBody
    public StrategyContent getContentById(Long id){
        return goodsServicef.getContentById(id);
    }*/

}
