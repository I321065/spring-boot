package com.ironman.www.spring.service;

import com.ironman.www.spring.service.dao.CommentDAO;
import com.ironman.www.spring.service.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by superuser on 10/17/17.
 */
@Service
public class CommentService {

    @Autowired
    private CommentDAO commentDAO;

    public Comment saveComment(Comment comment) {
        comment.setCreateDate(new Date());
        comment.setUpdateDate(new Date());
        commentDAO.saveComment(comment);
        return comment;
    }

}
