package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StrategyServiceImpl implements IStrategyService{

    @Autowired
    private StrategyMapper strategyMapper;

    public void saveOrUpdate(Strategy strategy) {
        if(strategy.getId()!=null){
            strategyMapper.updateByPrimaryKey(strategy);
        }else{
            strategyMapper.insert(strategy);
        }
    }

    public List<Strategy> listAll() {
        return strategyMapper.selectAll();
    }

    public Strategy getById(Long id) {
        return strategyMapper.selectByPrimaryKey(id);
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),true,false,null);
        List list = strategyMapper.selectForList(qo);
        return new PageInfo(list);
    }

}
