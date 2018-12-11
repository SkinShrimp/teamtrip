package cn.wolfcode.trip.app.interceptor;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.util.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否已经登录
        User user = UserContext.getUser();
        if(user==null){
            //重定向到登录页面
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }
}
