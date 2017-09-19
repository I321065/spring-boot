package com.ironmam.www.spring.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by superuser on 9/19/17.
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/ironman")
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "hello wordfdfasfasfasf";
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleController.class, args);
    }

}
