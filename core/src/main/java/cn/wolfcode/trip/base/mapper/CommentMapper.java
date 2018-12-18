package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Comment;
import cn.wolfcode.trip.base.query.CommentQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    int insert(Comment record);

    List<Comment> selectForList(QueryObject qo);

    Comment selectByPrimaryKey(Long id);

    void updateByPrimaryKey(Comment entry);

    void deleteByPrimaryKey(Long id);

    Comment selectByTypeIdAndType(Comment entry);

    void updateCommentById(Comment entry);

    Long selectMaxFlagId(Comment entry);

    Long selectByCollect(Comment entry);

    List<Map<String,Object>> selectCommentByUserId(CommentQueryObject qo);

    List<Map<String,Object>> selectPraiseByUserId(CommentQueryObject qo);
}