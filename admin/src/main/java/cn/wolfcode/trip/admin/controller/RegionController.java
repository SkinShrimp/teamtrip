package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    @RequestMapping("/list")
    public String list(){
       return "region/list";
    }

    @RequestMapping("/listByParentId")
    @ResponseBody
    public List listByParentId(Long parentId,String type){
        List<Region> list = regionService.listByParentId(parentId);
        //判断是否是tree类型
        if("tree".equals(type)){
            //存储treeview格式的map
            ArrayList treeList = new ArrayList();
            //转换为treeview需要的格式
            for (Region region : list) {
                treeList.add(region.toTreeMap());
            }
            return treeList;
        }else{
            return list;
        }
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Region region){
        regionService.saveOrUpdate(region);
        return new JsonResult();
    }

    @RequestMapping("/changeState")
    @ResponseBody
    public JsonResult changeState(Region region){
        regionService.changeState(region);
        return new JsonResult();
    }
}
