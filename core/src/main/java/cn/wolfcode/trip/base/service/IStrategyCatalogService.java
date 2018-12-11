package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyCatalogService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 新增和编辑
     */
    void saveOrUpdate(StrategyCatalog catalog);

    /**
     * 根据攻略id查询分类
     * @param strategyId
     * @return
     */
    List<StrategyCatalog> listByStrategyId(Long strategyId);
}
