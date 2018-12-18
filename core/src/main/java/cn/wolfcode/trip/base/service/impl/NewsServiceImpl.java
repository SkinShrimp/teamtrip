package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.CircleForFriends;
import cn.wolfcode.trip.base.query.CircleForFriendsQueryObject;
import cn.wolfcode.trip.base.service.INewsService;
import cn.wolfcode.trip.base.util.SpiderUtil;
import com.github.pagehelper.PageInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements INewsService {

    public List getContent(Integer page) {
        List<Map<String,Object>> list = new ArrayList<>();

        String html = SpiderUtil.Connect("https://www.pengfu.com/xiaohua_" + page + ".html");
        // 将内容转换成dom格式，方便操作
        Document doc = Jsoup.parse(html);
        // 获取网页内所有标题节点
        Elements titles = doc.select("h1.dp-b");
        for (Element titleEle : titles) {
            Map<String,Object> map = new HashMap<>();
            Element parent = titleEle.parent();
            // 标题内容
            String title = titleEle.getElementsByTag("a").text();
            // 标题对应的作者
            //String author = parent.select("p.user_name_list > a").text();
            // 标题对应的正文
            String content = parent.select("div.content-img").text();
            map.put("title",title);
            map.put("content",content);
            list.add(map);
        }
        return list;
    }
}
