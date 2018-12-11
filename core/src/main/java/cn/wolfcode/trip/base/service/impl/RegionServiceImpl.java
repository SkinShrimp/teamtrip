package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.mapper.RegionMapper;
import cn.wolfcode.trip.base.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionMapper regionMapper;

    public List listByParentId(Long parentId) {
        return regionMapper.listByParentId(parentId);
    }

    public void saveOrUpdate(Region region) {
        if(region.getId()!=null){
            regionMapper.updateByPrimaryKey(region);
        }else{
            regionMapper.insert(region);
        }
    }

    public void changeState(Region region) {
        regionMapper.changeState(region);
    }

    public List<Region> listAll(Integer state) {
        return regionMapper.selectAll(state);
    }
}
