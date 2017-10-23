package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.UserService;
import com.ironman.www.spring.service.common.ResponseResult;
import com.ironman.www.spring.service.entity.User;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nick on 2017/4/18.
 */

@RestController
@RequestMapping(value = "/spring/ironman")
public class RegisterController{

    Logger log = LogManager.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseResult register(@RequestParam(value = "userName", required = true) String userName,
                         @RequestParam(value = "passWord", required = true) String passWord) {

        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passWord)) {
            String mess = "user name or pass word is null";
            log.error(mess);
            return new ResponseResult(null, 1, mess);
        }
        User user = userService.getUserByName(userName);
        if(user != null) {
            return new ResponseResult(null, 1, "the user name has been registered, please use another one");
        }
        user = new User(userName, passWord);
        userService.saveUser(user);
        return new ResponseResult(user);
    }

}
