package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.PraiseCollect;
import cn.wolfcode.trip.base.query.PraiseCollectQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 可拷贝
 */
public interface IPraiseCollectService {
    public abstract void save(PraiseCollect entry);

    public abstract void delete(Long id);

    public abstract PraiseCollect get(Long id);

     void   update(PraiseCollect record );

    public abstract List<PraiseCollect> listAll();

    public abstract PageInfo<PraiseCollect> query(QueryObject qo);

    void saveOrUpdatePraise(PraiseCollect entry);

    List<PraiseCollect> selectPraiseByTypeId(QueryObject qo);

    List<String> selectPraiseNameByParentId(Long parenId);

    //日报点赞
    List<PraiseCollect> selectPraiseByParentId(PraiseCollectQueryObject qo);
}
