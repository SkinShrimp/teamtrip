package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PraiseCollect extends BaseDomain {
    //点赞标志
    private Integer praise;
    //收藏标志
    private Integer collect;
    //类型标志 1:微信相关
    private Integer typeFlag = 0;
    //相关用户ID
    private Long typeId;
    //父节点的ID
    private Long parentId;

}