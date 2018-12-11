package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Region record);

    Region selectByPrimaryKey(Long id);

    List<Region> selectAll(@Param("state") Integer state);

    int updateByPrimaryKey(Region record);

    List listByParentId(@Param("parentId") Long parentId);

    void changeState(Region region);
}