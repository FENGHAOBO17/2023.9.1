package com.cleansoft.demo.Filter;


import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


@Component
public class JWTFilter extends Filter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //检查认证请求 获取请求头中的token
        if (isLoginAttempt(request,response)){
            try {
                //执行登录 subject.login
//                System.out.println("999");
                executeLogin(request,response);
            } catch (Exception e) {
                //如果请求头中没有token  跳转到401请求！让401controller返回前端一个请求
                response401(request,response);
                e.printStackTrace();
            }
        }else {
            System.out.println("999");
            //如果没有请求头的情况，我们直接返回401信息即可！
            response401(request,response);

        }
        return true;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest request1 = (HttpServletRequest) request;
        //从请求头中获取认证token信息
        String token = request1.getHeader("Authorization");
        System.out.println(token);
        return token!=null;
    }

    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest request1 = (HttpServletRequest) request;
        //从请求头中获取认证token信息
        String token = request1.getHeader("Authorization");
        getSubject(request,response).login(new MyJsonWebToken(token));
        System.out.println("123");
        return true;
    }
    //如果请求头没有token则跳转401请求controller
    private void response401(ServletRequest request, ServletResponse response){
        HttpServletResponse response1 = (HttpServletResponse) response;
        try {
            response1.sendRedirect("/401");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}