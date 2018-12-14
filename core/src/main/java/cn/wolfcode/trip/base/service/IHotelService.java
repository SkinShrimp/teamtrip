package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.query.HotelQueryObject;
import com.github.pagehelper.PageInfo;

public interface IHotelService {
    PageInfo query(HotelQueryObject qo);
}
