package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Comment;
import cn.wolfcode.trip.base.mapper.CommentMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ICommentService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    public void save(Comment entry) {
        Comment comment = commentMapper.selectByTypeIdAndType(entry.getTypeId(), entry.getType());
        if (comment != null) {
            entry.setId(comment.getId());
            update(entry);
            return;
        }
        if (entry != null) {
            entry.setUser(UserContext.getUser());
            entry.setCreateTime(new Date());
        }

        commentMapper.insert(entry);
    }

    public void delete(Long id) {
        commentMapper.deleteByPrimaryKey(id);
    }

    public Comment get(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    public void update(Comment entry) {
        commentMapper.updateByPrimaryKey(entry);
    }

    public PageInfo<Comment> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Comment> list = commentMapper.selectForList(qo);
        return new PageInfo<Comment>(list);
    }

    public Comment getCommentByTypeId(Integer typeId) {
        return commentMapper.selectByTypeIdAndType(typeId, Comment.NORMAL_TYPE);
    }
}
