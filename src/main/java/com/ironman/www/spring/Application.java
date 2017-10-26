package com.ironman.www.spring;

import com.ironman.www.spring.service.filter.PermissionCheckFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import static com.ironman.www.spring.service.common.CustomFilterNames.PERMISSON_CHECK_FILTER;

/**
 * Created by superuser on 9/19/17.
 */

@SpringBootApplication
@MapperScan("com.ironman.www.spring.service.dao")
@ServletComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
