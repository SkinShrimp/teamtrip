package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.NormalComment;
import cn.wolfcode.trip.base.query.CircleForFriendsCommentQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import com.sun.javafx.collections.MappingChange;

import java.util.List;
import java.util.Map;

public interface NormalCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NormalComment record);

    NormalComment selectByPrimaryKey(Long id);

    List<NormalComment> selectAll();

    int updateByPrimaryKey(NormalComment record);

    List<Map<String, Object>> selectForInitNormalComment(CircleForFriendsCommentQueryObject qo);

}