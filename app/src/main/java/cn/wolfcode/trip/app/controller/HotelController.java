package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.query.HotelQueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import cn.wolfcode.trip.base.service.IHotelService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 酒店资源控制器
 */
@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private IHotelService hotelService;
    
    @GetMapping
    public PageInfo query(HotelQueryObject qo){
        return hotelService.query(qo);
    }
}
