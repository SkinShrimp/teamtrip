package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.StrategyCatalog;
import cn.wolfcode.trip.base.mapper.StrategyCatalogMapper;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyCatalogService;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyCatalogServiceImpl implements IStrategyCatalogService {

    @Autowired
    private StrategyCatalogMapper strategyCatalogMapper;

    public void saveOrUpdate(StrategyCatalog catalog) {
        //如果没有序号
        if (catalog.getSequence()==null){
            //就查询最大的序号+1进行设置
            int maxSequence = strategyCatalogMapper.getMaxSequence(catalog.getStrategy().getId());
            catalog.setSequence(maxSequence+1);
        }

        if(catalog.getId()!=null){
            strategyCatalogMapper.updateByPrimaryKey(catalog);
        }else{
            strategyCatalogMapper.insert(catalog);
        }
    }

    public List<StrategyCatalog> listByStrategyId(Long strategyId) {
        return strategyCatalogMapper.selectByStrategyId(strategyId);
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List list = strategyCatalogMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
