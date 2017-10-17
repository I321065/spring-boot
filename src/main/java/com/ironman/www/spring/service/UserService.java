package com.ironman.www.spring.service;

import com.ironman.www.spring.service.dao.UserDAO;
import com.ironman.www.spring.service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by superuser on 9/21/17.
 */

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User getUserByName(String userName) {
        return userDAO.findUserByName(userName);
    }
}
