package org.example.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.dto.ArticleForm;
import org.example.firstproject.entity.Article;
import org.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController
{

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm()
    {
        return "articles/new";
    }


    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form)
    {
        log.info(String.format("[ArticleController/createArticle] form:[%s]", form));

        // 1. DTO 를 엔티티로 변환
        Article article = form.toEntity();
        log.info(String.format("[ArticleController/createArticle] article:[%s]", article));

        // 2. 리파지토리로 엔티티를 DB 에 저장
        Article saved = articleRepository.save(article);
        log.info(String.format("[ArticleController/createArticle] saved:[%s]", saved));

        return "";
    }
}

