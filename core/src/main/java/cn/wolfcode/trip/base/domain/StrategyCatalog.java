package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 攻略分类
 */
@Setter
@Getter
@JsonIgnoreProperties("handler")//转json数据时不要序列化handler属性
public class StrategyCatalog extends BaseDomain{

    //名称
    private String name;
    //所属攻略
    private Strategy strategy;
    //序号
    private Integer sequence;
    //状态
    private Boolean state;
    //攻略文章集合
    private List<StrategyDetail> details = new ArrayList<StrategyDetail>();

    public String getStateName(){
        return state?"启用":"禁用";
    }

    public String getJson(){
        HashMap<String, Object> map = new HashMap();
        map.put("id",id);
        map.put("name",name);
        map.put("sequence",sequence);
        map.put("state",state);
        if(strategy!=null) {
            map.put("strategyId", strategy.getId());
            map.put("strategyTitle", strategy.getTitle());
        }
        return JSONUtils.toJSONString(map);
    }


}