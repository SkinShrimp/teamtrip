package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IStrategyCommentService {

    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 新增
     * @param strategyComment
     * @return
     */
    void save(StrategyComment strategyComment, String[] tags);



    List<StrategyComment> selectAll();

    /**
     * 查询所有的评论用于首页轮播图
     * @return
     */
    List<StrategyComment> selectStrategyAll(StrategyComment strategyComment);
}
