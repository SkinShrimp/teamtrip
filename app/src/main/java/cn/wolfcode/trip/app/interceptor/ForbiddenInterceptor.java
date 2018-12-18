package cn.wolfcode.trip.app.interceptor;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.util.FilterUtil;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *敏感词过滤器
 */
public class ForbiddenInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String content = request.getParameter("content");
        System.out.println("content1:"+content);
        if(StringUtils.hasLength(content)){
            System.out.println("content2:"+FilterUtil.pass(content));
            request.removeAttribute("content");
            request.setAttribute("content",FilterUtil.pass(content));
            System.out.println("getParameter:"+request.getParameter("content"));
        }
        return true;
    }
}
