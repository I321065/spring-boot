package com.ironman.www.spring.service.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by superuser on 10/26/17.
 */
public abstract class AbstractHttpServletFilter implements Filter {

    private FilterConfig filterConfig;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        init();

    }


    protected void init() {
        //you can use filterConfig directly
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        doFilter(request, response, filterChain);
    }

    protected abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain);

    @Override
    public void destroy() {

    }
}
