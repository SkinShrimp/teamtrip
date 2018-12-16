package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.domain.Response;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import cn.wolfcode.trip.base.query.ResponseQueryObject;
import cn.wolfcode.trip.base.service.IQuestionService;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IResponseService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IResponseService responseService;

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


}
