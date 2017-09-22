package com.ironman.www.spring.service.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by superuser on 9/21/17.
 */

@Alias("User")
public class User {

    private int userId;

    private String userName;

    private String password;

    private Date createDate;

    private Date updateDate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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
