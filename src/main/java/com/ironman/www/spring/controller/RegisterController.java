package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.UserService;
import com.ironman.www.spring.service.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nick on 2017/4/18.
 */

@RestController
public class RegisterController{

    Logger log = LogManager.getLogger(RegisterController.class);

    @Autowired
    private UserService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register() {
        String userName = getPara("userName");
        String pwd = getPara("passWord");
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(pwd)) {
            return null;
        }
        User user = service.getUserByName(userName);
        if(user != null) {
            return null;
        }
        user = new User();
        service.saveUser(user);
        renderJson(user);
    }

}
