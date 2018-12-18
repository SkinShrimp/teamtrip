package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.HotelOrder;
import cn.wolfcode.trip.base.query.HotelOrderQuery;
import cn.wolfcode.trip.base.query.HotelQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IHotelOrderService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单资源控制器
 */
@RestController
@RequestMapping("/hotelOrders")
public class HotelOrderController {
    @Autowired
    private IHotelOrderService hotelOrderService;

    @PostMapping
    public JsonResult save(HotelOrder hotelOrder){
        JsonResult result = new JsonResult();
        hotelOrderService.save(hotelOrder);
        return result;
    }

    @GetMapping("/{userId}")
    public PageInfo query(HotelOrderQuery qo){
        return hotelOrderService.query(qo);
    }

}
