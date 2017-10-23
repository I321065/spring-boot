package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.ArticleService;
import com.ironman.www.spring.service.CommentService;
import com.ironman.www.spring.service.common.ResponseResult;
import com.ironman.www.spring.service.entity.Article;
import com.ironman.www.spring.service.entity.Comment;
import com.ironman.www.spring.service.vo.ArticleVO;
import com.ironman.www.spring.service.vo.CommentVO;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by superuser on 9/18/17.
 */
@RestController
@RequestMapping("/spring/ironman/article")
public class ArticleController {

    Logger log = LogManager.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseResult save(@RequestBody(required = true) Map<String, String> titleAndContent) {
        //User user = getAttr("user");//get user from token
        //int userId = user.getUserId();
        int userId = 1;//get the userId by session/token

        ResponseResult result = null;

        String articleTitle = titleAndContent.get("title");
        String articleContent = titleAndContent.get("content");

        if(StringUtils.isBlank(articleTitle) || StringUtils.isBlank(articleContent)) {
            log.error("title or content can not be null");
            return null;
        }

        Article article = articleService.createArticle(articleTitle, articleContent, userId);

        if(article != null) {
            result = new ResponseResult(article);
        }else{
            result = new ResponseResult(null, 1, "something wrong happened, please contact administrator");
        }
        return result;
    }

    @RequestMapping("/update")
    public void update() {

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseResult list() {
        ResponseResult result = null;
        List<ArticleVO> articleVOs = null;
        articleVOs = articleService.listAllArticles();
        return new ResponseResult(articleVOs);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseResult list(@RequestParam(value = "articleUserId", required = true, defaultValue = "-1") long articleUserId) {
        List<ArticleVO> articleVOs = null;
        if(articleUserId < 0) {
            return new ResponseResult(null, 1, "the article user id is null");
        }
        articleVOs = articleService.listAllArticles(articleUserId);
        return new ResponseResult(articleVOs);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseResult comment(@RequestBody(required = true) Comment comment) {

        long userId = 1;//get it from token

        /*Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setCommentUserId(userId);
        comment.setCommentDetail(commentDetail);
        comment.setCommentOverall(commentOverall);*/
        comment.setCommentUserId(userId);
        commentService.saveComment(comment);

        return new ResponseResult(new CommentVO(comment.getArticleId(), comment.getCommentOverall()));
    }

}
