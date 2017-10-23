package com.ironman.www.spring.service.dao;


import com.ironman.www.spring.service.entity.Article;

import java.util.List;

/**
 * Created by superuser on 9/21/17.
 */

public interface ArticleDAO {

    void saveArticle(Article article);

    List<Article> getArticlesByUserId(long articleUserId);

    void deleteByArticleId(long articleId);

    String getArticleLocationByArticleId(long articleId);
}
