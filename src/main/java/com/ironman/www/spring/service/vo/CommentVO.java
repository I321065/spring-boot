package com.ironman.www.spring.service.vo;

/**
 * Created by superuser on 10/23/17.
 */
public class CommentVO {

    private long articleId;

    private int comment;

    public CommentVO() {

    }

    public CommentVO(long articleId, int comment) {
        this.articleId = articleId;
        this.comment = comment;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
