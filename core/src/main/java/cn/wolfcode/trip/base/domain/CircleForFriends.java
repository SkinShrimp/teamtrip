package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.util.Date;

@Setter
@Getter
public class CircleForFriends extends BaseDomain {
    //用户id
    private User user;
    //评论内容
    private String content;
    //评论图片
    private String imgUrls;
    //评论时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date releaseTime;

    private PraiseCollect praiseCollect = new PraiseCollect();

    //返回图片的数组
    public String[] getImgArr() {
        if (StringUtils.hasLength(imgUrls)) {
            return imgUrls.split(";");
        }
        return null;
    }

}