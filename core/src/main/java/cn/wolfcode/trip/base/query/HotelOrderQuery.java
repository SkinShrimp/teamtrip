package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelOrderQuery extends QueryObject {
    private Integer state;//状态
    private Long userId;//用户id
}
