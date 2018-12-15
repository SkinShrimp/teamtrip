package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Focus;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;

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

    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User get(Long id);

    void focus(Focus focus);

    Integer getFocuses(Long id);

    Integer checkFocusRelation(Focus focus);
}
