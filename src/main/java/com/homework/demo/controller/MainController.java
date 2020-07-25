package com.homework.demo.controller;

import com.homework.demo.repository.model.Article;
import com.homework.demo.service.ArticleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    ArticleService articleService;

    @GetMapping({"/","home"})
    public String index(ModelMap modelMap, @RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "5") int limit){

        Article article= new Article();
        article.setId(articleService.getLastId());
        modelMap.addAttribute("articleNew",article);

        /*pagination*/
        List<Article> pagingList= new ArrayList<>();
        Article tmp;
        int startIndex =(page - 1) * limit;
        int endIndex = page*limit;
        int currentPage=1,Allpage,b=0;
        if(articleService.findAll().size()==0) {
            Allpage=1;
        }
        else{
            Allpage = (int) Math.ceil((articleService.findAll().size() /(float)limit));
        }
        for (int i = startIndex; i < endIndex; i++) {
            if (articleService.findAll().size() <= i){
                b=1;
                currentPage = Allpage;
                break;
            }else{
                currentPage=page;
            }
            tmp= new Article();
            tmp.setId(articleService.findAll().get(i).getId());
            tmp.setTitle(articleService.findAll().get(i).getTitle());
            tmp.setDesc(articleService.findAll().get(i).getDesc());
            tmp.setAuthor(articleService.findAll().get(i).getAuthor());
            tmp.setThumbnail(articleService.findAll().get(i).getThumbnail());
            pagingList.add(tmp);
        }
        int next=currentPage+1;
        int prev=currentPage-1;
        if(next>Allpage) next=1;
        if(prev<=0) prev=Allpage;
        modelMap.addAttribute("articles",pagingList);
        modelMap.addAttribute("totalPage",Allpage);
        modelMap.addAttribute("cur",currentPage);
        modelMap.addAttribute("next",next);
        modelMap.addAttribute("prev",prev);
        return "index";
    }


    @GetMapping("/adddatas")
    public String addData(ModelMap modelMap){
        Article article= new Article();
        article.setId(articleService.getLastId());
        modelMap.addAttribute("article",article);
        return "adddata";
    }

    @PostMapping("/add")
    public String add_(@Valid @ModelAttribute Article article, BindingResult bindingResult, @RequestParam MultipartFile file){
        if(bindingResult.hasErrors()){
            return "adddata";
        }else{
            /*file upload*/
            String serverPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
            String filename= UUID.randomUUID().toString()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
            if(!file.isEmpty()){
                try{
                    Files.copy(file.getInputStream(),Paths.get(serverPath,filename));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                filename="default.png";
            }
            article.setId(articleService.getLastId());
            article.setThumbnail(filename);
            /* end file upload*/
        }
        articleService.add(article);
        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable int id, ModelMap modelMap){
        modelMap.addAttribute("article",articleService.findById(id));
        System.out.println("before"+articleService.findById(id).getId());
        System.out.println(articleService.findById(id).getTitle());
        return "update";
    }
    @PostMapping("/update")
    public String update_(@Valid @ModelAttribute Article article, BindingResult bindingResult,@RequestParam("file") MultipartFile file){
        if(bindingResult.hasErrors()){
            return "update";
        }else{
            String filename="";
            if(!file.isEmpty()){
                String serverPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
                filename= UUID.randomUUID().toString()+"."+file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".")+1);
                try{
                    Files.copy(file.getInputStream(),Paths.get(serverPath,filename));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                filename=article.getThumbnail();
            }
            article.setThumbnail(filename);
            System.out.print("update object");
            System.out.println(article.getId());
            /* end file upload*/
        }
        articleService.update(article);
        return "redirect:/";
    }
    @GetMapping("/view/{id}")
    public String view(ModelMap modelMap, @PathVariable("id") int id){
        modelMap.addAttribute("article",articleService.findById(id));
        return "view";
    }
    @GetMapping("/del/{id}")
    public String del(@PathVariable("id") int id){
        articleService.delete(id);
        return "redirect:/";
    }

    @GetMapping("navbar")
    public String getNabar() {
        return "fragement/navbar :: nav1";
    }

    @GetMapping("navbar-dropdown")
    public String getDropDownNavbar() {
        return "fragement/navbar :: nav2";
    }

}
