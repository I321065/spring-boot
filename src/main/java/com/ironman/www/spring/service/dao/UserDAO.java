package com.ironman.www.spring.service.dao;

import com.ironman.www.spring.service.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by superuser on 9/21/17.
 */

public interface UserDAO {
    User findUserByName(String userName);

}
