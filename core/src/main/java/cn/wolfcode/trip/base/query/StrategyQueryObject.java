package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StrategyQueryObject extends QueryObject{
    private Integer state;//状态
    private Long regionId;//地区id
    private String keyword;//关键字
}
