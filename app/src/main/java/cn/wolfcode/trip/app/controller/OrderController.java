package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Order;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IOrderService;
import cn.wolfcode.trip.base.util.JsonResult;
import cn.wolfcode.trip.base.util.PaymentUtil;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

@Controller
public class OrderController {
@Autowired
private IOrderService service;
@RequestMapping("order/submit")
public void submitOrder(Order order, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

  //1、private String oid;//该订单的订单号
  //随机数
  String oid = UUID.randomUUID().toString();
  order.setOid(oid);

  //2、private Date ordertime;//下单时间
  //当前时间
  order.setOrdertime(new Date());

  //3、private double total;//该订单的总金额
  order.setTotal(0.01D);

  //4、private int state;//订单支付状态 1代表已付款 0代表未付款
  order.setState(0);

  //5、private String address;//收货地址
  //固定
  order.setAddress("棠下");

  //6、private String name;//收货人
  //固定
  order.setName("小二");

  //7、private String telephone;//收货人电话
  order.setTelephone("18888888888");

  //8、private User user;//该订单属于哪个用户
  User currentUser = UserContext.getUser();
  User user = new User();
  user.setId(currentUser.getId());
  user.setNickName(currentUser.getNickName());
  order.setUser(user);
  service.saveOrder(order);
  //TODO返回到页面
  //再进行订单支付
  //页面跳转
  request.setAttribute("order", order);
  request.getRequestDispatcher(request.getContextPath()+"/tripProduct/order_detail.jsp").forward(request,response);
//  response.sendRedirect(request.getContextPath()+"/tripProduct/order_detail.jsp");

}

@RequestMapping("order/confirm")
public void confirmOrder(Order order, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  System.out.println("=============================order/confirm");

  //本应该从数据库查该订单再更改订单的状态出来
  //确认订单是页面传入form表单参数收货人地址姓名等属性
service.update(order);

//service.updateOrderState(order.getOid());

  //2、在线支付
		/*if(pd_FrpId.equals("ABC-NET-B2C")){
			//介入农行的接口
		}else if(pd_FrpId.equals("ICBC-NET-B2C")){
			//接入工行的接口
		}*/
  //.......

  //只接入一个接口，这个接口已经集成所有的银行接口了  ，这个接口是第三方支付平台提供的
  //接入的是易宝支付
  // 获得 支付必须基本数据

  //String money = order.getTotal()+"";//支付金额
  //TODO
  String money = "0.01";//支付金额
  // 银行的接口
  String pd_FrpId = request.getParameter("pd_FrpId");

  // 发给支付公司需要哪些数据
  String p0_Cmd = "Buy";
  String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
  String p2_Order = order.getOid();
  String p3_Amt = money;
  String p4_Cur = "CNY";
  String p5_Pid = "";
  String p6_Pcat = "";
  String p7_Pdesc = "";
  // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
  // 第三方支付可以访问网址
  String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("callback");
  String p9_SAF = "";
  String pa_MP = "";
  String pr_NeedResponse = "1";
  // 加密hmac 需要密钥
  String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
          "keyValue");
  String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
          p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
          pd_FrpId, pr_NeedResponse, keyValue);


  String url = "https://www.yeepay.com/app-merchant-proxy/node?pd_FrpId="+pd_FrpId+
          "&p0_Cmd="+p0_Cmd+
          "&p1_MerId="+p1_MerId+
          "&p2_Order="+p2_Order+
          "&p3_Amt="+p3_Amt+
          "&p4_Cur="+p4_Cur+
          "&p5_Pid="+p5_Pid+
          "&p6_Pcat="+p6_Pcat+
          "&p7_Pdesc="+p7_Pdesc+
          "&p8_Url="+p8_Url+
          "&p9_SAF="+p9_SAF+
          "&pa_MP="+pa_MP+
          "&pr_NeedResponse="+pr_NeedResponse+
          "&hmac="+hmac;

//  //重定向到第三方支付平台
  System.out.println(order);
  System.out.println(order.getOid());
  System.out.println("===============================end");
  response.sendRedirect(url);

}

/*
//提交订单
public void submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  HttpSession session = request.getSession();

  //判断用户是否已经登录 未登录下面代码不执行
  User user = (User) session.getAttribute("user");
  if(user==null){
    //没有登录
    response.sendRedirect(request.getContextPath()+"/login.jsp");
    return;
  }


  //目的：封装好一个Order对象 传递给service层
  Order order = new Order();

  //1、private String oid;//该订单的订单号
  String oid = CommonsUtils.getUUID();
  order.setOid(oid);

  //2、private Date ordertime;//下单时间
  order.setOrdertime(new Date());

  //3、private double total;//该订单的总金额
  //获得session中的购物车
  Cart cart = (Cart) session.getAttribute("cart");
  double total = cart.getTotal();
  order.setTotal(total);

  //4、private int state;//订单支付状态 1代表已付款 0代表未付款
  order.setState(0);

  //5、private String address;//收货地址
  order.setAddress(null);

  //6、private String name;//收货人
  order.setName(null);

  //7、private String telephone;//收货人电话
  order.setTelephone(null);

  //8、private User user;//该订单属于哪个用户
  order.setUser(user);

  //9、该订单中有多少订单项List<OrderItem> orderItems = new ArrayList<OrderItem>();
  //获得购物车中的购物项的集合map
  Map<String, CartItem> cartItems = cart.getCartItems();
  for(Map.Entry<String, CartItem> entry : cartItems.entrySet()){
    //取出每一个购物项
    CartItem cartItem = entry.getValue();
    //创建新的订单项
    OrderItem orderItem = new OrderItem();
    //1)private String itemid;//订单项的id
    orderItem.setItemid(CommonsUtils.getUUID());
    //2)private int count;//订单项内商品的购买数量
    orderItem.setCount(cartItem.getBuyNum());
    //3)private double subtotal;//订单项小计
    orderItem.setSubtotal(cartItem.getSubtotal());
    //4)private Product product;//订单项内部的商品
    orderItem.setProduct(cartItem.getProduct());
    //5)private Order order;//该订单项属于哪个订单
    orderItem.setOrder(order);

    //将该订单项添加到订单的订单项集合中
    order.getOrderItems().add(orderItem);
  }


  //order对象封装完毕
  //传递数据到service层
  ProductService service = new ProductService();
  service.submitOrder(order);


  session.setAttribute("order", order);
*/

  @RequestMapping(value = "/queryOrder/{id}")
  @ResponseBody
  public JsonResult queryOrder(@PathVariable Long id){
    JsonResult result = new JsonResult();
    try {
      List<Order> list = service.queryOrder(id);
      result.setResult(list);
    } catch (Exception e) {
      e.printStackTrace();
      result.mark(e.getMessage());
    }
    return result;
  }
}
