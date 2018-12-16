package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.NormalComment;
import cn.wolfcode.trip.base.query.CircleForFriendsCommentQueryObject;

import java.util.List;
import java.util.Map;

public interface INormalCommentService {
    int insert(NormalComment record);

    NormalComment selectByPrimaryKey(Long id);

    List<NormalComment> selectAll();

    List<Map<String,Object>> initNormalComment(CircleForFriendsCommentQueryObject qo);
}
