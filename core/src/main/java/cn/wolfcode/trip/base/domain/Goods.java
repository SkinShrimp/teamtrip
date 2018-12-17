package cn.wolfcode.trip.base.domain;

import com.alibaba.druid.support.json.JSONUtils;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;

@Setter@Getter
public class Goods {
    private Long id;

    private GoodsType type;

    private String name;

    private String picture;

    private String introduce;

    private Integer money;

    private Integer sellcount;


    public String getJson(){
        HashMap<String, Object> map = new HashMap();
        map.put("id",id);
        map.put("name",name);
        map.put("introduce",introduce);
        map.put("picture",picture);
        map.put("money",money);
        map.put("sellcount",sellcount);
        if(type!=null) {
            map.put("goodsTypeId", type.getId());
            map.put("goodsType", type.getGoodsType());
        }

        return JSONUtils.toJSONString(map);
    }


}