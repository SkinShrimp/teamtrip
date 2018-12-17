package cn.wolfcode.trip.base.service;


import java.util.List;

public interface IIntegralStoreOrderService {

    List listExpressage();

    void login(String password);

    void savaOrder(Long goodsId, Integer count, Integer totalPrice);

    /**
     * 查询指定状态的订单
     */
    List list(Boolean stutes);

}
