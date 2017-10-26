package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.UserService;
import com.ironman.www.spring.service.common.ResponseResult;
import com.ironman.www.spring.service.entity.User;
import com.ironman.www.spring.service.utils.Base64Util;
import com.ironman.www.spring.service.utils.JedisUtil;
import com.ironman.www.spring.service.utils.RSAUtil;
import com.ironman.www.spring.service.vo.PublicKeyVO;
import com.ironman.www.spring.service.vo.UserVO;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.ironman.www.spring.service.common.Constants.RESPONSE_ERROR;

/**
 * Created by superuser on 9/21/17.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/spring/ironman")
public class LoginController {

    private static final Logger log = LogManager.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    private static Map<String, String> keyPairs = new ConcurrentHashMap<String, String>();

    @RequestMapping(value = "/publicKey", method = RequestMethod.GET)
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
            result = new ResponseResult(null, 1, RESPONSE_ERROR);
        }
        return result;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseResult register(@RequestBody(required = true) PublicKeyVO vo) {

        String publicKey = vo.getPublicKey();
        String username = vo.getUsername();
        String password = vo.getPassword();
        if(StringUtils.isBlank(publicKey) || StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            String mess = "username or password is null";
            log.error(mess);
            return new ResponseResult(null, 1, mess);
        }
        User user = userService.getUserByName(username);
        if(user != null) {
            return new ResponseResult(null, 1, "the user name has been registered, please use another one");
        }
        user = new User(username, password);
        userService.saveUser(user);
        return new ResponseResult(user);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody(required = true) PublicKeyVO vo) {

        log.info("access the login controller");
        ResponseResult result = null;
        String publicKey = vo.getPublicKey();
        String username = vo.getUsername();
        String password = vo.getPassword();
        if(StringUtils.isBlank(publicKey) || StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            log.error("some message is null, publicKey=" + publicKey + ", username=" + username + ", password=" + password);
            return new ResponseResult(null, 1, "some argument is null");
        }

        String privateKey = JedisUtil.getStringValue(publicKey);
        byte[] bytes = null;
        try {
            bytes = Base64Util.decodeString(password);
            String pwd = new String(RSAUtil.decryptByPrivateKey(bytes, privateKey));
            User user = userService.getLoginUser(username, pwd, privateKey);
            if(user == null) {
                return new ResponseResult(null, 1, "the password is not right");
            }
            UserVO uVO = new UserVO();
            uVO.setUsername(user.getUsername());
            uVO.setToken(userService.generateUserToken(user));
            return new ResponseResult(uVO);
        } catch (Exception e) {
            log.error("catch exception when get the content from private key", e);
        }
        return new ResponseResult(null, 1, RESPONSE_ERROR);
    }

}
