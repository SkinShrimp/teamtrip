package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Comment;
import cn.wolfcode.trip.base.mapper.CommentMapper;
import cn.wolfcode.trip.base.query.CommentQueryObject;
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
        entry.setUser(UserContext.getUser());
        Comment comment = commentMapper.selectByTypeIdAndType(entry);
        //判断是否点赞过
        if (comment != null) {
            entry.setId(comment.getId());
            update(entry);
            return;
        }
        if (entry != null) {
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

    public Comment getCommentByTypeId(Comment entry) {
        return commentMapper.selectByTypeIdAndType(entry);
    }

    public void saveComment(Comment entry) {
        //无关代码
        entry.setCreateTime(new Date());
        entry.setUser(UserContext.getUser());

        Comment comment = commentMapper.selectByTypeIdAndType(entry);
        if (comment == null && entry.getParentId() == 0) {//新增父节点
            //获取最大节点数
            entry.setUser(null);
            Long num1 = commentMapper.selectMaxFlagId(entry);
            entry.setUser(UserContext.getUser());
            //设置节点ID,最多只能有100
            if (num1 != null) {
                entry.setFlagId(num1 + 100);
                entry.setCollect((int) (num1 + 100));
            } else {
                entry.setFlagId(100L);
                entry.setCollect(100);
            }
            commentMapper.insert(entry);
            return;
        } else {
            //父节点已经评论过的情况
            if (entry.getParentId() == 0) {
                entry.setParentId(comment.getFlagId());
                entry.setCollect(comment.getCollect());
            }


            /****确定子节点的FLAGID****/
            Long flagId = commentMapper.selectByCollect(entry);
            entry.setFlagId(flagId + 1);
            entry.setCollect(entry.getCollect());

        }
        commentMapper.insert(entry);

    }
}
