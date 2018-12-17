package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Expressage;
import java.util.List;

public interface ExpressageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Expressage record);

    Expressage selectByPrimaryKey(Long id);

    List<Expressage> selectAll();

    int updateByPrimaryKey(Expressage record);
}