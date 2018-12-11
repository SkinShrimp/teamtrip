package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.TravelCommendQueryObject;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import cn.wolfcode.trip.base.service.ITravelService;
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
@RequestMapping("/travelCommend")
public class TravelCommendController {

    @Autowired
    private ITravelCommendService travelCommendService;

    @RequestMapping("/list")
    public String list(@ModelAttribute("qo") TravelCommendQueryObject qo, Model model){
        model.addAttribute("pageInfo",travelCommendService.query(qo));
        return "travelCommend/list";
    }



    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(TravelCommend travelCommend, MultipartFile file){
        //判断是否上传图片
        if(file!=null&&file.getSize()>0){
            String url = UploadUtil.upload(file, UploadUtil.PATH + "/upload");
            travelCommend.setCoverUrl(url);
        }

        travelCommendService.saveOrUpdate(travelCommend);
        return new JsonResult();
    }

}
