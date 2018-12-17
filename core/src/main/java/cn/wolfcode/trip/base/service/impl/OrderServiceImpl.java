package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Order;
import cn.wolfcode.trip.base.mapper.OrderMapper;
import cn.wolfcode.trip.base.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper mapper;

    public void saveOrder(Order newOrder) {
        mapper.save(newOrder);
    }

    public void update(Order order) {
        mapper.updateOrder(order);
    }

    public void updateOrderState(String r6_order) {
        mapper.updateOrderState(r6_order);
    }

    public List<Order> queryOrder(Long id) {
        return mapper.queryOrder(id);
    }
}
