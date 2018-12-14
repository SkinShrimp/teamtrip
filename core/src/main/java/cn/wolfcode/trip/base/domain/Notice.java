package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Notice {
    public static final int STATUS_ON = 1;
    public static final int STATUS_OFF = 0;

    private Long id;
    private String content;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date noticeTime;
    private Travel travel;
    private User user;
    private Integer status=Notice.STATUS_ON;
    private Integer flag;
}