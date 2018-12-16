package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.NormalComment;
import cn.wolfcode.trip.base.mapper.NormalCommentMapper;
import cn.wolfcode.trip.base.service.INormalCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NormalCommentServiceImpl implements INormalCommentService {

    @Autowired
    private NormalCommentMapper normalCommentMapper;

    @Override
    public int insert(NormalComment record) {
        return normalCommentMapper.insert(record);
    }

    @Override
    public NormalComment selectByPrimaryKey(Long id) {
        return normalCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<NormalComment> selectAll() {
        return normalCommentMapper.selectAll();
    }
}