package cn.wolfcode.trip.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//门票
public class Order {

  private  Long id;
  private String oid;//该订单的订单号
  private Date ordertime;//下单时间
  private double total;//该订单的总金额
  private int state;//订单支付状态 1代表已付款 0代表未付款

  private String address;//收货地址
  private String name;//收货人
  private String telephone;//收货人电话

  private User user;//该订单属于哪个用户

  //该订单中有多少订单项
//  List<OrderItem> orderItems = new ArrayList<OrderItem>();
}
