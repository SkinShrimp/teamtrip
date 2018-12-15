package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Hotel;
import cn.wolfcode.trip.base.query.HotelQueryObject;
import cn.wolfcode.trip.base.query.HotelRoomDetailQuery;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.UserQueryObject;
import cn.wolfcode.trip.base.service.IHotelRoomDetailService;
import cn.wolfcode.trip.base.service.IHotelService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private IHotelRoomDetailService hotelRoomDetailService;
    
    @GetMapping
    public PageInfo query(HotelQueryObject qo){
        return hotelService.query(qo);
    }

    @GetMapping("/{id}")
    public Hotel selectById(@PathVariable Long id){
        return hotelService.selectById(id);
    }

    @GetMapping("/{hotelId}/rooms")
    public PageInfo getRooms(HotelRoomDetailQuery qo){
        return hotelRoomDetailService.query(qo);
    }


}
