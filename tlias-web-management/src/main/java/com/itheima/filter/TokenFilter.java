package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        if(requestURI.contains("/login")){
            filterChain.doFilter(request,response);
            return;
        }
        String token=request.getHeader("token");
        if(token==null || token.isEmpty()){
            response.setStatus(401);
            return;
        }
        try {
            Claims claims = JwtUtils.parseJwt(token);
        } catch (Exception e) {
            response.setStatus(401);
            return;
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
