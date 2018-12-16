package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
@Api(value = "图片资源",description = "图片资源控制器")
public class ImageController {

    //
    @PostMapping
    @ApiOperation(value = "上传图片",notes = "上传图片")
    public Map upload(MultipartFile file){
        HashMap map = new HashMap<>();
        //上传图片,保存到指定目录
        try {
            //String url = UploadUtil.upload(file, UploadUtil.PATH + "/upload");
            String url = UploadUtil.uploadQiniuyun(file);
            map.put("status",1);
            map.put("url",UploadUtil.QI_PATH+url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",0);
            map.put("msg","亲,上传失败!");
        }
        return map;
    }

}
