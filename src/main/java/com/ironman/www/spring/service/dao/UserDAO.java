package com.ironman.www.spring.service.dao;

import com.ironman.www.spring.service.entity.User;

/**
 * Created by superuser on 9/21/17.
 */

public interface UserDAO {

    User getUserByName(String username);

    String getUserNameByUserId(long userId);

    User getUserByIdAndName(Long userId, String username);

    void saveUser(User user);

}
