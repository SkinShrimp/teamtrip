package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Expressage;
import cn.wolfcode.trip.base.domain.IntegralStoreOrder;
import cn.wolfcode.trip.base.service.IIntegralStoreOrderService;
import cn.wolfcode.trip.base.util.JsonResult;
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
    @GetMapping
    public List<IntegralStoreOrder> list(){
        List list = integralStoreOrderService.list(false);
        return list;
    }
}