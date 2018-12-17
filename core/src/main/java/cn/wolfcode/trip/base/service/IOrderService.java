package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Order;

import java.util.List;

public interface IOrderService {
  void saveOrder(Order newOrder);

  void update(Order order);

  //oid
  void updateOrderState(String r6_order);

  List<Order> queryOrder(Long id);
}
