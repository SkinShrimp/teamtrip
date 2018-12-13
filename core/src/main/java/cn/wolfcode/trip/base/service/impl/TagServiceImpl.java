package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.mapper.TagMapper;
import cn.wolfcode.trip.base.query.TagQueryObject;
import cn.wolfcode.trip.base.service.ITagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagMapper tagMapper;

    public PageInfo<Tag> query(TagQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<Tag> travelCommends = tagMapper.selectByTag(qo);
        return new PageInfo(travelCommends);
    }
}
