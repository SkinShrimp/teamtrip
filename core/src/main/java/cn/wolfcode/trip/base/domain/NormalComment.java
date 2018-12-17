package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter@Setter
public class NormalComment extends BaseDomain{
    //回复方
    private User user;
    //被回复方
    private User acceptUser;
    //内容
    private String comment;
    //评论时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;
    //类型
    private Integer type;
    //主体id 2:代表朋友圈的评论
    private Long typeId;
    //最外层父节点
    private Long parentId;
    //标识
    private Long flagId;

}