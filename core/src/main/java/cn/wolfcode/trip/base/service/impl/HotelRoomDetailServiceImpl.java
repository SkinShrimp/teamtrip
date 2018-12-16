package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.HotelRoomDetail;
import cn.wolfcode.trip.base.mapper.HotelRoomDetailMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IHotelRoomDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelRoomDetailServiceImpl implements IHotelRoomDetailService {
    @Autowired
    private HotelRoomDetailMapper hotelRoomDetailMapper;

    @Override
    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),"price");
        List list = hotelRoomDetailMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public HotelRoomDetail queryById(Long id) {
        return hotelRoomDetailMapper.selectByPrimaryKey(id);
    }
}
