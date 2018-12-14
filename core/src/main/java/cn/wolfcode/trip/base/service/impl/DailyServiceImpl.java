package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Daily;
import cn.wolfcode.trip.base.domain.DailyContent;
import cn.wolfcode.trip.base.mapper.DailyContentMapper;
import cn.wolfcode.trip.base.mapper.DailyMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IDailyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 日报service
 * 包含日报内容
 */
@Service
public class DailyServiceImpl implements IDailyService {

    //注入日报
    @Autowired
    private DailyMapper dailyMapper;

    //注入日报内容
    @Autowired
    private DailyContentMapper contentMapper;

    //分页
    public PageInfo<?> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<Daily>list = dailyMapper.selectForList(qo);
        return new PageInfo(list);
    }

    //新增或编辑
    public void saveOrUpdate(Daily daily) {

        //获取日报的内容对象
        DailyContent content = daily.getContent();

        if(daily.getId()!=null){
            //更新日报
            dailyMapper.updateByPrimaryKey(daily);

            //手动封装内容id, 内容id没有自增长,内容id与日报id同步
            content.setId(daily.getId());
            //更新日报内容
            contentMapper.updateByPrimaryKey(content);

        }else {
            //手动封装创建时间
            daily.setCreateTime(new Date());
            //保存日报
            dailyMapper.insert(daily);

            //手动封装内容id, 内容id没有自增长,内容id与日报id同步
            content.setId(daily.getId());
            //保存日报内容
            contentMapper.insert(content);

        }

    }

    /**
     * 根据日报文章id查询单个日报文章内容
     */
    public DailyContent getContentById(Long id) {
        return contentMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据日报文章id 查询单个日报文章及内容
     */
    public Daily getByDailyId(Long id) {
        return dailyMapper.selectByPrimaryKey(id);
    }
}
