package cn.wolfcode.trip.app.filter;


import cn.wolfcode.trip.base.util.FilterUtil;
import cn.wolfcode.trip.base.util.UploadUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class ForbidFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("敏感词过滤器正在执行...");

        HttpServletRequest req = (HttpServletRequest)request;

        String content = req.getParameter("content");
        System.out.println("content1:"+content);
        if(StringUtils.hasLength(content)){
            System.out.println("content过滤后:"+FilterUtil.pass(content));
            req.setAttribute("content",FilterUtil.pass(content));
            System.out.println("getParameter:"+req.getAttribute("content"));
        }
        chain.doFilter(req,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    @Override
    public void destroy() {

    }
}
