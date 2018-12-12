package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.util.UploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageController {

    @PostMapping
    public Map upload(MultipartFile upload) {
        HashMap map = new HashMap();
        //上传图片,保存到指定目录
        try {
            //String url = UploadUtil.upload(upload, UploadUtil.PATH + "/upload");

            String url = UploadUtil.uploadQiniuyun(upload);
            map.put("uploaded", 1);
            map.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("uploaded", 0);
            HashMap temp = new HashMap();
            temp.put("message", "亲,上传失败!");
            map.put("error", temp);
        }
        return map;
    }


}
