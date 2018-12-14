package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Notice;
import cn.wolfcode.trip.base.mapper.NoticeMapper;
import cn.wolfcode.trip.base.query.NoticeQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.INoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements INoticeService{

    @Autowired
    private NoticeMapper noticeMapper;

    public void saveOrUpdate(Notice notice,int flag) {
        if(notice.getId()!=null){
            noticeMapper.updateByPrimaryKey(notice);
        }else{
            notice.setStatus(0);
            notice.setNoticeTime(new Date());
            if(flag == 1){
                notice.setTitle("您的游记已被推荐!");
            }else if(flag == 2){
                notice.setTitle("您的游记已被发布!");
            }else{
                notice.setTitle("您的游记已被拒绝!");
            }
            noticeMapper.insert(notice);
        }
    }

    public List<Notice> listAll() {
        return noticeMapper.selectAll();
    }

    public Notice getById(Long id) {
        return noticeMapper.selectByPrimaryKey(id);
    }

    public void delete(Long id) {
        noticeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateStatus(Notice notice) {
        noticeMapper.updateByStatus(notice);
    }

    @Override
    public Integer listUnReads(NoticeQueryObject qo) {
        Integer res = noticeMapper.selectCountByStatus(qo);
        return res;
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List list = noticeMapper.selectForList(qo);
        return new PageInfo(list);
    }

}
