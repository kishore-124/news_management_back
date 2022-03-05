package com.news.controller;

import com.news.dao.NewsRepository;
import com.news.model.News;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.*;

@RestController
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @PostMapping(value = "/news")
    public HashMap<String, String> createNews(@RequestBody News news){

        newsRepository.save(news);
        HashMap<String, String> result_map = new HashMap<>();
        result_map.put("message", "News Created Successfully.");

        return result_map;
    }

    @GetMapping("/news")
    public List<News> newsList(){
        return newsRepository.findAll();
    }

    @GetMapping("/news/{id}")
    public News getNews(@PathVariable("id")int id){
        return newsRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/news/{id}")
    public HashMap<String, String> deleteNews(@PathVariable("id")int id){

        newsRepository.deleteById(id);
        HashMap<String, String> result_map = new HashMap<>();
        result_map.put("message", "News Destroyed Successfully.");

        return result_map;
    }

    @PutMapping("/news/{id}/update_like")
    public HashMap<String, String> updateLike(@PathVariable("id") int id)  {

        News news_exists = newsRepository.findById(id).orElse(null);
        news_exists.setLike_count(news_exists.getLike_count() + 1);
        newsRepository.save(news_exists);

        HashMap<String, String> result_map = new HashMap<>();
        result_map.put("message", "Like updated successfully.");

        return result_map;
    }

    @PutMapping("/news/{id}/update_dislike")
    public HashMap<String, String> updateDisLike(@PathVariable("id") int id)  {

        News news_exists = newsRepository.findById(id).orElse(null);
        news_exists.setDislike_count(news_exists.getDislike_count() + 1);
        newsRepository.save(news_exists);

        HashMap<String, String> result_map = new HashMap<>();
        result_map.put("message", "DisLike updated successfully.");

        return result_map;
    }

    @PutMapping("/news/{id}")
    public HashMap<String, String> updateNews(@PathVariable("id") int id, @RequestBody News news)  {

        News news_exists = newsRepository.findById(id).orElse(null);
        news_exists.setTitle(news.getTitle());
        news_exists.setDescription(news.getDescription());
        newsRepository.save(news_exists);

        HashMap<String, String> result_map = new HashMap<>();
        result_map.put("message", "News updated successfully.");

        return result_map;
    }

}
