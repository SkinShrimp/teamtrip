package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Daily;
import cn.wolfcode.trip.base.domain.DailyContent;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IDailyService {

    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo<?> query(QueryObject qo);


    /**
     * 新增或编辑
     * @param daily
     */
    void saveOrUpdate(Daily daily);

    /**
     * 查询文章内容 回显到ck编辑框
     * @param id
     * @return
     */
    DailyContent getContentById(Long id);

    /**
     * 根据id查询日报文章所有信息(包括内容)
     * @param id
     * @return
     */
    Daily getByDailyId(Long id);
}
