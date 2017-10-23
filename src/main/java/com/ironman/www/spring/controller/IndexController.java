package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.common.ResponseResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nick on 2017/3/6.
 */
@RestController
@RequestMapping("/spring/ironman")
public class IndexController {

    Logger logger = LogManager.getLogger(IndexController.class);


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ResponseResult index() {
        return new ResponseResult("access index");
    }

}
