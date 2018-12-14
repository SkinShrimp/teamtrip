package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Notice;
import cn.wolfcode.trip.base.domain.UserChat;
import cn.wolfcode.trip.base.query.NoticeQueryObject;
import cn.wolfcode.trip.base.query.UserChatQueryObject;
import cn.wolfcode.trip.base.service.INoticeService;
import cn.wolfcode.trip.base.service.IUserChatService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * 用户私信资源控制器
 */
@RestController
@RequestMapping("/notices")
public class NoticeController {

    @Autowired
    private INoticeService noticeService;

    /**
     * 查询我所有收到的通知
     * @return
     */
    @GetMapping("/{userId}")
    public PageInfo listAll(NoticeQueryObject qo){
        qo.setOrderBy("n.notice_time desc");
        qo.setPageSize(50);
        PageInfo query = noticeService.query(qo);
        return query;
    }

    /**
     *删除通知
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public JsonResult delete(@PathVariable Long id){
       JsonResult jsonResult = new JsonResult();
       noticeService.delete(id);
       return jsonResult;
    }

    /**
     *更新通知状态
     * @return
     */
    @PutMapping("/{id}")
    public JsonResult updateStatus(Notice notice){
        JsonResult jsonResult = new JsonResult();
        noticeService.updateStatus(notice);
        return jsonResult;
    }

    /**
     * 查询我所有收到的通知
     * @return
     */
    @GetMapping("/{userId}/unreads")
    public Integer listUnReads(NoticeQueryObject qo){
        Integer res = noticeService.listUnReads(qo);
        return res;
    }
}
