package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StrategyCommentQueryObject extends QueryObject{
    private Long strategyId;//攻略id
    private Long userId;
    //状态 首页点评轮播图
    private Integer state;
}
