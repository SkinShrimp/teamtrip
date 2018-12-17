package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter@Getter
public class SignInTime extends BaseDomain{

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date signTime;


}