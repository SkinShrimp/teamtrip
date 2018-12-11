package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 新增和编辑
     * @param strategy
     */
    void saveOrUpdate(Strategy strategy);

    /**
     * 查询全部
     * @return
     */
    List<Strategy> listAll();

    /**
     * 根据id查询攻略
     * @param id
     * @return
     */
    Strategy getById(Long id);

}
