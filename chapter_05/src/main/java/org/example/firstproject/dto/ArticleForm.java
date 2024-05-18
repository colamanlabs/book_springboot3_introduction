package org.example.firstproject.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.firstproject.entity.Article;


@AllArgsConstructor
@ToString
public class ArticleForm
{
    private final String title;
    private final String content;

    public Article toEntity()
    {
        return new Article(null, title, content);
    }
}
