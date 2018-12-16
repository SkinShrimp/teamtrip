package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IQuestionService {

    int insert(Question record);

    Question selectByPrimaryKey(Long id);

    List<Question> selectAll();

    PageInfo query(QuestionQueryObject qo);

    void updateBrowseAnsave(Question question);
}
