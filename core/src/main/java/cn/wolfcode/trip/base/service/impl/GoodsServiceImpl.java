package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Goods;
import cn.wolfcode.trip.base.mapper.GoodsMapper;
import cn.wolfcode.trip.base.query.GoodsQueryObject;
import cn.wolfcode.trip.base.service.IGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo query(GoodsQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Goods> list = goodsMapper.selectForList(qo);
        return new PageInfo<>(list);
    }

    @Override
    public void saveOrUpdate(Goods goods) {

        //判断是否是更新操作
        if(goods.getId()!=null){
        goodsMapper.updateByPrimaryKey(goods);
        }else {
            goodsMapper.insert(goods);
        }
    }

    @Override
    public Goods getGoodsById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }
}
