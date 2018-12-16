package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Question;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Question record);

    Question selectByPrimaryKey(@Param("id") Long id);

    List<Question> selectAll();

    int updateByPrimaryKey(Question record);

    List selectForList(QuestionQueryObject qo);

    Question selectBrowse(@Param("id") Long id);

    //修改浏次数
    void updateBrowse(Question question);
}