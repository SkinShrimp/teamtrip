package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.domain.TravelContent;
import cn.wolfcode.trip.base.mapper.TravelContentMapper;
import cn.wolfcode.trip.base.mapper.TravelMapper;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TravelServiceImpl implements ITravelService {

    @Autowired
    private TravelMapper travelMapper;
    @Autowired
    private TravelContentMapper contentMapper;

    public PageInfo query(TravelQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List list = travelMapper.selectForList(qo);
        return new PageInfo(list);
    }

    public void saveOrUpdate(Travel travel) {
        Date date = new Date();
        travel.setLastUpdateTime(date);//最后更新时间

        if(travel.getId()!=null){
            //更新游记表
            travelMapper.updateByPrimaryKey(travel);

            //更新游记内容表
            TravelContent travelContent = travel.getTravelContent();
            travelContent.setId(travel.getId());
            contentMapper.updateByPrimaryKey(travelContent);

        }else{
            //保存游记表
            travel.setAuthor(UserContext.getUser());//设置作者
            travel.setCreateTime(date);//创建时间
            travelMapper.insert(travel);

            //保存游记内容表
            TravelContent travelContent = travel.getTravelContent();
            travelContent.setId(travel.getId());
            contentMapper.insert(travelContent);
        }
    }

    public Travel getById(Long id) {
        return travelMapper.selectByPrimaryKey(id);
    }

    public TravelContent getContentById(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }

    public void changeState(Travel travel) {
        //判断是否是发布状态,如果是就设置发布时间
        if(travel.getState()==Travel.STATE_RELEASE){
            travel.setReleaseTime(new Date());
        }
        travelMapper.changeState(travel);
    }
}
