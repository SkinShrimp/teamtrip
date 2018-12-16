package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Response;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import cn.wolfcode.trip.base.query.ResponseQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResponseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Response record);

    Response selectByPrimaryKey(Long id);

    List<Response> selectAll();

    int updateByPrimaryKey(Response record);

    List selectForList(ResponseQueryObject qo);

    //查询总条数
    int ResponseCount(@Param("questionId") Long questionId);
}