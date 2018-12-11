package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TravelQueryObject extends QueryObject {
    private Long authorId;//作者id
    private Integer state;//状态
    private Boolean isPublic;//是否公开
    private Long travelId;//游记id
}
