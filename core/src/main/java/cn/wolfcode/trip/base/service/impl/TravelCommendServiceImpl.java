package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.TravelCommend;
import cn.wolfcode.trip.base.mapper.TravelCommendMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ITravelCommendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelCommendServiceImpl implements ITravelCommendService {
    @Autowired
    private TravelCommendMapper travelCommendMapper;

    public void saveOrUpdate(TravelCommend travelCommend) {
        if(travelCommend.getId()!=null){
            travelCommendMapper.updateByPrimaryKey(travelCommend);
        }else{
            travelCommendMapper.insert(travelCommend);
        }
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List list = travelCommendMapper.selectForList(qo);
        return new PageInfo(list);
    }

    public PageInfo queryForApp(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List list = travelCommendMapper.selectForAppList(qo);
        return new PageInfo(list);
    }
}
