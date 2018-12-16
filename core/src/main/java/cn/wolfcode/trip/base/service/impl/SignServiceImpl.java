package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.SignIn;
import cn.wolfcode.trip.base.mapper.SignInMapper;
import cn.wolfcode.trip.base.service.ISignInTimeService;
import cn.wolfcode.trip.base.service.ISignService;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SignServiceImpl implements ISignService {

    @Autowired
    private SignInMapper signInMapper;

    @Autowired
    private ISignInTimeService signInTimeService;

    //获取当前用户id


    public SignIn get() {
        Long UserId = UserContext.getUser().getId();
        Date date =new Date();
        return signInMapper.SelectByUserId(UserId,null);
    }

    public void update() {

        Long UserId = UserContext.getUser().getId();
        Date date =new Date();
        //数据表中没有今天的数据,才可以进行更新
        SignIn signIn = signInMapper.SelectByUserId(UserId, date);

            if(signIn==null){

                //更新这次签到的时间和上传签到的时间
                signInMapper.updateSignTimeByUserId(UserId,date);

                //如果上一次签到的时间是昨天
                SignIn signIn1 = signInMapper.isContinuous();
                if(signIn1!=null){
                // 连续签到的天数加一,积分加一
                    signInMapper.updateIntegralAndContinuousByUserId(UserId);

                //如果上一次签到的时间不是昨天,将连续签到的更新为1,积分加一
                }else {
                    signInMapper.resetContinuous(UserId);
                }

            }


    }

    public SignIn getByUserId() {

        Long UserId = UserContext.getUser().getId();
        Date date =new Date();
        return  signInMapper.SelectByUserId(UserId, date);
    }

    public Integer getIntegral(Long userId) {
        return signInMapper.selectIntegral(userId);
    }
}
