package com.edu_platform.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{

        Object loginUser = request.getSession().getAttribute("User");

        if(loginUser == null){
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/signin").forward(request,response);
            return false;
        }else{
            return true;
        }
    }
}
