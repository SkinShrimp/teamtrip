package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter@Getter
public class CircleForFriends extends BaseDomain{
    //用户id
    private User user;
    //评论内容
    private String content;
    //评论图片
    private String imgUrls;
    //评论时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:dd:SSS")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:dd:SSS",timezone = "GMT+8")
    private Date releaseTime;

}