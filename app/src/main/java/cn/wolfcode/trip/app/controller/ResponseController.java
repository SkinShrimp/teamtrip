package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.domain.Response;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import cn.wolfcode.trip.base.query.ResponseQueryObject;
import cn.wolfcode.trip.base.service.IQuestionService;
import cn.wolfcode.trip.base.service.IResponseService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responses")
public class ResponseController {
    @Autowired
    private IResponseService responseService;

    /**
     * 查询评论总数
     * @return
     */
    @GetMapping("/{questionId}")
    public int selectResults(@PathVariable Long questionId){
        return responseService.ResponseCount(questionId);
    }
    /**
     * 查询评论总数
     * @return
     */
    @PostMapping()
    public JsonResult saveResponse(Response response){
        responseService.insert(response);
        return new JsonResult();
    }

}
