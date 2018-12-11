package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 攻略评论
 */
@Setter
@Getter
public class StrategyComment extends BaseDomain{
    public static final int STATE_NORMAL = 0;//正常
    public static final int STATE_HOT = 1;//推荐
    public static final int STATE_DISABLE = -1;//禁用

    //点评者
    private User user;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
    //点评内容
    private String content;
    //图片urls
    private String imgUrls;
    //星星数量
    private Integer starNum;
    //所属攻略
    private Strategy strategy;
    //状态
    private Integer state = STATE_NORMAL;
    //推荐时间
    private Date commendTime;

    //返回图片的数组
    public String[] getImgArr(){
        if(StringUtils.hasLength(imgUrls)){
            return imgUrls.split(";");
        }
        return null;
    }

}