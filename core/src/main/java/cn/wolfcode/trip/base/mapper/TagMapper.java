package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.TagQueryObject;
import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    Tag selectByPrimaryKey(Long id);

    List<Tag> selectAll();

    int updateByPrimaryKey(Tag record);

    List<Tag> selectByTag(TagQueryObject qo);
}