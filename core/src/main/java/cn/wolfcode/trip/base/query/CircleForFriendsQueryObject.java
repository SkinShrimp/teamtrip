package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircleForFriendsQueryObject extends QueryObject {
    //用户id
    private Long userId;
    private Long loginUserId;
}
