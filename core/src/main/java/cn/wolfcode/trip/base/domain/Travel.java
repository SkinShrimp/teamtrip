package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;

/**
 * 游记
 */
@Setter
@Getter
public class Travel extends BaseDomain{

    public static final int STATE_NORMAL = 0;//草稿
    public static final int STATE_AUDIT = 1;//待审核
    public static final int STATE_RELEASE = 2;//已发布
    public static final int STATE_REJECT = -1;//拒绝

    //标题
    private String title;
    //出行时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date travelTime;
    //人均消费
    private String perExpends;
    //旅游天数
    private Integer days;
    //和谁旅游
    private Integer person;
    //作者
    private User author;
    //创建时间
    private Date createTime;
    //发布时间
    private Date releaseTime;
    //是否公开
    private Boolean isPublic;
    //地区
    private Region place;
    //封面url
    private String coverUrl;
    //最后更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastUpdateTime;
    //状态
    private Integer state = STATE_NORMAL;
    //游记内容
    private TravelContent travelContent;


    public String getStateName(){
        String temp = "";
        switch (state){
            case STATE_AUDIT:
                temp = "待审核";
                break;
            case STATE_RELEASE:
                temp = "已发布";
                break;
            case STATE_REJECT:
                temp = "拒绝";
                break;
            default:
                temp = "草稿";
        }
        return temp;
    }

    public String getPersonName(){
        String temp = "";
        switch (state){
            case 1:
                temp = "一个人的旅行";
                break;
            case 2:
                temp = "和父母";
                break;
            case 3:
                temp = "和朋友";
                break;
            case 4:
                temp = "和同事";
                break;
            case 5:
                temp = "和爱人";
                break;
            default:
                temp = "和其他";
        }
        return temp;
    }


    public String getJson(){
        HashMap<String, Object> map = new HashMap();
        map.put("id",id);
        map.put("title",title);
        map.put("coverUrl",coverUrl);
        if(author != null){
            map.put("authorId",author.getId());
        }
        return JSONUtils.toJSONString(map);
    }
}