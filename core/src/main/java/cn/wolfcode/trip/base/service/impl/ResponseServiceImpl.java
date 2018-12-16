package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.domain.Response;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.QuestionMapper;
import cn.wolfcode.trip.base.mapper.ResponseMapper;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import cn.wolfcode.trip.base.query.ResponseQueryObject;
import cn.wolfcode.trip.base.service.IQuestionService;
import cn.wolfcode.trip.base.service.IResponseService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResponseServiceImpl implements IResponseService {

    @Autowired
    private ResponseMapper responseMapper;

    @Override
    public int insert(Response record) {
        //保存当前用户id
        record.setUser(UserContext.getUser());
        //当前时间
        record.setReleaseTime(new Date());
        return responseMapper.insert(record);
    }

    @Override
    public Response selectByPrimaryKey(Long id) {
        return responseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Response> selectAll() {
        return responseMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Response record) {
        return responseMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo query(ResponseQueryObject qo) {

        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List list = responseMapper.selectForList(qo);
        return new PageInfo(list);
    }

    @Override
    public int ResponseCount(Long questionId) {
        return responseMapper.ResponseCount(questionId);
    }

}
