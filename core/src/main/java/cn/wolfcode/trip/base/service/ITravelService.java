package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import com.github.pagehelper.PageInfo;

public interface ITravelService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(TravelQueryObject qo);

    /**
     * 新增和编辑
     * @param travel
     */
    void saveOrUpdate(Travel travel);

    /**
     * 根据id查询游记
     * @param id
     * @return
     */
    Travel getById(Long id);

    /**
     * 根据id查询游记内容
     * @param id
     * @return
     */
    TravelContent getContentById(Long id);

    /**
     * 修改游记状态
     * @param travel
     */
    void changeState(Travel travel);
}
