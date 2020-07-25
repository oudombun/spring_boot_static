package com.homework.demo.service.ArticleService;

import com.homework.demo.repository.model.Article;
import com.homework.demo.utility.Paging;

import java.util.List;

public interface ArticleService {
    void add(Article article);
    void update(Article article);
    void delete(int id);
    List<Article> findAll();
    Article findById(int id);
    int getLastId();
}
