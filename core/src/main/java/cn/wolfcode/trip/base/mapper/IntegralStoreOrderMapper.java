package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.IntegralStoreOrder;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.IntegralStoreOrderQuery;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralStoreOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralStoreOrder record);

    IntegralStoreOrder selectByPrimaryKey(Long id);

    List<IntegralStoreOrder> selectAll(QueryObject qo);

    List<IntegralStoreOrder> selectForList(IntegralStoreOrderQuery qo);
}