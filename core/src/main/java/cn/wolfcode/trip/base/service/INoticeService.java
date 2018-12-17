package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Notice;
import cn.wolfcode.trip.base.query.NoticeQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface INoticeService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 新增和编辑
     * @param notice
     */
    void saveOrUpdate(Notice notice,int flag);

    /**
     * 查询全部
     * @return
     */
    List<Notice> listAll();

    /**
     * 根据id查询攻略
     * @param id
     * @return
     */
    Notice getById(Long id);

    void delete(Long id);

    void updateStatus(Notice notice);

    Integer listUnReads(NoticeQueryObject qo);
}
