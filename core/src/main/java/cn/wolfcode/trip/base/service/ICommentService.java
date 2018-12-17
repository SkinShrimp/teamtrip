package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Comment;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface ICommentService {
    public abstract void save(Comment entry);

    public abstract void delete(Long id);

    public abstract Comment get(Long id);

    public abstract void update(Comment entry);

    public abstract PageInfo<Comment> query(QueryObject qo);

    public abstract Comment getCommentByTypeId(Comment entry);

    /**
     * 评论信息保存或者更新
     *
     * @param entry
     */
    public abstract void saveComment(Comment entry);
}
