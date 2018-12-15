package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import cn.wolfcode.trip.base.service.IQuestionService;
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
     * 通过用户id查询出某个问题的信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Question getContent(@PathVariable Long id){
        return questionService.selectByPrimaryKey(id);
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

}
