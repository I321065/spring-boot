package com.ironman.www.spring.service;

import com.ironman.www.spring.service.dao.ArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by superuser on 10/17/17.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleDAO dao;



}
