package com.ironman.www.spring.service.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by Nick on 2017/3/5.
 */
@Alias("User")
public class User {

    private long userId;
    private String userName;
    private String password;
    private String salt;

    private Date createDate;
    private Date updateDate;

    public User() {}

    public User(String userName, String passWord) {
        this.userName = userName;
        this.password = passWord;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
