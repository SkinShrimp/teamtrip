package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Region extends BaseDomain{

    public static final int STATE_NORMAL = 0;//普通
    public static final int STATE_HOT = 1;//热门

    //名称
    private String name;
    //上级地区
    private Region parent;
    //状态
    private Integer state = STATE_NORMAL;

    public Map toTreeMap(){
        HashMap<String, Object> map = new HashMap();
        map.put("id",this.getId());
        map.put("text",this.getName());
        map.put("lazyLoad",true);//该节点开启懒加载
        //判断是否是热门状态
        if(this.state==STATE_HOT) {
            map.put("tags", new String[]{"推荐"});
        }
        return map;
    }

    public String getJson(){
        HashMap<String, Object> map = new HashMap();
        map.put("id",this.getId());
        map.put("name",this.getName());
        map.put("state",state);
        if(parent!=null){
            map.put("parentId",parent.getId());
            map.put("parentName",parent.getName());
        }
        return JSONUtils.toJSONString(map);
    }
}