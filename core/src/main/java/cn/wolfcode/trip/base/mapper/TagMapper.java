package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Tag;
import java.util.List;

public interface TagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tag record);

    Tag selectByPrimaryKey(Long id);

    List<Tag> selectAll();

    int updateByPrimaryKey(Tag record);
}