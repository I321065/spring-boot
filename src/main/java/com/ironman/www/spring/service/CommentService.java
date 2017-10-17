package com.ironman.www.spring.service;

import com.ironman.www.spring.service.dao.CommentDAO;
import com.ironman.www.spring.service.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by superuser on 10/17/17.
 */
@Service
public class CommentService {

    @Autowired
    private CommentDAO dao;

    public Comment save(Comment comment) {
        return dao.save(comment);
    }

}
