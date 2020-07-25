package com.homework.demo.service;

import com.homework.demo.repository.ArticleRepository.ArticleRepository;
import com.homework.demo.repository.model.Article;
import com.homework.demo.service.ArticleService.ArticleService;
import com.homework.demo.utility.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Override
    public void add(Article article) {
        articleRepository.add(article);
    }

    @Override
    public void update(Article article) {
        articleRepository.update(article);
    }

    @Override
    public void delete(int id) {
        articleRepository.delete(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public int getLastId() {
        return articleRepository.getLastId();
    }
}
