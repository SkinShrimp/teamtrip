package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.SignIn;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SignInMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SignIn record);

    SignIn selectByPrimaryKey(Long id);

    List<SignIn> selectAll();

    int updateByPrimaryKey(SignIn record);

    SignIn SelectByUserId(@Param("userId") Long id,@Param("data") Date date);

    void updateSignTimeByUserId(@Param("userId")Long userId,@Param("date") Date date);

    void updateIntegralAndContinuousByUserId(@Param("userId") Long userId);

    SignIn isContinuous();

    void resetContinuous(@Param("userId")Long userId);

    Integer selectIntegral(@Param("userId") Long userId);
}