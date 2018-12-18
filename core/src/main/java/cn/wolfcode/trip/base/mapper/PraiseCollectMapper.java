package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.PraiseCollect;
import cn.wolfcode.trip.base.query.PraiseCollectQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PraiseCollectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PraiseCollect record);

    PraiseCollect selectByPrimaryKey(Long id);

    List<PraiseCollect> selectAll();

    void updateByPrimaryKey(PraiseCollect record);

    List<PraiseCollect> selectForList(QueryObject qo);

    /**
     * 根据 type typeId parentId
     *
     * @param qo
     * @return
     */
    PraiseCollect selectByQueryObject(QueryObject qo);

    List<PraiseCollect> selectPraiseByTypeId(QueryObject qo);

    List<String> selectPraiseNameByParentId(@Param("parentId") Long parentId);

    List<PraiseCollect> selectPraiseByParentId(PraiseCollectQueryObject qo);



}