package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Customer extends BaseDomain{

    private Long userId;

    private String name;

    private String address;

    private Long phone;

    private String email;


}