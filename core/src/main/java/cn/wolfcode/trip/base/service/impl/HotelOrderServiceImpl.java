package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.HotelOrder;
import cn.wolfcode.trip.base.mapper.HotelOrderMapper;
import cn.wolfcode.trip.base.service.IHotelOrderService;
import cn.wolfcode.trip.base.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelOrderServiceImpl implements IHotelOrderService {
    @Autowired
    private HotelOrderMapper hotelOrderMapper;

    @Override
    public void save(HotelOrder hotelOrder) {
        hotelOrderMapper.insert(hotelOrder);
    }
}
