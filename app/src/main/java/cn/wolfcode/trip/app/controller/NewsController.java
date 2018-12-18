package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 段子控制器
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private INewsService newsService;

    @GetMapping
    public List getById(Integer page){
        return newsService.getContent(page);
    }
}
