package com.ironman.www.spring.service.entity;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by I321065 on 9/25/2017.
 */
@Alias("Article")
public class Article {

    private long articleId;

    private String articleTitle;

    private String articleLocation;

    private long articleUserId;

    private Date createDate;

    private Date updateDate;

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleLocation() {
        return articleLocation;
    }

    public void setArticleLocation(String articleLocation) {
        this.articleLocation = articleLocation;
    }

    public long getArticleUserId() {
        return articleUserId;
    }

    public void setArticleUserId(long articleUserId) {
        this.articleUserId = articleUserId;
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
