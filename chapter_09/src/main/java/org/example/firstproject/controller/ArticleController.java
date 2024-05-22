package org.example.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.firstproject.dto.ArticleForm;
import org.example.firstproject.entity.Article;
import org.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

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

        // redirect
        return "redirect:/articles/" + saved.getId();

    }


    /*
    컨트롤러에서 URL 변수를 사용할 때는 중괄호 하나({})만 쓴다.
     */
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model)   // 매개변수로 id 받아오기
    {
        log.info(String.format("id:[%s]", id));

        /*
        https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html
         */
//        Optional<Article> articleEntity = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model)
    {
        // 1안) 캐스팅 하는 방법
//        List<Article> articleEntityList = (List<Article>)(articleRepository.findAll());

        // 2안) 메소드 리턴타입으로 받는 방법
//        Iterable<Article> articleEntityList = articleRepository.findAll();

        // 3안) findAll() 을 오버라이딩 하는 방법
        // 책에서는 3안으로 한다.
        ArrayList<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "articles/index";
    }

    /*
    뷰페이지에서 변수를 사용할 때는 중괄호를 두개{{}} 쓰지만,
    컨트롤러에서 URL 변수를 사용할 때는 하나{} 만 쓴다.
     */
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model)
    {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form)
    {
        log.info(String.format("[ArticleController/update] form:[%s]", form));

        Article articleEntity = form.toEntity();

        log.info(String.format("[ArticleController/update] articleEntity:[%s]", articleEntity));

        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        if (target != null)
        {
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/" + articleEntity.getId();
    }


    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr)
    {
        log.info("삭제 요청이 들어왔습니다!!");

        Article target = articleRepository.findById(id).orElse(null);
        log.info(String.format("[ArticleController/delete] target:[%s]", target));

        if (target != null)
        {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }
        return "redirect:/articles";
    }
}

