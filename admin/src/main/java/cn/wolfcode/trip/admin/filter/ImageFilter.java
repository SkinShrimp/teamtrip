package cn.wolfcode.trip.admin.filter;


import cn.wolfcode.trip.base.util.UploadUtil;
import org.apache.commons.io.FileUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class ImageFilter implements Filter {


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        //获取当前请求的图片路径
        String uri = req.getRequestURI();
        //判断图片目录中是否存在该图片
        File file = new File(UploadUtil.PATH, uri);
        if(file.exists()){
            response.getOutputStream().write(FileUtils.readFileToByteArray(file));
        }else{
            //放行
            chain.doFilter(request,response);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }



    public void destroy() {

    }
}
