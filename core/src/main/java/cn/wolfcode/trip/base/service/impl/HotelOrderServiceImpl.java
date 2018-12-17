package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.HotelOrder;
import cn.wolfcode.trip.base.mapper.HotelOrderMapper;
import cn.wolfcode.trip.base.query.HotelOrderQuery;
import cn.wolfcode.trip.base.service.IHotelOrderService;
import cn.wolfcode.trip.base.service.IHotelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelOrderServiceImpl implements IHotelOrderService {
    @Autowired
    private HotelOrderMapper hotelOrderMapper;

    @Override
    public void save(HotelOrder hotelOrder) {
        hotelOrderMapper.insert(hotelOrder);
    }

    @Override
    public PageInfo query(HotelOrderQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"ho.startTime desc");
        List list = hotelOrderMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
