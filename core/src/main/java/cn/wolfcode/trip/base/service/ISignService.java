package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.SignIn;
import cn.wolfcode.trip.base.util.JsonResult;

public interface ISignService {
    SignIn get();

    void update();

    SignIn getByUserId();

    Integer getIntegral(Long userId);

    void updateIntegral(Long userId, Integer integral);
}
