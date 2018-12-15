package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentQueryObject extends QueryObject {
    private Integer type;
    private Long typeId;
    private Long parentId;
}
