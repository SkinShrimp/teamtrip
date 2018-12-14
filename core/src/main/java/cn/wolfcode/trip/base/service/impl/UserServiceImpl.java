package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

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
}
