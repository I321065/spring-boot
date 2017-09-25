package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.UserService;
import com.ironman.www.spring.service.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by superuser on 9/21/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/ironman")
public class LoginController {

    Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        User user = userService.getUserByName("test");
        return user.getUserName();
    }

}
