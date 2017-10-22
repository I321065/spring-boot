package com.ironman.www.spring.service;

import com.ironman.www.spring.service.dao.ArticleDAO;
import com.ironman.www.spring.service.entity.Article;
import com.ironman.www.spring.service.vo.ArticleVO;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by superuser on 10/17/17.
 */
@Service
public class ArticleService {

    private static final Logger log = LogManager.getLogger(ArticleService.class);

    @Autowired
    private ArticleDAO articleDao;

    @Autowired
    UserService userService;

    //private static String rootPath = "/home/superuser/workspace/temp/ironman/articles"; //local
    private static String rootPath = "/apps/ironman/articles"; //production

    public Article createArticle(String title, String content, long userId) {
        if(StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
            return null;
        }
        String filePath = saveArticleToDisk(title, content, userId);
        Article article = new Article();
        article.setArticleTitle(title);
        article.setArticleLocation(filePath);
        article.setArticleUserId(userId);
        article.setCreateDate(new Date());
        article.setUpdateDate(new Date());
        articleDao.saveArticle(article);
        return article;
    }

    private String saveArticleToDisk(String title, String content, long userId) {
        String dirPath = rootPath + File.separator + userId + File.separator;
        File dir = new File(dirPath);
        if(!dir.exists()) {
            if(!dir.mkdir()) {
                log.error("failed to create directory, path=" + dir.getAbsolutePath());
                return null;
            }
        }
        String filePath = dirPath + title + "_" + new Date().getTime();
        File article = new File(filePath);
        if(!article.exists()) {
            try {
                if(!article.createNewFile()) {
                    log.error("failed to create article file, path=" + article.getAbsolutePath());
                }
            } catch (IOException e) {
                log.error("catch exception", e);
            }
        }
        //save to diskspace
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(article);
            bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            log.error("catch some exception", e);
        } finally {
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    log.error("catch exception", e);
                }
            }
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    log.error("catch exception", e);
                }
            }
        }
        return filePath;
    }

    public List<ArticleVO> listAllArticles() {
        return listAllArticles(-1);
    }

    public List<ArticleVO> listAllArticles(long articleUserId) {
        /*StringBuilder sql = new StringBuilder("select * from article");
        if(articleUserId > 0) {
            sql.append(" where articleUserId = " + articleUserId);
        }*/
        List<Article> articles = articleDao.getArticlesByUserId(articleUserId);
        if(articles == null || articles.isEmpty()) {
            log.error("");
            return null;
        }
        return convertToArticleVO(articles);
    }

    private List<ArticleVO> convertToArticleVO(List<Article> articles) {
        if(articles == null || articles.isEmpty()) {
            return null;
        }

        List<ArticleVO> articleVOs = new ArrayList<ArticleVO>(articles.size());

        for(Article article : articles) {
            ArticleVO vo = new ArticleVO();
            vo.setArticleId(article.getArticleId());
            vo.setTitle(article.getArticleTitle());
            vo.setContent(getArticleContent(article.getArticleLocation()));
            long articleUserId = article.getArticleUserId();
            vo.setUserName(userService.getUserNameById(articleUserId));
            articleVOs.add(vo);
        }
        return articleVOs;
    }

    private String getArticleContent(String filePath) {
        if(StringUtils.isBlank(filePath)) {
            log.error("file path is null");
            return null;
        }
        File file = new File(filePath);
        if(!file.exists()) {
            log.error("the path is not right, path=" + filePath);
            return null;
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String temp = null;
            while((temp = br.readLine()) != null) {
                content.append(temp);
            }
            return content.toString();
        } catch (FileNotFoundException e) {
            log.error("catch some exception", e);
        } catch (IOException e) {
            log.error("catch some exception", e);
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("catch some exception", e);
                }
            }
        }
        return null;
    }

    public boolean deleteArticleById(long articleId) {
        log.info("delete the article by id...");
        if(articleId < 0) {
            log.error("the article id of deleting is not right, id = " + articleId);
            return false;
        }
        String location = getArticleLocationByArticleId(articleId);

        if(StringUtils.isBlank(location)) {
            log.info("get the article location is null, article=" + articleId);
            return false;
        }

        //delete from datbbase
        articleDao.deleteById(articleId);

        //delete from disk
        boolean isDeleteFromDisk = deleteArticleFromDisk(location);

        return isDeleteFromDisk;
    }

    private String getArticleLocationByArticleId(long articleId) {
        /*String sql = "select articleLocation from article where articleId=" + articleId;
        Article article = articleDao.findFirst(sql);*/
        return articleDao.getArticleLocationByArticleId(articleId);
    }

    private boolean deleteArticleFromDisk(String filePath) {
        if(StringUtils.isBlank(filePath)) {
            log.error("the file path is null");
            return false;
        }
        File file = new File(filePath);
        if(file.isDirectory()) {
            log.error("the article file path is not right, it is directory not file");
            return false;
        }

        return file.delete();
    }

}
