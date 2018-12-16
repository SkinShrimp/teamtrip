package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PraiseCollectQueryObject extends QueryObject {
    //确定是谁的日记
    private Integer typeFlag;
    private Long typeId;
    private Long parentId;
}
