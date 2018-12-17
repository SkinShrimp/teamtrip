package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date date;

    private Boolean state;

    private Integer count;


}