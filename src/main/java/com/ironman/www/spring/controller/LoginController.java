package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.UserService;
import com.ironman.www.spring.service.common.ResponseResult;
import com.ironman.www.spring.service.entity.User;
import com.ironman.www.spring.service.utils.Base64Util;
import com.ironman.www.spring.service.utils.JedisUtil;
import com.ironman.www.spring.service.utils.RSAUtil;
import com.ironman.www.spring.service.vo.UserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.CollationElementIterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by superuser on 9/21/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/ironman")
public class LoginController {

    Logger log = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    private static Map<String, String> keyPairs = new ConcurrentHashMap<String, String>();

    public ResponseResult getPublicKey() {
        ResponseResult result = null;
        try {
            List<String> loginKeyPair = RSAUtil.createLoginKeyPair();
            if(loginKeyPair == null || loginKeyPair.size() != 2) {
                log.error("failed to create public key");
                return null;
            }
            String publicKey = loginKeyPair.get(0);
            String privateKey = loginKeyPair.get(1);
            JedisUtil.set(publicKey, privateKey);
            result = new ResponseResult(publicKey, 0, null);
        } catch (Exception e) {
            log.error("catch exception", e);
            result = new ResponseResult(null, 1, "failed to create public key")
        }
        return result;
    }

    @RequestMapping("/login")
    public ResponseResult login(@RequestParam(value = "userName", required = true) String userName,
                        @RequestParam(value = "passWord", required = true) String passWord,
                        @RequestParam(value = "publicKey", required = true) String publicKey) {

        log.info("access the login controller");
        ResponseResult result = null;

        String privateKey = JedisUtil.getStringValue(publicKey);
        String pwd = new String(RSAUtil.decryptByPrivateKey(Base64Util.decodeString(passWord), privateKey));
        User user = userService.getLoginUser(uName, pwd, privateKey);
        if(user == null) {
            return new ResponseResult(null, 1, "the password is not right");
        }
        UserVO uVO = new UserVO();
        uVO.setUserName(user.getUserName());
        uVO.setToken(userService.generateUserToken(user));
        renderJson(new Result(uVO));
        User user = userService.getUserByName("test");
        return user.getUserName();
    }

}
