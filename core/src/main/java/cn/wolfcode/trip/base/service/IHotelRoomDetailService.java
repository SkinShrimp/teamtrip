package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IHotelRoomDetailService {
    PageInfo query(QueryObject qo);
}
