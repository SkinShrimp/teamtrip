package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Customer;
import cn.wolfcode.trip.base.service.ICustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomersService customersService;

    @GetMapping()
    public Customer get(){
        return customersService.getCustomerById();
    }
}
