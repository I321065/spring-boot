package com.ironman.www.spring.controller;

import com.ironman.www.spring.service.ArticleService;
import com.ironman.www.spring.service.CommentService;
import com.ironman.www.spring.service.entity.Comment;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by superuser on 9/18/17.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    Logger log = LogManager.getLogger(ArticleController.class);

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/save")
    public void save() {
        //User user = getAttr("user");//get user from token
        //int userId = user.getUserId();
        int userId = 1;

        try {
            ArticleRequestObject articleRequestObject = ParseRequest.getObjectFromRequest(ArticleRequestObject.class, this);
            String articleTitle = BeanUtils.getProperty(articleRequestObject, "title");
            String articleContent = BeanUtils.getProperty(articleRequestObject, "content");
            if(StringUtils.isBlank(articleTitle) || StringUtils.isBlank(articleContent)) {
                renderJson("title or content can not be null");
                return;
            }

            Article article = articleService.createArticle(articleTitle, articleContent, userId);
            Result result = null;
            if(article != null) {
                result = new Result(article);
            }else{
                result = new Result("something wrong happened, please contact administrator");
            }
            renderJson(result);
        } catch (IOException e) {
            log.error("catch exception", e);
        } catch (IllegalAccessException e) {
            log.error("catch exception", e);
        } catch (InvocationTargetException e) {
            log.error("catch exception", e);
        } catch (NoSuchMethodException e) {
            log.error("catch exception", e);
        }
    }

    @RequestMapping("/update")
    public void update() {

    }

    @RequestMapping("/list")
    public void list() {
        List<ArticleVO> articleVOs = null;
        String articleUserId = getPara("articleUserId");
        if(!StringUtils.isBlank(articleUserId)) {
            articleVOs = articleService.listAllArticles(Integer.parseInt(articleUserId));
        }else {
            articleVOs = articleService.listAllArticles();
        }
        renderJson(new Result(articleVOs));
    }

    @RequestMapping("/comment")
    public void comment() {
        int articleId = 1;
        int userId = 1;
        String commentDetail = "";
        int commentOverall = 1;

        Comment comment = new Comment();
        comment.setArticleId(articleId);
        comment.setCommentUserId(userId);
        comment.setCommentDetail(commentDetail);
        comment.setCommentOverall(commentOverall);
        Comment saveBean = commentService.save(comment);



    }



}
