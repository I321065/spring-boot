package com.ironman.www.spring.service.vo;

/**
 * Created by superuser on 9/16/17.
 */
public class PublicKeyVO {

    private String publicKey;

    private String username;

    private String password;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
