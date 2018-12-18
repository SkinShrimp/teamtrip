package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.HotelOrder;
import cn.wolfcode.trip.base.query.HotelOrderQuery;
import com.github.pagehelper.PageInfo;

public interface IHotelOrderService {
    void save(HotelOrder hotelOrder);

    PageInfo query(HotelOrderQuery qo);
}
