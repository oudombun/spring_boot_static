package com.homework.demo.repository;

import com.homework.demo.repository.ArticleRepository.ArticleRepository;
import com.homework.demo.repository.model.Article;
import com.homework.demo.utility.Paging;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleRepositoryImp implements ArticleRepository {
    List<Article> list = new ArrayList<>();
    ArticleRepositoryImp(){
        Article art;
        for (int i=1;i<=15;i++){
            art = new Article();
            art.setId(i);
            art.setTitle("Title"+i);
            art.setDesc("Description"+i);
            art.setAuthor("Oudom");
            art.setThumbnail("default.png");
            list.add(art);
        }
    }
    @Override
    public void add(Article article) {
        list.add(article);
    }

    @Override
    public void update(Article article) {
        for (int i = 0;i<list.size();i++){
            if(list.get(i).getId()==article.getId()){
                list.set(i,article);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0;i<list.size();i++){
            if(list.get(i).getId()==id){
                list.remove(i);
                return;
            }
        }
    }

    @Override
    public List<Article> findAll() {
//        paging.setTotalCount(list.size());
        return list;
    }


    @Override
    public Article findById(int id) {
        for (int i = 0;i<list.size();i++){
            if(list.get(i).getId()==id){
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public int getLastId() {
        int lastid;
        int size = list.size();
        if(size>0){
            lastid = list.get(list.size()-1).getId() +1;
        }else {
            lastid = 1;
        }

        return lastid;
    }

}
