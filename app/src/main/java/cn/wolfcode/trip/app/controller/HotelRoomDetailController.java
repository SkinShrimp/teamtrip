package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Hotel;
import cn.wolfcode.trip.base.domain.HotelRoomDetail;
import cn.wolfcode.trip.base.query.HotelQueryObject;
import cn.wolfcode.trip.base.query.HotelRoomDetailQuery;
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
@RequestMapping("/hotelroomdetails")
public class HotelRoomDetailController {
    @Autowired
    private IHotelRoomDetailService hotelRoomDetailService;
    @GetMapping("/{id}")
    public HotelRoomDetail queryById(@PathVariable Long id){
        return hotelRoomDetailService.queryById(id);
    }
}
