package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.IntegralStoreOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralStoreOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IntegralStoreOrder record);

    IntegralStoreOrder selectByPrimaryKey(Long id);

    List<IntegralStoreOrder> selectAll(@Param("status") Boolean status ,@Param("userId") long userId);

}