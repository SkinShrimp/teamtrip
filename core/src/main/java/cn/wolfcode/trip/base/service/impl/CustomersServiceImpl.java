package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Customer;
import cn.wolfcode.trip.base.mapper.CustomerMapper;
import cn.wolfcode.trip.base.service.ICustomersService;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersServiceImpl implements ICustomersService{

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public Customer getCustomerById() {
        Long userId = UserContext.getUser().getId();
        Customer customer = customerMapper.selectByUserId(userId);
        return customer;
    }

    @Override
    public void update(Customer customer) {
        customerMapper.updateByUserId(customer);
    }
}
