package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Comment extends BaseDomain {
    public static final Integer COLLECT_FLAG = 1;//收藏
    public static final Integer COLLECT_NORMAL = 0;//收藏正常
    public static final Integer PRAISE_FLAG = 1;//点赞
    public static final Integer PRAISE_NORMAL = 0;//点赞正常

    public static final Integer NORMAL_TYPE = 0;//基本状态
    public static final Integer TRAVEL_TYPE = 1;//旅游游记评论
    public static final Integer STRATEGY_TYPE = 2;//攻略评论

    //父节点ID
    private Integer parentId;
    //评论内容
    private String comment;
    //创建时间
    private Date createTime;
    //评论类型
    private Integer type = NORMAL_TYPE;
    //实体ID
    private Integer typeId;
    //用户
    private User user;
    //收藏
    private Integer collect;
    //点赞
    private Integer praise;

}