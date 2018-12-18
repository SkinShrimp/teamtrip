package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Customer;
import java.util.List;

public interface CustomerMapper {
    int insert(Customer record);

    List<Customer> selectAll();

    Customer selectByUserId(Long userId);

    void updateByUserId(Customer customer);
}