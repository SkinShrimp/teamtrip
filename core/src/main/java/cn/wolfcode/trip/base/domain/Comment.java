package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long parentId = 0L;
    //评论内容
    private String comment;
    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    //评论类型(游记？攻略)
    private Integer type = NORMAL_TYPE;
    //实体ID（游记或者评论的ID）
    private Long typeId;
    //用户
    private User user;
    //收藏
    private Integer collect;
    //点赞
    private Integer praise;
    //ID节点
    private Long flagId;

}