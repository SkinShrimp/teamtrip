package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.SignIn;

import java.util.Date;
import java.util.List;

public interface ISignInTimeService {

    /**
     * 根据当前用户id获取当月已经签到的时间
     *
     * @return
     */
    List getDaysByuserId();

    void save();



}
