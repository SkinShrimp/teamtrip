package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.NormalComment;
import java.util.List;

public interface NormalCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NormalComment record);

    NormalComment selectByPrimaryKey(Long id);

    List<NormalComment> selectAll();

    int updateByPrimaryKey(NormalComment record);
}