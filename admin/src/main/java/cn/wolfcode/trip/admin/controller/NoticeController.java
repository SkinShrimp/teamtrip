package cn.wolfcode.trip.admin.controller;

import cn.wolfcode.trip.base.domain.Notice;
import cn.wolfcode.trip.base.query.NoticeQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.INoticeService;
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
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    @RequestMapping("/save")
    @ResponseBody
    public JsonResult saveOrUpdate(Notice notice,Integer flag){
        noticeService.saveOrUpdate(notice,flag);
        return new JsonResult();
    }

}
