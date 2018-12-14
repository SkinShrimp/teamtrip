package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.domain.UserChat;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.UserChatQueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface IUserChatService {
    /**
     * 分页
     * @param qo
     * @return
     */
    PageInfo query(QueryObject qo);

    /**
     * 新增和编辑
     * @param userChat
     */
    void save(UserChat userChat);

    /**
     * 查询全部
     * @return
     */
    List<UserChat> listAll();

    /**
     * 根据id查询攻略
     * @param id
     * @return
     */
    UserChat getById(Long id);

    Set listAllAboutMe(UserChatQueryObject qo);

    PageInfo queryByOrder(UserChatQueryObject qo);

    Integer getHistoryUnreadNum(Long receiverId);

    Integer getUnreadsBySenderAndrReceiverId(UserChatQueryObject qo);

    void update(UserChat uc);
}
