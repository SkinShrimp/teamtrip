package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

public interface IUserService {
    /**
     * 注册用户
     * @param user
     */
    void register(User user);

    /**
     * 登陆
     * @param email
     * @param password
     */
    User login(String email,String password);

    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 更新
     * @param user
     */
    void update(User user);
}
