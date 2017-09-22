package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.UserService;
import com.ironman.www.spring.service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by superuser on 9/21/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/ironman")
public class LoginController {

    @Inject
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        User user = userService.getUserByName("test");
        return user.getUserName();
    }

}
