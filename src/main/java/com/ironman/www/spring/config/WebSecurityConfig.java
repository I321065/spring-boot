package com.ironman.www.spring.config;

import com.ironman.www.spring.service.security.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * Created by superuser on 10/25/17.
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomFilterSecurityInterceptor customFilterSecurityInterceptor;

    @Bean
    UserDetailsService customerUserService() {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserService());
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout().permitAll();

        httpSecurity.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }
}
