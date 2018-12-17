package cn.wolfcode.trip.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class IntegralStoreOrder extends BaseDomain {


    private Expressage expressage;

    private Goods goods;

    private Customer customer;

    private Integer totalMoney;

    private Date date;

    private Boolean state;

    private Integer count;


}