package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IStrategyDetailService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 新增和编辑
     */
    void saveOrUpdate(StrategyDetail detail);

    /**
     * 根据id查询攻略文章内容
     * @param id
     * @return
     */
    StrategyContent getContentById(Long id);

    /**
     * 根据id查询攻略文章所有信息(包括内容)
     * @param id
     * @return
     */
    StrategyDetail getByDetailsId(Long id);
}
