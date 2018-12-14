package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyQueryObject extends QueryObject {

    private Integer state;//状态
    private String keyword;//关键字

}
