package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.NormalComment;

import java.util.List;

public interface INormalCommentService {
    int insert(NormalComment record);

    NormalComment selectByPrimaryKey(Long id);

    List<NormalComment> selectAll();
}
