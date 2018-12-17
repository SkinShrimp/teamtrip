package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.*;
import cn.wolfcode.trip.base.mapper.ExpressageMapper;
import cn.wolfcode.trip.base.mapper.IntegralStoreOrderMapper;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.IntegralStoreOrderQuery;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IIntegralStoreOrderService;
import cn.wolfcode.trip.base.util.UserContext;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntegralStoreOrderServiceImpl implements IIntegralStoreOrderService {

    @Autowired
    private IntegralStoreOrderMapper integralStoreOrderMapper;

    @Autowired
    private ExpressageMapper expressageMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Expressage> listExpressage() {
        return expressageMapper.selectAll();
    }

    @Override
    public void login(String password) {
        String email = UserContext.getUser().getEmail();
        User user = userMapper.selectByEmailAndPassword(email, password);
        if(user==null){
            throw new RuntimeException("支付密码错误");
        }
    }

    @Override
    public void savaOrder(Long goodsId, Integer count, Integer totalPrice) {

        //商品
        Goods goods = new Goods();
        goods.setId(goodsId);

        //客户
        Customer customer = new Customer();
        customer.setId(UserContext.getUser().getId());

        IntegralStoreOrder integralStoreOrder = new IntegralStoreOrder(null,goods,customer,
                        totalPrice,new Date(),false,count);

        //保存订单
        integralStoreOrderMapper.insert(integralStoreOrder);
    }

    @Override
    public PageInfo list(QueryObject qo) {

        //传入状态 ,传入当前用于
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<IntegralStoreOrder> list = integralStoreOrderMapper.selectAll(qo);
        return new PageInfo(list);


    }

    @Override
    public PageInfo query(IntegralStoreOrderQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
        List<IntegralStoreOrder> list = integralStoreOrderMapper.selectForList(qo);
        return new PageInfo(list);
    }
}
