package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.TagQueryObject;
import com.github.pagehelper.PageInfo;

public interface ITagService {
    /**
     * 获取排序的标签
     * @param
     * @return
     */
    PageInfo<Tag> query(TagQueryObject qo);
}
