package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.StrategyComment;
import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.StrategyCommentQueryObject;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;

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
}
