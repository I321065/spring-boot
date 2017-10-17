package com.ironman.www.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Nick on 2017/3/6.
 */
@RestController
@RequestMapping("/")
public class IndexController{

    Logger logger = LogManager.getLogger(IndexController.class);


    @RequestMapping("index")
    public void index() {

    }

}
