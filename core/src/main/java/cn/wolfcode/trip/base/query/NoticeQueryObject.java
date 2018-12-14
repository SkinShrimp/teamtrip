package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class NoticeQueryObject extends QueryObject {
    private Integer status;  //状态
    private Long userId;  //发送者id
    private Long travelId;  //发送者id
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeTime;   //发送时间
    private Integer flag;   //0是普通通知，1是系统通知
}
