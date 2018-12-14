package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.domain.UserChat;
import cn.wolfcode.trip.base.query.UserChatQueryObject;
import cn.wolfcode.trip.base.service.IUserChatService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户私信资源控制器
 */
@RestController
@RequestMapping("/userchats")
public class UserChatController {

    @Autowired
    private IUserChatService userChatService;

    /**
     * 查询所有我私信过的用户和私信过我的用户
     * @return
     */
    @GetMapping
    public Set listAllAboutMe(UserChatQueryObject qo){
        qo.setOrderBy("uc.sendTime desc");
        Set set = userChatService.listAllAboutMe(qo);
        return set;
    }

    /**
     * 根据发送者和接收者获取二者之间的历史纪录
     * @param qo
     * @return
     */
    @GetMapping("/{senderId}/{receiverId}/histories")
    public PageInfo listHistories(UserChatQueryObject qo){
        qo.setOrderBy("uc.sendTime");
        PageInfo pageInfo = userChatService.query(qo);
        return pageInfo;
    }

    /**
     * 两个用户进行即时通信,前台每隔两秒发一次请求
     * @param qo
     * @return
     */
    @GetMapping("/{senderId}/{receiverId}/{sendTime}")
    public List getNewMessage(UserChatQueryObject qo){
        qo.setPageSize(1);
        qo.setOrderBy("uc.sendTime desc");
        PageInfo<UserChat> pageInfo = userChatService.queryByOrder(qo);
        return pageInfo.getList();
    }

    /**
     * 保存一条userchat记录
     * @param uc
     * @return
     */
    @PostMapping
    public JsonResult save(UserChat uc){
        JsonResult jsonResult = new JsonResult();
        userChatService.save(uc);
        return jsonResult;
    }

    /**
     * 查询当前用户共多少条私信内容
     * @param receiverId
     * @return
     */
    @GetMapping("/{receiverId}/unreads")
    public Integer getHistoryUnreadNum(@PathVariable Long receiverId){
        Integer res = userChatService.getHistoryUnreadNum(receiverId);
        return res;
    }

    /**
     * 接收者为自己，展示当前用户私信的每个用户上有多少条未读信息，之和应该为上边查询结果
     * @param qo
     * @return
     */
    @GetMapping("/{senderId}/{receiverId}/unreads")
    public Integer getUnreadNum(UserChatQueryObject qo){
        Integer num = userChatService.getUnreadsBySenderAndrReceiverId(qo);
        return num;
    }

    @PutMapping
    public JsonResult updateByStatus(UserChat uc){
        JsonResult jsonResult = new JsonResult();
        userChatService.update(uc);
        return jsonResult;
    }
}
