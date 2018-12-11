package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Region;

import java.util.List;

public interface IRegionService {
    /**
     * 根据上级地区查询
     * @param parentId
     * @return
     */
    List listByParentId(Long parentId);

    /**
     * 新增和编辑
     * @param region
     */
    void saveOrUpdate(Region region);

    /**
     * 修改状态
     * @param region
     */
    void changeState(Region region);

    /**
     * 获取所有地区
     * 可以根据状态查询
     * @return
     */
    List<Region> listAll(Integer state);

}
