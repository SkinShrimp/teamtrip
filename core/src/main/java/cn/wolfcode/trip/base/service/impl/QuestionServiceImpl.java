package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.mapper.QuestionMapper;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import cn.wolfcode.trip.base.service.IQuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
   private UserMapper userMapper;

    @Override
    public int insert(Question record) {
       //设置保存时间
        record.setReleaseTime(new Date());
        return questionMapper.insert(record);
    }

    @Override
    public Question selectByPrimaryKey(Long id) {

        return questionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Question> selectAll() {
        return questionMapper.selectAll();
    }

    @Override
    public PageInfo query(QuestionQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List list = questionMapper.selectForList(qo);
        return new PageInfo(list);
    }

    /**
     * 修改出当前的浏览次数
     */

    @Override
    public void updateBrowseAnsave(Question question) {
        //先查询出当前浏览的次数然后在进行加1修改
        Question q = questionMapper.selectBrowse(question.getId());
        question.setBrowse(q.getBrowse()+1);
        question.setId(q.getId());
        //执行修改方法
        questionMapper.updateBrowse(question);
    }

}
