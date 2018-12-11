package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 游记推荐
 */
@Setter
@Getter
public class TravelCommend extends BaseDomain{

    public static final int TYPE_WEEK = 1;//每周推荐
    public static final int TYPE_MONTH = 2;//每月推荐
    public static final int TYPE_STRATEGY = 3;//攻略推荐

    //关联游记
    private Travel travel;
    //标题
    private String title;
    //副标题
    private String subTitle;
    //封面url
    private String coverUrl;
    //推荐时间安排
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date schedule;
    //推荐类型
    private Integer type;

    public String getTypeName(){
        String temp = "";
        switch (type){
            case TYPE_WEEK:
                temp = "每周推荐";
                break;
            case TYPE_MONTH:
                temp = "每月推荐";
                break;
            case TYPE_STRATEGY:
                temp = "攻略推荐";
                break;
        }
        return temp;
    }

    public String getJson(){
        HashMap<String, Object> map = new HashMap();
        map.put("id",id);
        map.put("title",title);
        map.put("subTitle",subTitle);
        map.put("coverUrl",coverUrl);
        map.put("travelId",travel.getId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        map.put("schedule",dateFormat.format(schedule));
        map.put("type",type);
        return JSONUtils.toJSONString(map);
    }
}