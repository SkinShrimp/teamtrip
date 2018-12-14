package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hotel extends BaseDomain{

    private String hotelRegion;//酒店位置

    private String name;//酒店名称

    private String pic;//图片

    private String distance;//距离

    private Integer starLevel;//星级

    private Long price;//价格

}