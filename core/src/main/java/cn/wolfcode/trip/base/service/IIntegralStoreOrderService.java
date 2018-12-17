package cn.wolfcode.trip.base.service;


import cn.wolfcode.trip.base.query.IntegralStoreOrderQuery;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IIntegralStoreOrderService {

    List listExpressage();

    void login(String password);

    void savaOrder(Long goodsId, Integer count, Integer totalPrice);

    /**
     * 查询指定状态的订单
     */
    PageInfo list(QueryObject qo);

    PageInfo query(IntegralStoreOrderQuery qo);
}
