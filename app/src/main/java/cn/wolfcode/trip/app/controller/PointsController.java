package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Goods;
import cn.wolfcode.trip.base.query.GoodsQueryObject;
import cn.wolfcode.trip.base.service.IGoodsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/points")
public class PointsController {

    @Autowired
    private IGoodsService goodsService;

    /**
     * 根据商品类型的id查询指定类的商品
     * @param qo
     * @return
     */
    @RequestMapping("/getTypeId/{typeId}/goodses")
    @ResponseBody
    public PageInfo ListGoodsByTypeId(GoodsQueryObject qo){
        return goodsService.query(qo);

    }

    /**
     * 获取指定商品的信息
     * @param id
     * @return
     */
    @RequestMapping("/getGoodsById/{id}/goodses")
    @ResponseBody
    public Goods getGoodsById(@PathVariable Long id){
        return goodsService.getGoodsById(id);
    }
}
