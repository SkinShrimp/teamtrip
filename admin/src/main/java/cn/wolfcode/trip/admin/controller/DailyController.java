package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Daily;
import cn.wolfcode.trip.base.domain.DailyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.DailyQueryObject;
import cn.wolfcode.trip.base.service.IDailyService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UploadUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 日报资源控制器
 */
@Controller
@RequestMapping("daily")
public class DailyController {

    //注入日报Service
    @Autowired
    private IDailyService dailyService;


    //日报列表
    @RequestMapping("list")
    public String list(Model model, @ModelAttribute("qo") DailyQueryObject qo){

        //列表按照创建时间降序排列展示
        qo.setOrderBy("createTime desc");

        //共享数据到页面
        PageInfo<?> pageInfo = dailyService.query(qo);
        model.addAttribute("pageInfo",pageInfo);

        return "daily/list";
    }

    //保存或更新
   /* @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Daily daily){
        dailyService.saveOrUpdate(daily);
        return new JsonResult();
    }*/

    //查询日报的内容,用于回显到ck文本框
    @RequestMapping("getContentById")
    @ResponseBody
    public DailyContent getContentById(Long id){
        return dailyService.getContentById(id);
    }
    //查询日报的内容,用于回显到ck文本框
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult  saveOrUpdate(Daily daily,MultipartFile file){
        if(file != null && file.getSize() > 0){
            String url = UploadUtil.uploadQiniuyun(file);
            daily.setCoverUrl(url);
        }
        dailyService.saveOrUpdate(daily);
        return new JsonResult();
    }
}
