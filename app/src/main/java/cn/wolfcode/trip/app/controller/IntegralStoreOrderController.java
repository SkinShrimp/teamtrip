package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Expressage;
import cn.wolfcode.trip.base.domain.IntegralStoreOrder;
import cn.wolfcode.trip.base.query.HotelOrderQuery;
import cn.wolfcode.trip.base.query.IntegralStoreOrderQuery;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IIntegralStoreOrderService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class IntegralStoreOrderController {

    @Autowired
    private IIntegralStoreOrderService integralStoreOrderService;


    /**
     * 查询所有的快递
     * @return
     */
    @RequestMapping("/expressages")
    public List<Expressage> listExpressages(){
        return integralStoreOrderService.listExpressage();
    }

    /**
     * 支付密码认证
     * @param password
     * @return
     */
    @PostMapping("/verifyPassword")
    public JsonResult verifyPassword(String password){

        JsonResult jsonResult = new JsonResult();
        try {
            integralStoreOrderService.login(password);
        }catch (RuntimeException e){
            jsonResult.mark(e.getMessage());

        }
        return jsonResult;
    }


    /**
     * 生成订单,保存到数据库中
     * @param
     * @return
     */
    @PostMapping()
    public JsonResult savaOrder(Long goodsId,Integer count,Integer totalPrice){

        JsonResult jsonResult = new JsonResult();

        integralStoreOrderService.savaOrder(goodsId,count,totalPrice);

        return jsonResult;
    }

    /**
     * 查询用户所有的未指定状态订单
     * @return
     */
    @GetMapping("/{userId}")
    public PageInfo list(HotelOrderQuery qo ){
        PageInfo list = integralStoreOrderService.list(qo);
        return list;
    }

    /**
     * 根据用户查询订单
     */
   /* @GetMapping("/{userId}")
    public PageInfo query(IntegralStoreOrderQuery qo){
        return integralStoreOrderService.query(qo);

    }*/
}
