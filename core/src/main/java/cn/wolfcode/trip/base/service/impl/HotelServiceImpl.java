package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Hotel;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.HotelMapper;
import cn.wolfcode.trip.base.query.HotelQueryObject;
import cn.wolfcode.trip.base.service.IHotelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements IHotelService {
    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public PageInfo query(HotelQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Hotel> list = hotelMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public Hotel selectById(Long id) {
        return hotelMapper.selectByPrimaryKey(id);
    }
}
