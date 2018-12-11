package cn.wolfcode.trip.base.util;

import cn.wolfcode.trip.base.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Set;

public class UserContext {

    public static final String USER_IN_SESSION = "USER_IN_SESSION";

    public static HttpSession getSession() {
        return ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
                .getRequest().getSession();
    }

    public static void setUser(User user) {
        if (user == null) {
            getSession().invalidate();
        } else {
            getSession().setAttribute(USER_IN_SESSION, user);
        }
    }

    public static User getUser() {
        return (User) getSession().getAttribute(USER_IN_SESSION);
    }

}
