package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelQueryObject extends QueryObject {
    private String keyword;//关键字
    private Integer starLevel;//星级

}
