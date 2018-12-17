package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Hotel;
import cn.wolfcode.trip.base.query.HotelQueryObject;
import com.github.pagehelper.PageInfo;

public interface IHotelService {
    PageInfo query(HotelQueryObject qo);

    /**
     * 根据id查询酒店
     * @param id
     * @return
     */
    Hotel selectById(Long id);
}
