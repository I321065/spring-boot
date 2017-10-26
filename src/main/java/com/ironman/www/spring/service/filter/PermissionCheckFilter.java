package com.ironman.www.spring.service.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by superuser on 10/26/17.
 */
@Order(1)
@WebFilter(filterName = "permissionCheckFilter", urlPatterns = "/*")
public class PermissionCheckFilter extends AbstractHttpServletFilter{

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

    }

}
