package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface ITravelCommendService {

    /**
     * 保存和更新
     * @param travelCommend
     */
    void saveOrUpdate(TravelCommend travelCommend);

    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * app的分页
     * @param qo
     * @return
     */
    PageInfo queryForApp(QueryObject qo);
}
