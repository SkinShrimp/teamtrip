package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Focus;
import java.util.List;

public interface FocusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Focus record);

    Focus selectByPrimaryKey(Long id);

    List<Focus> selectAll();

    int updateByPrimaryKey(Focus record);

    void deleteByFocus(Focus focus);

    Integer selectCountByFocuseeId(Long id);

    Integer selectCountByFocuserIdAndFocuseeId(Focus focus);
}