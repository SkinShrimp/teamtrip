package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.domain.UserChat;
import cn.wolfcode.trip.base.mapper.UserChatMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.query.UserChatQueryObject;
import cn.wolfcode.trip.base.service.IUserChatService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserChatImpl implements IUserChatService{

    @Autowired
    private UserChatMapper userChatMapper;

    public void save(UserChat userChat) {
        userChat.setStatus(userChat.STATUS_OFF);
        userChat.setSendTime(new Date());
        userChatMapper.insert(userChat);
    }

    public List<UserChat> listAll() {
        return userChatMapper.selectAll();
    }

    public UserChat getById(Long id) {
        return userChatMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询我私信以及私信过我的用户
     * @return
     */
    public Set listAllAboutMe(UserChatQueryObject qo) {
       /* List<Map> iToOtehers = userChatMapper.selectBySenderId(currentUser.getId());
        System.out.println("iToOtehers:"+iToOtehers);
        List<Map> otehersToIs = userChatMapper.selectByReceiverId(currentUser.getId());
        System.out.println("otehersToIs:"+otehersToIs);*/
        PageHelper.startPage(qo.getCurrentPage(),100,"uc.sendTime desc");
        List<UserChat> list = userChatMapper.selectForList(qo);
        Set<User> result = new HashSet();
        for (UserChat uc : list) {
            User u = new User();
            if(uc.getReceiver().getId() == qo.getAboutMeId()){
                u.setId(uc.getSender().getId());
                u.setNickName(uc.getSender().getNickName());
                u.setHeadImgUrl(uc.getSender().getHeadImgUrl());
            }else {
                u.setId(uc.getReceiver().getId());
                u.setNickName(uc.getReceiver().getNickName());
                u.setHeadImgUrl(uc.getReceiver().getHeadImgUrl());
            }
            result.add(u);
        }
        System.out.println("result:"+result);
        return result;
    }

    public PageInfo queryByOrder(UserChatQueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List list = userChatMapper.selectForListByOrder(qo);
        return new PageInfo(list);
    }

    public Integer getHistoryUnreadNum(Long receiverId) {
        return userChatMapper.selectByReceiverId(receiverId);
    }

    @Override
    public Integer getUnreadsBySenderAndrReceiverId(UserChatQueryObject qo) {
        Integer num = userChatMapper.selectUnreadsBySenderAndrReceiverId(qo);
        return num;
    }

    @Override
    public void update(UserChat uc) {
        userChatMapper.updateByStatus(uc);
    }

    public PageInfo query(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize(),qo.getOrderBy());
        List<UserChat> list = userChatMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
