package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter@Setter
public class NormalComment extends BaseDomain{

    //回复方
    private User user;
    //被回复方
    private User acceptuser;
    //内容
    private String comment;
    //评论时间
    private Date createTime;
    //类型 问答类型为:1
    private Integer type;
    //主体id
    private Long typeId;
    //最外层父节点
    private Long parentId;
    //标识
    private Long flagId;



}