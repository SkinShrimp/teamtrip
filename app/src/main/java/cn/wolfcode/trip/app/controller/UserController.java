package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户资源控制器
 */
@RestController
@RequestMapping("/users")
@Api(value = "用户资源",description = "用户资源控制器")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITravelService travelService;

    @PostMapping
    @ApiOperation(value = "注册功能",notes = "其实就是新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "昵称",name = "nickName",dataType = "String",required = true),
            @ApiImplicitParam(value = "邮箱",name = "email",dataType = "String",required = true),
            @ApiImplicitParam(value = "密码",name = "password",dataType = "String",required = true)
    })

    public JsonResult register(User user){
        JsonResult json = new JsonResult();
        try {
            userService.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            json.mark(e.getMessage());
        }
        return json;
    }

    @PutMapping("/{id}")
    public JsonResult update(User user){
        JsonResult json = new JsonResult();
        userService.update(user);
        //把用户返回给前台
        json.setResult(user);
        return json;
    }

    @GetMapping("/{authorId}/travels")
    public PageInfo queryTravel(TravelQueryObject qo){
        qo.setOrderBy("t.lastUpdateTime desc");
        return travelService.query(qo);
    }

    @GetMapping
    public PageInfo query(UserQueryObject qo){
        return userService.query(qo);
    }
}
