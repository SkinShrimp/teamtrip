package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Comment;
import cn.wolfcode.trip.base.service.ICommentService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @PostMapping("/{typeId}/collectOrPraise")
    public JsonResult save(Comment entry) {
        commentService.save(entry);
        return new JsonResult();
    }

    @PutMapping("/{id}")
    public JsonResult update(Comment entry) {
        commentService.update(entry);
        return new JsonResult();
    }

    @GetMapping("/{typeId}")
    public Comment get(@PathVariable Integer typeId) {
        return commentService.getCommentByTypeId(typeId);
    }
}
