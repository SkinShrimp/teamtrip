package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户私信数据表
 */
@Data
public class UserChat extends BaseDomain{
    public static final int STATUS_ON = 1;
    public static final int STATUS_OFF = 0;

    private User sender;  //发送者
    private User receiver;  //接收者
    private String message;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sendTime;
    private Integer status = STATUS_OFF;  //设置状态，默认为1
}