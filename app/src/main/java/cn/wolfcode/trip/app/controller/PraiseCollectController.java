package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.domain.PraiseCollect;
import cn.wolfcode.trip.base.query.PraiseCollectQueryObject;
import cn.wolfcode.trip.base.service.IPraiseCollectService;
import cn.wolfcode.trip.base.util.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/praiseCollects")
public class PraiseCollectController {
    @Autowired
    private IPraiseCollectService praiseCollectService;

    @GetMapping("/praise")
    public List<PraiseCollect> getPraise(PraiseCollectQueryObject qo) {
        return praiseCollectService.selectPraiseByTypeId(qo);
    }

    @PostMapping("/praise")
    public JsonResult saveOrUpdatePraise(PraiseCollect entry) {
        praiseCollectService.saveOrUpdatePraise(entry);
        return new JsonResult();
    }

    @GetMapping("/praise/{parenId}")
    public List<String> getPraiseNames(@PathVariable Long parenId) {
        return praiseCollectService.selectPraiseNameByParentId(parenId);
    }
}
