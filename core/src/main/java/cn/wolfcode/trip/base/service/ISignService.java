package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.SignIn;

public interface ISignService {
    SignIn get();

    void update();

    SignIn getByUserId();

}
