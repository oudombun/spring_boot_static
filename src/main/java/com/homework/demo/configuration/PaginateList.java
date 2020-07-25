package com.homework.demo.configuration;

import com.homework.demo.repository.model.Article;
import com.homework.demo.service.ArticleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaginateList {
    @Autowired
    ArticleService articleServices;

    public List<Article> pagedList(int page,int limit){
        List<Article> pagingList= new ArrayList<>();
//        Article tmp;
        int currentPage=1,Allpage,b=0;
        int startIndex =(page - 1) * limit;
        int endIndex = page*limit;
//        if(articleServices.findAll().size()==0) {
//            Allpage=1;
//        }
//        else{
//            Allpage = (int) Math.ceil((articleServices.findAll().size() /(float)limit));
//        }
//        for (int i = startIndex; i < endIndex; i++) {
//            if (articleServices.findAll().size() <= i){
//                b=1;
//                currentPage = Allpage;
//                break;
//            }else{
//                currentPage=page;
//            }
//            tmp= new Article();
//            tmp.setId(articleServices.findAll().get(i).getId());
//            tmp.setTitle(articleServices.findAll().get(i).getTitle());
//            tmp.setDesc(articleServices.findAll().get(i).getDesc());
//            tmp.setAuthor(articleServices.findAll().get(i).getAuthor());
//            tmp.setThumbnail(articleServices.findAll().get(i).getThumbnail());
//            pagingList.add(tmp);
//        }
        return pagingList;
    }

//    public Map<String,Integer> listInfo(){
//        Map<String,Integer> info=null;
//        int next=currentPage+1;
//        int prev=currentPage-1;
//        if(next>Allpage) next=1;
//        if(prev<=0) prev=Allpage;
//        info.put("cur",currentPage);
//        info.put("totalPage",Allpage);
//        info.put("next",next);
//        info.put("prev",prev);
//        return info;
//    }
}
