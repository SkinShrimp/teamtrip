package cn.wolfcode.trip.app.controller;


import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.domain.Strategy;
import cn.wolfcode.trip.base.query.StrategyQueryObject;
import cn.wolfcode.trip.base.service.IRegionService;
import cn.wolfcode.trip.base.service.IStrategyService;
import com.github.pagehelper.PageInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 地区资源控制器
 */
@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IStrategyService strategyService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping
    public List<Region> listAll(Integer state){

        return regionService.listAll(state);
    }

    @GetMapping(value="/{regionId}/strategies",produces = "text/html;charset=utf-8" )
    public void queryStrategiesByRegionId(StrategyQueryObject qo, HttpServletResponse response) throws Exception {
        //查询该地区的所有状态为推荐的攻略(不分页)
        qo.setState(Strategy.STATE_HOT);
        qo.setPageSize(0);
        PageInfo hot = strategyService.query(qo);

        //查询该地区的所有攻略(分页)
        qo.setState(null);
        qo.setPageSize(4);
        PageInfo all = strategyService.query(qo);

        //创建freemarker的配置对象
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        //设置模板所在目录
        config.setDirectoryForTemplateLoading(new File(servletContext.getRealPath("/template")));
        //设置编码
        config.setDefaultEncoding("utf-8");
        //获取模板
        Template template = config.getTemplate("strategyTemplate.ftl");

        HashMap<String, Object> map = new HashMap<>();
        map.put("hot",hot.getList());
        map.put("all",all.getList());

        //模板和数据合成
        response.setContentType("text/html;charset=utf-8");
        template.process(map,response.getWriter());
    }


    @GetMapping(value="/{regionId}/strategies",produces = "application/json;charset=utf-8" )
    public PageInfo queryStrategiesByRegionId(StrategyQueryObject qo)  {
        qo.setPageSize(4);
        return strategyService.query(qo);
    }
}
