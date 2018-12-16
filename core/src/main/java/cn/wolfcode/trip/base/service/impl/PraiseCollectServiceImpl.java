package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.PraiseCollect;
import cn.wolfcode.trip.base.mapper.PraiseCollectMapper;
import cn.wolfcode.trip.base.query.PraiseCollectQueryObject;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IPraiseCollectService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PraiseCollectServiceImpl implements IPraiseCollectService {
    @Autowired
    private PraiseCollectMapper praiseCollectMapper;

    @Override
    public void save(PraiseCollect entry) {
        praiseCollectMapper.insert(entry);
    }

    @Override
    public void delete(Long id) {
        praiseCollectMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PraiseCollect get(Long id) {
        return praiseCollectMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(PraiseCollect entry) {
        praiseCollectMapper.updateByPrimaryKey(entry);
    }

    @Override
    public List<PraiseCollect> listAll() {
        return praiseCollectMapper.selectAll();
    }

    @Override
    public PageInfo<PraiseCollect> query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<PraiseCollect> list = praiseCollectMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public void saveOrUpdatePraise(PraiseCollect entry) {
        //带入 type typeId parentId 必须
        PraiseCollectQueryObject qo = new PraiseCollectQueryObject();
        qo.setTypeId(entry.getTypeId());
        qo.setTypeFlag(entry.getTypeFlag());
        qo.setParentId(entry.getParentId());
        //是否存在
        PraiseCollect isFlag = praiseCollectMapper.selectByQueryObject(qo);

        if (isFlag == null && entry.getPraise() == 1) {
            entry.setTypeId(UserContext.getUser().getId());
            praiseCollectMapper.insert(entry);
        } else {
            entry.setId(isFlag.getId());
//            praiseCollectMapper.updateByPrimaryKey(entry);
            if (entry.getPraise() == 1) {
                return;
            }
            praiseCollectMapper.deleteByPrimaryKey(isFlag.getId());
        }
    }

    @Override
    public List<PraiseCollect> selectPraiseByTypeId(QueryObject qo) {
        return praiseCollectMapper.selectPraiseByTypeId(qo);
    }

    @Override
    public List<String> selectPraiseNameByParentId(Long parenId) {
        return praiseCollectMapper.selectPraiseNameByParentId(parenId);
    }
}
