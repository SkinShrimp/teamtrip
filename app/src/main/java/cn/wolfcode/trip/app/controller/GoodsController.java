package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Goods;
import cn.wolfcode.trip.base.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goodses")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/{id}")
    public Goods get(Long id){
        return goodsService.getGoodsById(id);
    }
}