package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter
public class SignIn extends BaseDomain{

    private User user;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date signTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date lastSign;

    private Integer integral;

    private Integer continuous;


}