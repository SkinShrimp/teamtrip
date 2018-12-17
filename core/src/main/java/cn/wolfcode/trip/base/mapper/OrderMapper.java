package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Order;

import java.util.List;

public interface OrderMapper {
    void save(Order newOrder);

    void updateOrderState(String oid);

    void updateOrder(Order order);

    List<Order> queryOrder(Long id);
}
