package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Focus;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.domain.UserStrategy;
import cn.wolfcode.trip.base.mapper.FocusMapper;
import cn.wolfcode.trip.base.mapper.StrategyMapper;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.mapper.UserStrategyMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.UserChatQueryObject;
import cn.wolfcode.trip.base.query.UserStrategyQueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FocusMapper focusMapper;
    @Autowired
    private UserStrategyMapper userStrategyMapper;
    @Autowired
    private StrategyMapper strategyMapper;

    public void register(User user) {
        //检查邮箱是否已经被注册
        User temp = userMapper.selectByEmailAndPassword(user.getEmail(), null);
        if(temp!=null){
            throw new RuntimeException("亲,邮箱已被注册!");
        }
        //设置默认头像封面性别
        user.setHeadImgUrl("/img/user/head.jpg");
        user.setCoverImgUrl("/img/user/bg.jpeg");
        user.setGender(User.SECRET);
        //保存到数据库
        userMapper.insert(user);
    }

    public User login(String email, String password) {
        User user = userMapper.selectByEmailAndPassword(email, password);
        if(user==null){
            throw new RuntimeException("亲,邮箱和密码不匹配!");
        }
        //把user放到session
        UserContext.setUser(user);
        return user;
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<User> list = userMapper.selectForList(qo);
        return new PageInfo(list);
    }

    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    public User get(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void focus(Focus focus) {
        if(focus.getFlag() == 1){
            focusMapper.insert(focus);
        }else {
            focusMapper.deleteByFocus(focus);
        }
    }

    public Integer getFocuses(Long id) {
        return focusMapper.selectCountByFocuseeId(id);
    }

    public Integer checkFocusRelation(Focus focus) {
        return focusMapper.selectCountByFocuserIdAndFocuseeId(focus);
    }

    public void join(UserStrategy uc) {
        uc.setJoinTime(new Date());
        userStrategyMapper.insert(uc);
    }

    public PageInfo getAllStrategiesByUserId(UserStrategyQueryObject qo) {
        qo.setOrderBy("us.joinTime desc");
        //qo.setPageSize(10);
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<Map<String,Object>> list = userStrategyMapper.selectByUserId(qo);
        return new PageInfo(list);
    }

    public void deleteUserStrategy(Long id) {
        userStrategyMapper.deleteByPrimaryKey(id);
    }
}
