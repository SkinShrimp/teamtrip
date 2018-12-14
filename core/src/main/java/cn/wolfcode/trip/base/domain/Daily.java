package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日报
 */
@Getter
@Setter
public class Daily extends BaseDomain {

    public static final int STATE_NORMAL = 1;//普通
    public static final int STATE_DISABLE = 2;//禁用

    //标题
    private String title;
    //副标题
    private String subTitle;
    //封面
    private String coverUrl;
    //创建时间
    private Date createTime;
    //状态
    private Integer state = STATE_NORMAL;

    //日报内容
    private DailyContent content;

    //状态显示
    public String getStateName() {
        String state = "";
        if (this.state == STATE_NORMAL) {
            state = "普通";
        } else if (this.state == STATE_DISABLE) {
            state = "禁用";
        }
        return state;
    }

    //返回json数据
    public String getJson() {
        Map<String, Object> map = new HashMap();
        map.put("id",id);
        map.put("title",title);
        map.put("subTitle",subTitle);
        map.put("coverUrl",coverUrl);
        map.put("state",state);
        return JSONUtils.toJSONString(map);
    }


}