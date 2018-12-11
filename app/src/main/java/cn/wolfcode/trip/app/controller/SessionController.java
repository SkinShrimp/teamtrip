package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会话资源控制器
 */
@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public JsonResult login(String email,String password){
        JsonResult json = new JsonResult();
        try {
            User user = userService.login(email, password);
            //把user返回给前台
            json.setResult(user);
        } catch (Exception e) {
            e.printStackTrace();
            json.mark(e.getMessage());
        }
        return json;
}


    @DeleteMapping
    public void logout(){
        //注销
        UserContext.setUser(null);
    }
}
