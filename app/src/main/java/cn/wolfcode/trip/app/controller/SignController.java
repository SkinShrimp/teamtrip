package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.SignIn;
import cn.wolfcode.trip.base.domain.SignInTime;
import cn.wolfcode.trip.base.service.ISignInTimeService;
import cn.wolfcode.trip.base.service.ISignService;
import cn.wolfcode.trip.base.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private ISignInTimeService signInTimeService;

    @Autowired
    private ISignService signInService;

    /**
     * 获取当月已经签到的时间
     * @return
     */
    @ResponseBody
    @GetMapping("/signInTime")
    public List getDays(){
        return signInTimeService.getDaysByuserId();
    }


    /**
     * 获取签到的记录数据
     * @return
     */
    @ResponseBody
    @GetMapping("/get")
    public SignIn getSignRecord(){
        SignIn signIn = signInService.get();
        return signIn;
    }


    /**
     * 前台签到,将签到的时间保持到签到记录表,更新签到表
     */
    @ResponseBody
    @GetMapping("/savaOrUpdate")
    public void savaOrUpdate(){

        signInTimeService.save();

        signInService.update();
    }


    /**
     * 判断当日是否签到
     * @return
     */
    @ResponseBody
    @GetMapping("/getTodaySign")
    public SignIn getTodaySign(){
        return signInService.getByUserId();
    }


}
