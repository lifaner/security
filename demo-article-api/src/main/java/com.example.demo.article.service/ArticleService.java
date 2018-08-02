package com.example.demo.article.service;

import java.util.List;

/**
 * Create by lifan
 * 23:13
 */
public interface ArticleService {

    /**
     * 添加文章
     */
    int insertArticle(String userId,String title,String link);

    List<Object> getArticleList();



}
