package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.SignIn;
import cn.wolfcode.trip.base.domain.SignInTime;
import cn.wolfcode.trip.base.mapper.SignInTimeMapper;
import cn.wolfcode.trip.base.service.ISignInTimeService;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class SignInTimeServiceImpl implements ISignInTimeService {

    @Autowired
    private SignInTimeMapper signInTimeMapper;

    public List getDaysByuserId() {

        //获取用户id
        Long id = UserContext.getUser().getId();

        List days = signInTimeMapper.selectDaysByUserId(id);

        return days;
    }

    public void save() {

        //获得当前用户的id
        Long userId = UserContext.getUser().getId();

        //获得当前的时间
        Date date = new Date();

        //查询今天是否已经签到,没有签到才能保存签到时间
        SignInTime  signInTime= signInTimeMapper.SelectByUserId(userId, date);

        if(signInTime==null){

            //保存到数据库中
            signInTimeMapper.insertByUserName(userId,date);

        }

    }

}
