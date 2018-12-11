package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.StrategyContent;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.domain.StrategyDetail;
import cn.wolfcode.trip.base.mapper.StrategyContentMapper;
import cn.wolfcode.trip.base.mapper.StrategyDetailMapper;
import cn.wolfcode.trip.base.mapper.StrategyDetailMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import cn.wolfcode.trip.base.service.IStrategyDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StrategyDetailServiceImpl implements IStrategyDetailService {

    @Autowired
    private StrategyDetailMapper strategyDetailMapper;

    @Autowired
    private StrategyContentMapper contentMapper;

    public void saveOrUpdate(StrategyDetail detail) {
        //如果没有序号
        if (detail.getSequence()==null){
            //就查询最大的序号+1进行设置
            int maxSequence = strategyDetailMapper.getMaxSequence(detail.getCatalog().getId());
            detail.setSequence(maxSequence+1);
        }

        //判断是否是发布状态,如果是就设置发布时间
        if(detail.getState()==StrategyDetail.STATE_RELEASE){
            detail.setReleaseTime(new Date());
        }

        StrategyContent content = detail.getStrategyContent();
        if(detail.getId()!=null){
            strategyDetailMapper.updateByPrimaryKey(detail);
            //操作攻略内容对象
            content.setId(detail.getId());
            contentMapper.updateByPrimaryKey(content);
        }else{
            //设置创建时间
            detail.setCreateTime(new Date());

            strategyDetailMapper.insert(detail);
            //操作攻略内容对象
            content.setId(detail.getId());
            contentMapper.insert(content);
        }

    }

    public StrategyContent getContentById(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    public StrategyDetail getByDetailsId(Long id) {
        return strategyDetailMapper.selectByPrimaryKey(id);
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List list = strategyDetailMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
