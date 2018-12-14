package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class UserChatQueryObject extends QueryObject {
    private Integer status;  //状态
    private Long senderId;  //发送者id
    private Long receiverId;  //接收者id
    private Long aboutMeId;   //查询我私信的和私信过我的用户id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;   //发送时间
}
