package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.domain.Response;
import cn.wolfcode.trip.base.query.QuestionQueryObject;
import cn.wolfcode.trip.base.query.ResponseQueryObject;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IResponseService {

    int insert(Response record);

    Response selectByPrimaryKey(Long id);

    List<Response> selectAll();

    int updateByPrimaryKey(Response record);

    PageInfo query(ResponseQueryObject qo);

    //查询总条数
    int ResponseCount(Long questionId);

}
