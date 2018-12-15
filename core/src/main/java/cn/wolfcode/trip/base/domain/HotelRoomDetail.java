package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelRoomDetail extends BaseDomain{

    private Hotel hotel;//酒店

    private String roomName;//房间名称

    private Long price;//房间价格

    private String roomPic;//房间图片

    private String roomArea;//房间大小

}