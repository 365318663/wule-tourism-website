package cn.itcast.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HTMLFilterIntercepor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        System.out.println(uri);
        if(uri.contains(".html")){
            if (!(uri.contains("login")||uri.contains("index")||uri.contains("register")||uri.contains("register_ok")
            )){
                request.getRequestDispatcher("login.html").forward(request,response);
                return false;
            }
        }
        return true;
    }
}
