package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;

/**
 * 攻略文章
 */
@Setter
@Getter
public class StrategyDetail extends BaseDomain{

    public static final int STATE_NORMAL = 0;//草稿
    public static final int STATE_RELEASE = 1;//发布
    public static final int STATE_DISABLE = -1;//禁用

    //标题
    private String title;
    //创建时间
    private Date createTime;
    //发布时间
    private Date releaseTime;
    //序号
    private Integer sequence;
    //所属分类
    private StrategyCatalog catalog;
    //封面
    private String coverUrl;
    //状态
    private Integer state = STATE_NORMAL;
    //攻略内容
    private StrategyContent strategyContent;

    public String getJson(){
        HashMap<String, Object> map = new HashMap();
        map.put("id",id);
        map.put("title",title);
        map.put("sequence",sequence);
        map.put("coverUrl",coverUrl);
        map.put("state",state);
        if(catalog!=null) {
            map.put("catalogId", catalog.getId());
            map.put("catalogName", catalog.getName());
            if (catalog.getStrategy()!=null){
                map.put("strategyId", catalog.getStrategy().getId());
            }
        }

        return JSONUtils.toJSONString(map);
    }

    public String getStateName(){
        String temp = "";
        switch (state){
            case STATE_NORMAL:
                temp = "草稿";
                break;
            case STATE_RELEASE:
                temp = "发布";
                break;
            default:
                temp = "禁用";
        }
        return temp;
    }

}