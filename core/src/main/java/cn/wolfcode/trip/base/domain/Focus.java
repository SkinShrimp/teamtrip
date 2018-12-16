package cn.wolfcode.trip.base.domain;

import lombok.Data;

@Data
public class Focus extends BaseDomain{
    Long focuserId;  //关注者
    Long focuseeId;  //被关注着
    Integer flag;    //标志
}
