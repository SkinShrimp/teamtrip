package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter@Getter
public class Question extends BaseDomain{
    //提问内容
    private String content;
    //浏览次数默认0次浏览
    private Integer browse=0;
    //标题
    private String title;
    //用户id
    private User user;
    //用户自定义地区
    private String region;
    //发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date releaseTime;
    //问题内容图片
    private String imgUrls;

}