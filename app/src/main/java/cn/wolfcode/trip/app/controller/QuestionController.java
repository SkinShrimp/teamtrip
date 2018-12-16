package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.NormalComment;
import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.domain.Response;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import cn.wolfcode.trip.base.query.ResponseQueryObject;
import cn.wolfcode.trip.base.service.*;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IResponseService responseService;
    @Autowired
    private INormalCommentService normalCommentService;

    /**
     * 查询所有的问题
     * @param qo
     * @return
     */
    @GetMapping()
    public PageInfo queryComments(QuestionQueryObject qo){
        qo.setOrderBy("q.releaseTime desc");
        return questionService.query(qo);
    }

    /**
     * 保存提出的问题
     * @return
     */
    @PostMapping()
    public JsonResult saveComments(Question question){
        questionService.insert(question);
        return new JsonResult();
    }

    /**
     * 通过提问id查询出某个问题的信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Question getContent(@PathVariable Long id){
        return questionService.selectByPrimaryKey(id);
    }



    /**
     * 通过用户id查询出用户信息
     * @param id
     * @return
     */
    @GetMapping("/{id}/users")
    public User getUser(@PathVariable Long id){
        return userService.get(id);
    }

    /**
     * 保存回答的问题
     * @param question
     * @return
     */
    @PostMapping("/{questionId}/comments")
    public JsonResult saveResponseComments(Question question){
        questionService.insert(question);
        return new JsonResult();
    }

    /**
     * 通过提问id查询出所有的回答,需要分页
     * @return
     */
    @GetMapping("/{questionId}/responses")
    public PageInfo selectResult(ResponseQueryObject qo){
        qo.setOrderBy("r.releaseTime desc");
        return responseService.query(qo);
    }
    /**
     * 修改浏览次数
     * @return
     */
    @PutMapping("/{id}")
    public JsonResult updateBrowseAnsave(Question question){
        questionService.updateBrowseAnsave(question);
        return new JsonResult();
    }


    /**
     * 保存二级评论内容
     * @param entry
     * @return
     */
    @PostMapping("/normalComments")
    public JsonResult save(NormalComment entry) {
        //设置当前用户
        entry.setUser(UserContext.getUser());
        //设置当前保存的时间
        entry.setCreateTime(new Date());
        //设置问答的状态 1
        entry.setType(1);
        normalCommentService.insert(entry);
        return new JsonResult();
    }

}
