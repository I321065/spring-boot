package com.ironman.www.spring.service;

import com.alibaba.fastjson.JSONObject;
import com.ironman.www.spring.service.dao.UserDAO;
import com.ironman.www.spring.service.entity.User;
import com.ironman.www.spring.service.utils.JWTUtil;
import com.ironman.www.spring.service.utils.MD5Util;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by superuser on 9/21/17.
 */

@Service
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;


    public User getLoginUser(String userName, String password, String privateKey) {
        try {
            /*String sqlString = "select userId, userName, password, salt from user where userName = '" + userName + "'";
            log.info("get the user String=" + sqlString);
            User user = User.dao.findFirst(sqlString);*/
            User user = userDAO.getUserByName(userName);
            log.info("user is null by DB query=" + user);
            if(user == null) {
                log.info("user is null by DB query");
                return null;
            }
            String salt = user.getSalt();
            String pwdDB = user.getPassword();
            String passWord = MD5Util.generate(password, salt);
            log.info("get the user password" + passWord);
            if(passWord.equals(pwdDB)) {
                return user;
            }else {
                log.info("password is wrong");
            }
        } catch (Exception e) {
            log.error("catch exception", e);
        }
        return null;
    }

    public User getUserByName(String userName) {
        return userDAO.getUserByName(userName);
    }


    private User getUserByUserIdAndUserName(long userId, String userName) {
        return userDAO.getUserByIdAndName(userId, userName);
    }

    public User parseUserToken(String token) {
        if(StringUtils.isBlank(token)) {
            return null;
        }
        User user = null;
        try {
            Claims claims = JWTUtil.parseJWT(token);
            String userJson = claims.getSubject();
            JSONObject obj = (JSONObject) JSONObject.parse(userJson);
            long userId = obj.getLongValue("userId");
            String userName = obj.getString("userName");
            log.info("parse User Token userId=" + userId + " userName=" + userName);
            user = getUserByUserIdAndUserName(userId, userName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("parse User token failed", e);
        }
        return user;
    }

}
