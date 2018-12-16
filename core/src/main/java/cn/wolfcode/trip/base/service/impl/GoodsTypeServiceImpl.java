package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.GoodsType;
import cn.wolfcode.trip.base.mapper.GoodsTypeMapper;
import cn.wolfcode.trip.base.service.IGoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeServiceImpl implements IGoodsTypeService {


    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public List<GoodsType> listAll() {
        return goodsTypeMapper.selectAll();
    }
}
