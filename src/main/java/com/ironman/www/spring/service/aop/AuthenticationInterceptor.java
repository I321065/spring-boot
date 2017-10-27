package com.ironman.www.spring.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by superuser on 10/27/17.
 */
@Aspect
@Order(-99)
@Component
public class AuthenticationInterceptor {

}
