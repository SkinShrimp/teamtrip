package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.NormalComment;
import cn.wolfcode.trip.base.query.CircleForFriendsCommentQueryObject;
import cn.wolfcode.trip.base.service.ICircleForFriendsService;
import cn.wolfcode.trip.base.service.INormalCommentService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CircleForFriendsComment")
public class CircleForFriendsCommentController {
    @Autowired
    private INormalCommentService normalCommentService;

    @PostMapping
    public JsonResult save(NormalComment entry) {
        normalCommentService.insert(entry);
        return new JsonResult();
    }

    @GetMapping
    private List<Map<String, Object>> getNormalComments(CircleForFriendsCommentQueryObject qo){
        return normalCommentService.initNormalComment(qo);
    }
}
