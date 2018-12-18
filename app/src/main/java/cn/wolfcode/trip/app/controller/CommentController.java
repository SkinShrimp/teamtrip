package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Comment;
import cn.wolfcode.trip.base.query.CommentQueryObject;
import cn.wolfcode.trip.base.service.ICommentService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageInfo;
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

    /**
     * 增加互相评论
     *
     * @param entry
     * @return
     */
    @PostMapping("/comment")
    public JsonResult saveComment(Comment entry) {
        commentService.saveComment(entry);
        return new JsonResult();
    }

    /**
     * 查询点赞、收藏
     * @param entry
     * @return
     */
    @GetMapping("/{typeId}")
    public Comment get(Comment entry) {
        entry.setUser(UserContext.getUser());
        entry.setParentId(null);
        return commentService.getCommentByTypeId(entry);
    }

    /**
     * 查询所有评论信息
     *
     * @param qo
     * @return
     */
    @GetMapping
    public PageInfo query(CommentQueryObject qo) {
        qo.setPageSize(0);
        return commentService.query(qo);
    }

    /**
     * 查询一个用户的所有评论
     * @param qo
     * @return
     */
    @GetMapping("/{userId}/comments")
    public PageInfo getCommentByUserId(CommentQueryObject qo) {
        qo.setOrderBy("c.createTime desc");
        return commentService.getCommentByUserId(qo);
    }


    /**
     * 查询一个用户的所有点赞
     * @param qo
     * @return
     */
    @GetMapping("/{userId}/praises")
    public PageInfo getPraiseByUserId(CommentQueryObject qo) {
        qo.setOrderBy("c.createTime desc");
        return commentService.getPraiseByUserId(qo);
    }
}
