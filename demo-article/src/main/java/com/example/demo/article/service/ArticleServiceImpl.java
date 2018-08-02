package com.example.demo.article.service;

import ch.qos.logback.core.util.TimeUtil;
import com.example.demo.constant.ArticleConstant;
import org.apache.activemq.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Create by lifan
 * 2018/8/2 23:16
 */

public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int insertArticle(String userId,String title,String link) {
        Long article_id = redisTemplate.opsForValue().increment("article:", 0);
        String voted = "voted:"+article_id;
        redisTemplate.opsForSet().add(voted,userId);
        redisTemplate.expire(voted,ArticleConstant.ONE_WEEK_IN_SECONDS,TimeUnit.SECONDS);

        return 0;
    }

    @Override
    public List<Object> getArticleList() {
        return null;
    }
}
