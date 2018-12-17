package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.SignInTime;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SignInTimeMapper {

    int deleteByPrimaryKey(Long id);

    SignInTime selectByPrimaryKey(Long id);

    List<SignInTime> selectAll();

    int updateByPrimaryKey(SignInTime record);

    List selectDaysByUserId(Long id);

    void insertByUserName(@Param("userId") Long userId, @Param("date") Date date);

    SignInTime SelectByUserId(@Param("userId") Long userId, @Param("date") Date date);

}