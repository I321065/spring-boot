package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nick on 2017/4/18.
 */

@RestController("/")
public class RegisterController{

    Logger log = LogManager.getLogger(RegisterController.class);

    @Autowired
    private UserService service;

    @RequestMapping("register")
    public void register() {
        String userName = getPara("userName");
        String pwd = getPara("passWord");
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(pwd)) {
            renderJson("the userName and password can be null");
            return;
        }
        User user = service.getUserByName(userName);
        if(user != null) {
            renderJson("The userName has been registered, please change it, userName=" + userName);
            return;
        }
        user = new User(userName, pwd);
        service.saveUser(user);
        renderJson(user);
    }

}
